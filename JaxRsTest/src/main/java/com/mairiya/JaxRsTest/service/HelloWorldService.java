package com.mairiya.JaxRsTest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("helloworld")
public class HelloWorldService {

	@GET
	public String helloWorld() {
		return "Hello World-modified!";
	}
	
}
