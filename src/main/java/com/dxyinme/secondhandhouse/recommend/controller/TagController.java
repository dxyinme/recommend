package com.dxyinme.secondhandhouse.recommend.controller;

import com.dxyinme.secondhandhouse.recommend.CONSTLIST.CONSTLIST;
import com.dxyinme.secondhandhouse.recommend.model.HouseTag;
import com.dxyinme.secondhandhouse.recommend.service.HouseTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"Tag"})
@RestController
public class TagController {
    @Autowired
    HouseTagService houseTagService;

    @ApiOperation(value = "设置标记" , response = String.class)
    @PostMapping("/setHurrySell")
    public String SetHurrySell(String houseId) {
        houseTagService.insert(new HouseTag(houseId, CONSTLIST.hurrySell));
        return "OK";
    }

    @ApiOperation(value = "删除标记" , response = String.class)
    @PostMapping("/deleteHurrySell")
    public String deleteHurrySell(String houseId) {
        houseTagService.deleteByPrimaryKey(houseId);
        return "OK";
    }
}
