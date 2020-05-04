package com.dxyinme.secondhandhouse.recommend.mapper;

import com.dxyinme.secondhandhouse.recommend.model.SubscribeTable;
import com.dxyinme.secondhandhouse.recommend.model.SubscribeTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SubscribeTableMapper {
    long countByExample(SubscribeTableExample example);

    int deleteByExample(SubscribeTableExample example);

    int deleteByPrimaryKey(String subscribeId);

    int insert(SubscribeTable record);

    int insertSelective(SubscribeTable record);

    List<SubscribeTable> selectByExample(SubscribeTableExample example);

    SubscribeTable selectByPrimaryKey(String subscribeId);

    int updateByExampleSelective(@Param("record") SubscribeTable record, @Param("example") SubscribeTableExample example);

    int updateByExample(@Param("record") SubscribeTable record, @Param("example") SubscribeTableExample example);

    int updateByPrimaryKeySelective(SubscribeTable record);

    int updateByPrimaryKey(SubscribeTable record);
}