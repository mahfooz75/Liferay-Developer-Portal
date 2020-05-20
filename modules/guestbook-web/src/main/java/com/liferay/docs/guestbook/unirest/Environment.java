package com.liferay.docs.guestbook.unirest;

import com.liferay.docs.guestbook.unirest.config.UnirestConfiguration;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONObject;

public class Environment {

	private static final String URL="http://anypoint.mulesoft.com/accounts/api/organizations/";
	public String getEnvironmentId(String orgId,String token) throws UnirestException {
		String envURL=URL+orgId+"/environments";
		String environmentId="";
		
		UnirestConfiguration.configuration(token);
		HttpResponse<String> response = Unirest.get(envURL).asString();
		JSONObject rootObject = new JSONObject(response.getBody());
		JSONArray data = rootObject.getJSONArray("data");
		if(data!=null && data.length()!=0) {
			for(Object obj:data) {
				JSONObject jsonObject = new JSONObject(obj.toString());
				String type = jsonObject.getString("type");
				if("sandbox".equalsIgnoreCase(type)) {
					environmentId = jsonObject.getString("id");
					break;
				}
			}
		}
		return environmentId;
	}
	
}
