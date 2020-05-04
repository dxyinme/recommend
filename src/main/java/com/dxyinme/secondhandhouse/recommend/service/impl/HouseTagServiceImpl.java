package com.dxyinme.secondhandhouse.recommend.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.dxyinme.secondhandhouse.recommend.model.HouseTagExample;
import java.util.List;
import com.dxyinme.secondhandhouse.recommend.mapper.HouseTagMapper;
import com.dxyinme.secondhandhouse.recommend.model.HouseTag;
import com.dxyinme.secondhandhouse.recommend.service.HouseTagService;
@Service
public class HouseTagServiceImpl implements HouseTagService{

    @Resource
    private HouseTagMapper houseTagMapper;

    @Override
    public long countByExample(HouseTagExample example) {
        return houseTagMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(HouseTagExample example) {
        return houseTagMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String houseId) {
        return houseTagMapper.deleteByPrimaryKey(houseId);
    }

    @Override
    public int insert(HouseTag record) {
        return houseTagMapper.insert(record);
    }

    @Override
    public int insertSelective(HouseTag record) {
        return houseTagMapper.insertSelective(record);
    }

    @Override
    public List<HouseTag> selectByExample(HouseTagExample example) {
        return houseTagMapper.selectByExample(example);
    }

    @Override
    public HouseTag selectByPrimaryKey(String houseId) {
        return houseTagMapper.selectByPrimaryKey(houseId);
    }

    @Override
    public int updateByExampleSelective(HouseTag record,HouseTagExample example) {
        return houseTagMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(HouseTag record,HouseTagExample example) {
        return houseTagMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(HouseTag record) {
        return houseTagMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(HouseTag record) {
        return houseTagMapper.updateByPrimaryKey(record);
    }

}
