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

    @Scheduled(cron = "*/30 * * * * ?")
    public void refreshHurrySell() {
        System.err.println("refreshHurrySell");
        HouseTagExample houseTagExp = new HouseTagExample();
        hurrySell = houseTagService.selectByExample(houseTagExp);
//        stringRedisTemplate.opsForValue().set("1","1");
//        String s = stringRedisTemplate.opsForValue().get("1");
//        System.out.println(s);
    }


    private void recommendUserId(Integer userId) {
        System.err.println("recommendUserId");
        SubscribeTableExample subscribeTableExp = new SubscribeTableExample();
        subscribeTableExp.or().andUserIdEqualTo(userId);
        List<SubscribeTable> subscribeTableList = subscribeTableService.selectByExample(subscribeTableExp);
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
        Double[] obc = new Double[4];
        Long[] obcInt = new Long[4];
        Integer[] proc = new Integer[]{20 , 20 , 5 , 3};
        for (int i = 0 ; i < 4; i++) obc[i] = 0.0;
        if (houseTableList.size() == 0) {
            stringRedisTemplate.opsForValue().set(userId.toString(), CONSTLIST.NONE);
        } else {
            for (HouseTable x:
                 houseTableList) {
                obc[0] += 1.0 * x.getHouseArea();
                obc[1] += 1.0 * x.getHousePrice();
                obc[2] += Double.parseDouble(x.getBuildTime());
                obc[3] += 1.0 * x.getHouseFloor();
            }
            for(int i=0;i<4;i++) {
                obc[i] /= 1.0 * houseTableList.size();
                obcInt[i] = Math.round(obc[i]);
            }
            houseTableExp.clear();
            houseTableExp
                    .or().andHouseAreaBetween(obcInt[0].intValue() - proc[0] , obcInt[0].intValue() + proc[0])
                        .andHousePriceBetween(obcInt[1].intValue() - proc[1] , obcInt[1].intValue() + proc[1])
                        .andHouseIdNotIn(houseIds);
            houseTableExp
                    .or().andHouseFloorBetween(obcInt[3].intValue() - proc[3] , obcInt[3].intValue() + proc[3])
                        .andHousePriceBetween(obcInt[1].intValue() - proc[1] , obcInt[1].intValue() + proc[1])
                        .andHouseIdNotIn(houseIds);
            List<HouseTable> houseTables = houseTableService.selectByExample(houseTableExp);
            List<String> resList = new ArrayList<>();
            for (HouseTable x:
                 houseTables) {
                resList.add(x.getHouseId());
            }
            String res = new Gson().toJson(resList,ArrayList.class);
            System.out.println(res);
            stringRedisTemplate.opsForValue().set(userId.toString() , res);
        }
    }


    @Scheduled(cron = "*/20 * * * * ?")
    public void recommendAll() {
        System.err.println("recommendAll");
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
