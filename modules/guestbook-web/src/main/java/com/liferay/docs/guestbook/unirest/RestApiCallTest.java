package com.liferay.docs.guestbook.unirest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONObject;

public class RestApiCallTest {

	public static void main(String[] args) throws UnirestException {
		RestApiCall restApiCall = new RestApiCall();

		HttpResponse<String> asJson = restApiCall.getAnypointAPIs();
		  System.out.println(asJson.getStatus());
		  System.out.println(asJson.getStatusText());
		  System.out.println(asJson.getBody());
		  JSONObject root = new JSONObject(asJson.getBody().toString());
		  JSONArray assets = root.getJSONArray("assets");
		  int length = assets.length();
		  Object object = assets.get(0);
		  JSONObject jsonObject = new JSONObject(assets.get(0).toString());
		  System.out.println(jsonObject.get("assetId"));
		
		/*
		 * HttpResponse<String> asJson = restApiCall.getCallAsString();
		 * System.out.println(asJson.getStatus());
		 * System.out.println(asJson.getStatusText());
		 * System.out.println(asJson.getBody());
		 */
		 

		/*
		 * HttpResponse<String> asJson =
		 * restApiCall.getCallAsStringWithURIParam("7878");
		 * System.out.println(asJson.getStatus());
		 * System.out.println(asJson.getStatusText());
		 * System.out.println(asJson.getBody());
		 */

			/*
			 * HttpResponse<String> postResponse = restApiCall.postRequest();
			 * System.out.println(postResponse.getStatus());
			 * System.out.println(postResponse.getStatusText());
			 * System.out.println(postResponse.getBody());
			 */

	}

}
