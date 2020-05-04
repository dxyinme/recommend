package com.dxyinme.secondhandhouse.recommend.service;

import com.dxyinme.secondhandhouse.recommend.model.HouseTable;
import java.util.List;
import com.dxyinme.secondhandhouse.recommend.model.HouseTableExample;
public interface HouseTableService{


    long countByExample(HouseTableExample example);

    int deleteByExample(HouseTableExample example);

    int deleteByPrimaryKey(String houseId);

    int insert(HouseTable record);

    int insertSelective(HouseTable record);

    List<HouseTable> selectByExample(HouseTableExample example);

    HouseTable selectByPrimaryKey(String houseId);

    int updateByExampleSelective(HouseTable record,HouseTableExample example);

    int updateByExample(HouseTable record,HouseTableExample example);

    int updateByPrimaryKeySelective(HouseTable record);

    int updateByPrimaryKey(HouseTable record);

}
