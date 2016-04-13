package com.afour.tad.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.afour.server.Server;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

public class MongoDBConnection {
	private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);
	private static Datastore datastore = null;
	private static Mongo mongo = null;
	private static Morphia morphia = null;
	private static String dbName = null;
	private static String dbHost = null;
	private static int dbPort ;
	
	
	static{
		try{
			LOGGER.info("--> Trying to load mongo db properties");
			InputStream inputFile = MongoDBConnection.class.getResourceAsStream("/mongoDB.properties");
			Properties properties = new Properties();
			properties.load(inputFile);
			dbName = (String) properties.get("database.name");
			dbHost = (String) properties.get("database.host");
			dbPort = Integer.parseInt(properties.get("database.port").toString());
			
		}catch(IOException e){
			LOGGER.warn("--> Unable to connect to Mongo server because : " + e.getMessage());
		}
	}
	@SuppressWarnings("deprecation")
	public static Datastore  getDatastore() {
		if(datastore == null){
			LOGGER.info("--> Trying to connect to mongo db on : " + dbHost);
			mongo= new Mongo(dbHost,dbPort);
		     morphia = new Morphia();
		     morphia.mapPackage("com.afour.tad.pojo");
		     datastore = morphia.createDatastore(mongo,dbName); 
		     LOGGER.info("--> Connected to mongo db : " + dbHost);
		     return datastore;
		}else{ 
			return datastore;
		}
	}

}

