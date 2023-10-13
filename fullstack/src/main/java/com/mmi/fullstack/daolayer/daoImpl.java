package com.mmi.fullstack.daolayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mmi.fullstack.model.user;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

@Component
public class daoImpl implements dao {

	@Autowired
	Environment env;

	@Autowired
	@Qualifier("teeDBTemplate")
	MongoTemplate teeDBTemplate;
	
	@Autowired
	@Qualifier("otherDBTemplate")
	MongoTemplate otherDBTemplate;

	@SuppressWarnings("unused")
	@Override
	public List<Map<String, Object>> getUser() throws Exception {
		MongoCollection<Document> coll = teeDBTemplate.getCollection("fullstack");
		List<Bson> pipeline = new ArrayList<>();

		List<Map<String, Object>> data = coll.find().into(new LinkedList<Map<String, Object>>());
		return data;
	}

	@Override
	public List<Map<String, Object>> addUser(user data) {

		MongoCollection<Document> coll = teeDBTemplate.getCollection("fullstack");

		Document doc = new Document();
		doc.append("name", data.getName()).append("username", data.getUsername()).append("email", data.getEmail());

		coll.insertOne(doc);

		return null;
	}

	@Override
	public List<Map<String, Object>> editUser(user data) {
		MongoCollection<Document> coll = teeDBTemplate.getCollection("fullstack");

		Document doc = new Document();
		doc.append("name", data.getName()).append("username", data.getUsername()).append("email", data.getEmail());

		coll.updateOne(Filters.eq("username", data.getUsername()), new BasicDBObject("$set", doc));
		return null;

	}

	@Override
	public Map<String, Object> register(com.mmi.fullstack.model.register data) {
		// TODO Auto-generated method stub
		MongoCollection<Document> coll = teeDBTemplate.getCollection("reactColl");

		Document doc = new Document();
		doc.append("name", data.getName()).append("password", data.getPassword()).append("email", data.getEmail());

		coll.insertOne(doc);
		return null;
	}

	@Override
	public Map<String, Object> login(String email, String password) {
		MongoCollection<Document> coll = teeDBTemplate.getCollection("reactColl");
		 
		Document doc = new Document();
		doc.put("email", email);
		doc.put("password", password);
		Document doc1 = coll.find(doc).first();
		
		return doc1;
		
	}

}
