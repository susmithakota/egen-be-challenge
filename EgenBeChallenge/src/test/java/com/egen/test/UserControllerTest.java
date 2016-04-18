package com.egen.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import spark.utils.IOUtils;

import com.egen.model.Address;
import com.egen.model.Company;
import com.egen.model.User;
import com.egen.util.JacksonUtil;

public class UserControllerTest {

	private static String id ;
	private String lastName = "Dorris";
	
	
	//Calling POST method
	@Test
	public void addNewUser() {
        
		User reqbody = new User();
		reqbody.setFirstName("Dorris");
		reqbody.setDateCreated("2016-03-15T07:02:40.896Z");
		reqbody.setProfilePic("http://lorempixel.com/640/480/people");
		reqbody.setLastName("Keeling");
		Address address = new Address();
		
		address.setStreet("193 Talon Valley");
		address.setCity("South Tate furt");		
		address.setState("47069");
		address.setZip("IA");
		address.setCountry("US");		
		reqbody.setAddress(address);
		
		Company company = new Company();
		company.setName("Denesik Group");
		company.setWebsite("http://jodie.org");
		reqbody.setCompany(company);
		reqbody.setEmail("Darby_Leffler68@gmail.com");
		
		
		String  res = request("POST", "/user", reqbody);
        User responseUser = JacksonUtil.jsonToObject(res);
		assertNotNull(responseUser.getId());
		id = responseUser.getId();
	}
	
	//Calling PUT method
	@Test
	public void updateUser() {
		
		User reqbody = new User();
		reqbody.setId(id);
		reqbody.setFirstName("Dorris");
		reqbody.setDateCreated("2016-03-15T07:02:40.896Z");
		reqbody.setProfilePic("http://lorempixel.com/640/480/people");
		reqbody.setLastName(lastName);
		Address address = new Address();
		
		address.setStreet("193 Talon Valley");
		address.setCity("South Tate furt");		
		address.setState("47069");
		address.setZip("IA");
		address.setCountry("US");		
		reqbody.setAddress(address);
		
		Company company = new Company();
		company.setName("Denesik Group");
		company.setWebsite("http://jodie.org");
		reqbody.setCompany(company);
		reqbody.setEmail("Darby_Leffler68@gmail.com");
		String result = request("PUT","/user",reqbody);
	
		assertEquals("User updated successfully", result);
	}

	
	private String request(String method, String path,User user) {
		try {
			URL url = new URL("http://localhost:4567" + path);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod(method);
			connection.setDoOutput(true);
			connection.connect();
			if(method != null && (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")))
			{
			OutputStream os = connection.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			osw.write(JacksonUtil.objectToJson(user));
			osw.flush();
			osw.close();
			}
			String body = IOUtils.toString(connection.getInputStream());
			return body;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Sending request failed: " + e.getMessage());
			return null;
		}
	}

}
