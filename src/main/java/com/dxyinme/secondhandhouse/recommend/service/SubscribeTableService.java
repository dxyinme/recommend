package com.dxyinme.secondhandhouse.recommend.service;

import com.dxyinme.secondhandhouse.recommend.model.SubscribeTableExample;
import java.util.List;
import com.dxyinme.secondhandhouse.recommend.model.SubscribeTable;
public interface SubscribeTableService{


    long countByExample(SubscribeTableExample example);

    int deleteByExample(SubscribeTableExample example);

    int deleteByPrimaryKey(String subscribeId);

    int insert(SubscribeTable record);

    int insertSelective(SubscribeTable record);

    List<SubscribeTable> selectByExample(SubscribeTableExample example);

    SubscribeTable selectByPrimaryKey(String subscribeId);

    int updateByExampleSelective(SubscribeTable record,SubscribeTableExample example);

    int updateByExample(SubscribeTable record,SubscribeTableExample example);

    int updateByPrimaryKeySelective(SubscribeTable record);

    int updateByPrimaryKey(SubscribeTable record);

}
