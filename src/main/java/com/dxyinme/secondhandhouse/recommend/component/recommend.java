package com.dxyinme.secondhandhouse.recommend.component;


import com.dxyinme.secondhandhouse.recommend.CONSTLIST.CONSTLIST;
import com.dxyinme.secondhandhouse.recommend.model.*;
import com.dxyinme.secondhandhouse.recommend.service.HouseTableService;
import com.dxyinme.secondhandhouse.recommend.service.HouseTagService;
import com.dxyinme.secondhandhouse.recommend.service.SubscribeTableService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class recommend {

    @Autowired
    SubscribeTableService subscribeTableService;

    @Autowired
    HouseTagService houseTagService;

    @Autowired
    HouseTableService houseTableService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private static List<HouseTag> hurrySell = new ArrayList<>();

    private static Map<HouseTable,Double> houseMap = new HashMap<>();

    private static List<HouseTable> houseTables = new ArrayList<>();

    @Scheduled(cron = "*/30 * * * * ?")
    public void refreshHurrySell() {
        System.err.println("refreshHurrySell");
        HouseTagExample houseTagExp = new HouseTagExample();
        houseTagExp.or().andTagEqualTo(CONSTLIST.hurrySell);
        hurrySell = houseTagService.selectByExample(houseTagExp);
        List<String> strings = new ArrayList<>();
        for (HouseTag x:
             hurrySell) {
            strings.add(x.getHouseId());
        }
        stringRedisTemplate.opsForValue().set("NONE" , new Gson().toJson(strings));
//        stringRedisTemplate.opsForValue().set("1","1");
//        String s = stringRedisTemplate.opsForValue().get("1");
//        System.out.println(s);
    }

    private Double getCos(Double[] staObc, Double[] obc) {
        int l = staObc.length;
        if (l != obc.length) return null;
        Double res = 0.0 , x_sum=0.0 , y_sum=0.0;
        for (int i=0; i<l; i++){
            x_sum += staObc[i] * staObc[i];
            y_sum += obc[i] * obc[i];
        }
        x_sum = Math.sqrt(x_sum);
        y_sum = Math.sqrt(y_sum);
        for (int i=0; i<l; i++){
            res += staObc[i] * obc[i] / (x_sum * y_sum);
        }

//        for (int i=0;i<l;i++) System.out.print(staObc[i] + ",");
//        System.out.println();
//        for (int i=0;i<l;i++) System.out.print(obc[i] + ",");
//        System.out.println("\n" + res);
        return res;
    }

    private Double[] getObc(List<HouseTable> houseTableList){
        Double[] obc = {0.0,0.0,0.0,0.0};
        for (HouseTable x:
                houseTableList) {
            obc[0] += 1.0 * x.getHouseArea();
            obc[1] += 1.0 * x.getHousePrice();
            obc[2] += Double.parseDouble(x.getBuildTime());
            obc[3] += 1.0 * x.getHouseFloor();
        }
        for(int i = 0; i < 4; i++) {
            obc[i] /= 1.0 * houseTableList.size();
        }
        return obc;
    }

    private List<String> getResHouseId(List<HouseTable> root , Double[] staObc) {
        for (HouseTable x:
             houseTables) {
            if (root.contains(x)) continue;
            List<HouseTable> now = new ArrayList<>();
            now.add(x);
            Double[] obcNow = getObc(now);
            houseMap.put(x,getCos(staObc , obcNow));
        }
        Set<HouseTable> ocv = houseMap.keySet();
        ocv.removeAll(root);
        class pair {
            public String houseId;
            public Double similar;
            public pair(String houseId,Double similar){
                this.houseId = houseId;
                this.similar = similar;
            }
        }
        List<pair> res = new ArrayList<>();
        for (HouseTable x:
             ocv) {
            //System.out.println(x.getHouseId() + " " +houseMap.get(x));
            res.add(new pair(x.getHouseId(),houseMap.get(x)));
        }
        res.sort(new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                return -o1.similar.compareTo(o2.similar);
            }
        });
        List<String> resString = new ArrayList<>();
        for (int i = 0 ; i < 3 ; i ++) {
            resString.add(res.get(i).houseId);
        }
        return resString;
    }



    private void recommendUserId(Integer userId) {
        System.err.println("recommendUserId");
        SubscribeTableExample subscribeTableExp = new SubscribeTableExample();
        subscribeTableExp.or().andUserIdEqualTo(userId);
        List<SubscribeTable> subscribeTableList =
                subscribeTableService.selectByExample(subscribeTableExp);
        /*
         * 用到的参数：
         * 0: houseArea
         * 1: housePrice
         * 2: buildTime
         * 3: houseFloor
         */
        List<String> houseIds = new ArrayList<>();
        for (SubscribeTable x:
             subscribeTableList) {
            houseIds.add(x.getHouseId());
        }
        HouseTableExample houseTableExp = new HouseTableExample();
        houseTableExp.or().andHouseIdIn(houseIds);
        List<HouseTable> houseTableList =
                houseTableService.selectByExample(houseTableExp);
        if (houseTableList.size() == 0) {
            stringRedisTemplate.opsForValue().set(userId.toString(), CONSTLIST.NONE);
        } else {
            Double[] obc = getObc(houseTableList);
            List<String> resList = getResHouseId(houseTableList,obc);
            String res = new Gson().toJson(resList,ArrayList.class);
            System.out.println(userId+" : "+res);
            stringRedisTemplate.opsForValue().set(userId.toString() , res);
        }
    }


    @Scheduled(cron = "*/20 * * * * ?")
    public void recommendAll() {
        System.err.println("recommendAll");
        houseTables = houseTableService.selectByExample(new HouseTableExample());
        for (HouseTable x:
             houseTables) {
            houseMap.put(x , 0.0);
        }
        Set<Integer> users = new HashSet<>();
        SubscribeTableExample subscribeTableExp = new SubscribeTableExample();
        List<SubscribeTable> subscribeTableList =
                subscribeTableService.selectByExample(subscribeTableExp);

        for (SubscribeTable x:
             subscribeTableList) {
            users.add(x.getUserId());
        }
        for (Integer x:
             users) {
            recommendUserId(x);
        }
    }
}
