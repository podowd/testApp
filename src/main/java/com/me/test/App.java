package com.me.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class App 
{
	private static final String CLIENT_URL = "https://maps.googleapis.com";	
	private static final String OP_PATH = "maps/api/timezone/json";
	private static final String API_KEY = "<your google api key>";
	
	public static void main(String[] args) throws ParseException {
		Client client = ClientBuilder.newClient(new ClientConfig());

		JSONObject responseObj = (JSONObject) new JSONParser().parse(
				client.target(CLIENT_URL)
		        	.path(OP_PATH)
		        	.queryParam("key", API_KEY)
		        	.queryParam("location", "53.347778,-6.259722")
		        	.queryParam("sensor", false)
		        	.queryParam("timestamp", "6331161200")
		        	.request(MediaType.APPLICATION_JSON)
		        	.get(String.class)
				);
		
		if (responseObj.get("status").equals("OK")) {
			printResponse(responseObj);
		}
		else {
			System.out.println("Request status: " + responseObj.get("status"));			
		}
	}

	private static void printResponse(JSONObject responseObj) {
		System.out.println("Server Response:");
		System.out.println("Timezone Id: " + responseObj.get("timeZoneId"));
		System.out.println("Timezone Name: " + responseObj.get("timeZoneName"));
		System.out.println("Daylight Savings Time Offset: " + responseObj.get("dstOffset"));
		System.out.println("Raw Offset: " + responseObj.get("rawOffset"));
	}
 }