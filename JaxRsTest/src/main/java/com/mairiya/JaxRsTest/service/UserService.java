package com.mairiya.JaxRsTest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;

import com.mairiya.JaxRsTest.biz.dao.UsersDao;

@Path("/user")
public class UserService {
	
	@Autowired
	private UsersDao usersDao;
	
	@GET
	@Path("/{id}")
	public String getUserInfo(@PathParam("id") int id) {
		return usersDao.findById(id).getName();
	}
	
}
