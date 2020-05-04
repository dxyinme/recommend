package com.dxyinme.secondhandhouse.recommend.service;

import com.dxyinme.secondhandhouse.recommend.model.HouseTagExample;
import java.util.List;
import com.dxyinme.secondhandhouse.recommend.model.HouseTag;
public interface HouseTagService{


    long countByExample(HouseTagExample example);

    int deleteByExample(HouseTagExample example);

    int deleteByPrimaryKey(String houseId);

    int insert(HouseTag record);

    int insertSelective(HouseTag record);

    List<HouseTag> selectByExample(HouseTagExample example);

    HouseTag selectByPrimaryKey(String houseId);

    int updateByExampleSelective(HouseTag record,HouseTagExample example);

    int updateByExample(HouseTag record,HouseTagExample example);

    int updateByPrimaryKeySelective(HouseTag record);

    int updateByPrimaryKey(HouseTag record);

}
