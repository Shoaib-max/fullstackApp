package com.mmi.fullstack.Controller;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mmi.fullstack.Service.service;
import com.mmi.fullstack.model.user;

@RestController
@CrossOrigin
public class controlls {
	
	@Autowired
	service serv;
	
	@GetMapping("/test")
	public String show() {
		return "testing";
	}
	
	@RequestMapping("/getUser")
	public List<Map<String,Object>> getUser() throws Exception{
		
		 List<Map<String,Object>> data = serv.getUser();
		 return data;
	
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public List<Map<String,Object>> addUser(@RequestBody user data) throws Exception{
		
		 List<Map<String,Object>> details = serv.addUser(data);
		 return details;
	
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.PUT)
	public List<Map<String,Object>> editUser(@RequestBody user data) throws Exception{
		
		 List<Map<String,Object>> details = serv.editUser(data);
		 return details;
	
	}
	
	@PostMapping(value = "/register")
	public Map<String,Object> register(@RequestBody com.mmi.fullstack.model.register data) throws Exception{
		
		 Map<String,Object> details = serv.register(data);
		 return details;
	
	}
	
	@PostMapping(value = "/login")
	public Map<String,Object> login(@RequestParam String email,@RequestParam String password) throws Exception{
		
		 Map<String,Object> details = serv.login(email,password);
		 return details;
	
	}

}
