package com.dxyinme.secondhandhouse.recommend.mapper;

import com.dxyinme.secondhandhouse.recommend.model.HouseTag;
import com.dxyinme.secondhandhouse.recommend.model.HouseTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HouseTagMapper {
    long countByExample(HouseTagExample example);

    int deleteByExample(HouseTagExample example);

    int deleteByPrimaryKey(String houseId);

    int insert(HouseTag record);

    int insertSelective(HouseTag record);

    List<HouseTag> selectByExample(HouseTagExample example);

    HouseTag selectByPrimaryKey(String houseId);

    int updateByExampleSelective(@Param("record") HouseTag record, @Param("example") HouseTagExample example);

    int updateByExample(@Param("record") HouseTag record, @Param("example") HouseTagExample example);

    int updateByPrimaryKeySelective(HouseTag record);

    int updateByPrimaryKey(HouseTag record);
}