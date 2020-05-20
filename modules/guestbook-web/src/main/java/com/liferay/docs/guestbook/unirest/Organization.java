package com.liferay.docs.guestbook.unirest;

import com.liferay.docs.guestbook.unirest.config.UnirestConfiguration;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONObject;

public class Organization {

	private static final String URL="https://anypoint.mulesoft.com/accounts/api/me";
	
	public String getOrganizationId(String token) throws UnirestException {
		UnirestConfiguration.configuration(token);
		HttpResponse<String> response = Unirest.get(URL).asString();
		JSONObject rootObject = new JSONObject(response.getBody().toString());
		JSONObject userObject=new JSONObject(rootObject.get("user").toString());
		String orgId = userObject.getString("organizationId");
		return orgId;
	}
}
