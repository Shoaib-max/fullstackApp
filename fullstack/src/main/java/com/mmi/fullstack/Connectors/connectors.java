package com.mmi.fullstack.Connectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;

@Configuration
public class connectors {
	
	@Autowired
	Environment env;
	

	@Bean
	@Primary
	public SimpleMongoClientDatabaseFactory teeDBFactory() throws Exception {
		MongoClientSettings settings =
			MongoClientSettings.builder()
			.applyConnectionString(
				new ConnectionString(env.getProperty("spring.mongo.connections.tee-db.hostStr"))
			).build();
	    
	    return new SimpleMongoClientDatabaseFactory(MongoClients.create(settings),
	    		env.getProperty("spring.mongo.connections.tee-db.database"));
	}
		
	@Primary
	@Bean(name = "teeDBTemplate")
	public MongoTemplate teeDBTemplate() throws Exception {
		MappingMongoConverter converter = new MappingMongoConverter(
				new DefaultDbRefResolver(teeDBFactory()), new MongoMappingContext());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
			
	    return new MongoTemplate(teeDBFactory(), converter);
	}
	
	@Bean
	public SimpleMongoClientDatabaseFactory otherDBFactory() throws Exception {
		MongoClientSettings settings =
			MongoClientSettings.builder()
			.applyConnectionString(
				new ConnectionString(env.getProperty("spring.mongo.connections.tee-db.hostStr"))
			).build();
	    
	    return new SimpleMongoClientDatabaseFactory(MongoClients.create(settings),
	    		env.getProperty("spring.mongo.connections.tee-db.database"));
	}
		
	@Bean(name = "otherDBTemplate")
	public MongoTemplate otherDBTemplate() throws Exception {
		MappingMongoConverter converter = new MappingMongoConverter(
				new DefaultDbRefResolver(otherDBFactory()), new MongoMappingContext());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
			
	    return new MongoTemplate(otherDBFactory(), converter);
	}

}
