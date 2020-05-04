package com.dxyinme.secondhandhouse.recommend.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.dxyinme.secondhandhouse.recommend.model.SubscribeTableExample;
import java.util.List;
import com.dxyinme.secondhandhouse.recommend.model.SubscribeTable;
import com.dxyinme.secondhandhouse.recommend.mapper.SubscribeTableMapper;
import com.dxyinme.secondhandhouse.recommend.service.SubscribeTableService;
@Service
public class SubscribeTableServiceImpl implements SubscribeTableService{

    @Resource
    private SubscribeTableMapper subscribeTableMapper;

    @Override
    public long countByExample(SubscribeTableExample example) {
        return subscribeTableMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(SubscribeTableExample example) {
        return subscribeTableMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String subscribeId) {
        return subscribeTableMapper.deleteByPrimaryKey(subscribeId);
    }

    @Override
    public int insert(SubscribeTable record) {
        return subscribeTableMapper.insert(record);
    }

    @Override
    public int insertSelective(SubscribeTable record) {
        return subscribeTableMapper.insertSelective(record);
    }

    @Override
    public List<SubscribeTable> selectByExample(SubscribeTableExample example) {
        return subscribeTableMapper.selectByExample(example);
    }

    @Override
    public SubscribeTable selectByPrimaryKey(String subscribeId) {
        return subscribeTableMapper.selectByPrimaryKey(subscribeId);
    }

    @Override
    public int updateByExampleSelective(SubscribeTable record,SubscribeTableExample example) {
        return subscribeTableMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(SubscribeTable record,SubscribeTableExample example) {
        return subscribeTableMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(SubscribeTable record) {
        return subscribeTableMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SubscribeTable record) {
        return subscribeTableMapper.updateByPrimaryKey(record);
    }

}
