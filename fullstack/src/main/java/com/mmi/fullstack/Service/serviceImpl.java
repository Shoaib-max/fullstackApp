package com.mmi.fullstack.Service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmi.fullstack.daolayer.dao;
import com.mmi.fullstack.model.user;

@Service
public class serviceImpl implements service {
	
	@Autowired
	dao daolay;

	@Override
	public List<Map<String, Object>> getUser() throws Exception {
		
		return daolay.getUser();
	}

	@Override
	public List<Map<String, Object>> addUser(user data) {
		// TODO Auto-generated method stub
		return daolay.addUser(data);
	}

	@Override
	public List<Map<String, Object>> editUser(user data) {
		// TODO Auto-generated method stub
		return daolay.editUser(data);
	}

	@Override
	public Map<String, Object> register(com.mmi.fullstack.model.register data) {
		// TODO Auto-generated method stub
		return daolay.register(data);
	}

	@Override
	public Map<String, Object> login(String email, String password) {
		// TODO Auto-generated method stub
		return daolay.login(email,password);
	}

}
