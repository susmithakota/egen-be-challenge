package com.egen.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.egen.model.User;

public class JacksonUtil  {
	
	public static String objectToJson(User user)
	{
		ObjectMapper mapper = new ObjectMapper();		
		
		try {			
			//Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(user);
			
		    return jsonInString;	
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		   return null;

	}
	
	public static User jsonToObject(String jsonString)
	{
		ObjectMapper mapper = new ObjectMapper();

		try {

			// Convert JSON string to Object			
			User user1 = mapper.readValue(jsonString, User.class);
			
			return user1;

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;

	}
	
	

}
