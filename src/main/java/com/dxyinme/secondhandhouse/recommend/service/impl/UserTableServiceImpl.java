package com.dxyinme.secondhandhouse.recommend.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.dxyinme.secondhandhouse.recommend.model.UserTable;
import com.dxyinme.secondhandhouse.recommend.model.UserTableExample;
import com.dxyinme.secondhandhouse.recommend.mapper.UserTableMapper;
import com.dxyinme.secondhandhouse.recommend.service.UserTableService;
@Service
public class UserTableServiceImpl implements UserTableService{

    @Resource
    private UserTableMapper userTableMapper;

    @Override
    public long countByExample(UserTableExample example) {
        return userTableMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(UserTableExample example) {
        return userTableMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userTableMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserTable record) {
        return userTableMapper.insert(record);
    }

    @Override
    public int insertSelective(UserTable record) {
        return userTableMapper.insertSelective(record);
    }

    @Override
    public List<UserTable> selectByExample(UserTableExample example) {
        return userTableMapper.selectByExample(example);
    }

    @Override
    public UserTable selectByPrimaryKey(Integer id) {
        return userTableMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(UserTable record,UserTableExample example) {
        return userTableMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(UserTable record,UserTableExample example) {
        return userTableMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(UserTable record) {
        return userTableMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserTable record) {
        return userTableMapper.updateByPrimaryKey(record);
    }

}
