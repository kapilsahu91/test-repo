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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.afour.tad.pojo.Channel;
import com.afour.tad.pojo.Sensor;
import com.afour.tad.pojo.SensorNew;
import com.afour.tad.utils.MongoDBConnection;
import com.google.code.morphia.Key;
import com.google.code.morphia.query.QueryResults;
import com.google.gson.Gson;

@Path("/mongo")
public class Server {
	private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);
	
	@Path("v1")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String sayPlainTextHello(Sensor sensor) {
		return "Hello Jersey";
	}

	@Path("insertSensor")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public String insertToMongoNew(SensorNew sensor) {
		try{
		     Key<SensorNew> savedSensor = MongoDBConnection.getDatastore().save(sensor);   
		     LOGGER.info("--> Inserted Sensor with Id : " + savedSensor.getId() +
		    		 	" and channelId/entryId : " + sensor.getChannel_id() +
		    		 	"/" + sensor.getEntry_id());

		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return "Sensor's data saved to DB";
	}
	
	@Path("insertChannel")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public String insertChannelToMongoNew(Channel channel) {
		try{
			//LOGGER.info("--> Insert Channel with Id : " + channel.getId());
		     Key<Channel> savedSensor = MongoDBConnection.getDatastore().save(channel);   
		     LOGGER.info("--> Inserted Channel with Id : " + savedSensor.getId());


		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return "Channel's data saved to DB";
	}
	
	@Path("showdata/{channelId}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@SuppressWarnings("unchecked")
	public JSONObject showDataMongoNew(@PathParam("channelId") String channelId) {
		LOGGER.info("--> Show All data with channelId : " + channelId);
		Gson gson = new Gson();
		JSONObject data=null;
		try{
		     QueryResults<SensorNew> retrievedSensors = 
		    		 MongoDBConnection.getDatastore().find(SensorNew.class).filter("channel_id", channelId);
		     //Query q = datastore.createQuery(Channel.class).field("").equal(arg0)
		     //datastore.findAndModify(q, , arg2, arg3)
		     data = new JSONObject();
		     JSONArray feeds = new JSONArray();
		     for (SensorNew sensor : retrievedSensors) {		    	 
		    	 feeds.add((JSONObject)new JSONParser().parse(gson.toJson(sensor)));
		     }
		     data.put("feeds", feeds);
		     
		     QueryResults<Channel> retrievedChannel = 
		    		 MongoDBConnection.getDatastore().find(Channel.class).filter("_id", channelId);
		     for (Channel channel : retrievedChannel) {
				data.put("channel", (JSONObject)new JSONParser().parse(gson.toJson(channel)));
			}
		     
		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return data;
	}
}
