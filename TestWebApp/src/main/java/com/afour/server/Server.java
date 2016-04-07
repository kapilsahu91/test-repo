package com.afour.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import com.afour.tad.pojo.Channel;
import com.afour.tad.pojo.Sensor;
import com.afour.tad.pojo.SensorNew;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.QueryResults;
import com.google.gson.Gson;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Path("/mongo")
public class Server {
	@Path("v1")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String sayPlainTextHello(Sensor sensor) {
		return "Hello Jersey";
	}
	@Path("insert")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public String insertToMongo(Sensor sensor) {
		MongoClient mongoClient= null;
		try{

			// To connect to mongodb server
			 mongoClient= new MongoClient( "localhost" , 27017 );
			 System.out.println("here are the fetched values : " + sensor.getDevice_id());
			 
			 String dbName = new String("TAD");
		     Mongo mongo = new Mongo();
		     Morphia morphia = new Morphia();
		     Datastore datastore = morphia.createDatastore(mongo, dbName); 
		     morphia.mapPackage("com.afour.tad.pojo");
		     
		     Key<Sensor> savedSensor = datastore.save(sensor);   
		     System.out.println(savedSensor.getId());
	/*		 
			 try{
				conn = MySQLConnection.getConnection();
				stmt = conn.prepareStatement("insert into sensor values(?,?)");
				stmt.setString(1,sensor.getSensor_id());
				stmt.setString(2,sensor.getSensor_data());
				stmt.execute();
					//conn.close();
			  }catch(SQLException  ex){
				  ex.printStackTrace();
			  }

			// Now connect to your databases
			@SuppressWarnings("deprecation")
			DB db = mongoClient.getDB( "TAD" );
			System.out.println("Connect to database successfully");
			//boolean auth = db.authenticate(myUserName, myPassword);
			//System.out.println("Authentication: "+auth);

			DBCollection coll = db.getCollection("sensor");
			System.out.println("Collection firstCollection  selected successfully");

			DBCursor cursor = coll.find();

			while (cursor.hasNext()) { 
				System.out.println(cursor.next().getClass());
			}*/

		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}finally{
			mongoClient.close();
		}

		return "hello this is the change.";
	}
	
	@Path("insertnew")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public String insertToMongoNew(SensorNew sensor) {
		MongoClient mongoClient= null;
		try{
			System.out.println("ulalalale ooooo");
			// To connect to mongodb server
			 mongoClient= new MongoClient( "localhost" , 27017 );
			 System.out.println("here are the fetched valuesasdfasdfdasasdfasd : " + sensor.getChannel_id());
			 System.out.println("filed2:" + sensor.getField2());
			 String dbName = new String("TAD");
		     Mongo mongo = new Mongo();
		     Morphia morphia = new Morphia();
		     Datastore datastore = morphia.createDatastore(mongo, dbName); 
		     morphia.mapPackage("com.afour.tad.pojo");
		     
		     Key<SensorNew> savedSensor = datastore.save(sensor);   
		     System.out.println(savedSensor.getId());

		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}finally{
			mongoClient.close();
		}

		return "hello this is the change.";
	}
	
	@Path("insertchannel")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public String insertChannelToMongoNew(Channel channel) {
		MongoClient mongoClient= null;
		try{
			System.out.println("ulalalale ooooo insert channel");
			// To connect to mongodb server
			 mongoClient= new MongoClient( "localhost" , 27017 );
			 //System.out.println("here are the fetched valuesasdfasdfdasasdfasd : " + sensor.getChannel_id());
			 
			 String dbName = new String("TAD");
		     Mongo mongo = new Mongo();
		     Morphia morphia = new Morphia();
		     Datastore datastore = morphia.createDatastore(mongo, dbName); 
		     morphia.mapPackage("com.afour.tad.pojo");
		     
		     Key<Channel> savedSensor = datastore.save(channel);   
		     System.out.println(savedSensor.getId());

		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}finally{
			mongoClient.close();
		
		}

		return "hello this is the change.";
	}
	@Path("showdata/{channelId}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject showDataMongoNew(@PathParam("channelId") String channelId) {
		Gson gson = new Gson();
		MongoClient mongoClient= null;
		JSONObject obj = null;
		JSONObject data=null;
		try{
			
			System.out.println("ulalalale ooooo");
			// To connect to mongodb server
			 mongoClient= new MongoClient( "localhost" , 27017 );
			 String dbName = new String("TAD");
		     Mongo mongo = new Mongo();
		     Morphia morphia = new Morphia();
		     Datastore datastore = morphia.createDatastore(mongo, dbName); 
		     morphia.mapPackage("com.afour.tad.pojo");
		     
		     QueryResults<SensorNew> retrievedSensors = 
		    		 datastore.find(SensorNew.class).filter("channel_id", channelId);
		     //Query q = datastore.createQuery(Channel.class).field("").equal(arg0)
		     //datastore.findAndModify(q, , arg2, arg3)
		     data = new JSONObject();
		     JSONArray feeds = new JSONArray();
		     for (SensorNew sensor : retrievedSensors) {		    	 
		    	 feeds.add((JSONObject)new JSONParser().parse(gson.toJson(sensor)));
		    	 System.out.println("asasdfs"+gson.toJson(sensor));
		     }
		     data.put("feeds", feeds);
		     
		     QueryResults<Channel> retrievedChannel = 
		    		 datastore.find(Channel.class).filter("_id", channelId);
		     for (Channel channel : retrievedChannel) {
				data.put("channel", (JSONObject)new JSONParser().parse(gson.toJson(channel)));
			}
		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}finally{
			mongoClient.close();
		}

		return data;
	}
}
