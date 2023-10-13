package com.mmi.fullstack.daolayer;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.mmi.fullstack.model.register;
import com.mmi.fullstack.model.user;

public interface dao {

	List<Map<String, Object>> getUser() throws Exception;

	List<Map<String, Object>> addUser(user data);

	List<Map<String, Object>> editUser(user data);

	Map<String, Object> register(register data);

	Map<String, Object> login(String email, String password);

}
