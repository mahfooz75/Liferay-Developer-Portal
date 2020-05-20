package com.liferay.docs.guestbook.unirest.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;

public class UnirestConfiguration {
	public static void configuration() {
		Unirest.setDefaultHeader("accept", "application/json");
		Unirest.setDefaultHeader("Content-Type", "application/json");
		Unirest.setObjectMapper(new MyObjectMapper());
	}
	
	public static void configuration(String authToken) {
		String token="Bearer "+authToken;
		Unirest.setDefaultHeader("Authorization",token);
		configuration();
	}
}

class MyObjectMapper implements com.mashape.unirest.http.ObjectMapper {
	ObjectMapper mapper = new ObjectMapper();

	@Override
	public <T> T readValue(String value, Class<T> valueType) {
		try {
			return mapper.readValue(value, valueType);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String writeValue(Object value) {
		try {
			return mapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
