package com.afour.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

@Path("/hello")
public class Server {
	@Path("v1")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}
	@Path("v2")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String connectToMongo() {
		try{

			// To connect to mongodb server
			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

			// Now connect to your databases
			@SuppressWarnings("deprecation")
			DB db = mongoClient.getDB( "test" );
			System.out.println("Connect to database successfully");
			//boolean auth = db.authenticate(myUserName, myPassword);
			//System.out.println("Authentication: "+auth);

			DBCollection coll = db.getCollection("firstCollection");
			System.out.println("Collection firstCollection  selected successfully");

			DBCursor cursor = coll.find();

			while (cursor.hasNext()) { 
				System.out.println(cursor.next().getClass());
			}

		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}

		return "hello";
	}
}
