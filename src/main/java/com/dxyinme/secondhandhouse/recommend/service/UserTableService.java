package com.dxyinme.secondhandhouse.recommend.service;

import java.util.List;
import com.dxyinme.secondhandhouse.recommend.model.UserTable;
import com.dxyinme.secondhandhouse.recommend.model.UserTableExample;
public interface UserTableService{


    long countByExample(UserTableExample example);

    int deleteByExample(UserTableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserTable record);

    int insertSelective(UserTable record);

    List<UserTable> selectByExample(UserTableExample example);

    UserTable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(UserTable record,UserTableExample example);

    int updateByExample(UserTable record,UserTableExample example);

    int updateByPrimaryKeySelective(UserTable record);

    int updateByPrimaryKey(UserTable record);

}
