package com.mairiya.JaxRsTest.biz.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mairiya.JaxRsTest.biz.dao.UsersDao;
import com.mairiya.JaxRsTest.biz.domain.Users;

@Repository
public class UsersDaoImpl implements UsersDao {
    @Autowired
    private SqlSession session;
    
	@Override
	public Users findById(int id) {
		return session.selectOne("com.mairiya.JaxRsTest.dao.UsersMapper.selectUser", id);
	}

}
