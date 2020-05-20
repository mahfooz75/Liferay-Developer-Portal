package com.liferay.docs.guestbook.unirest;

import com.liferay.docs.guestbook.model.LoginRequest;
import com.liferay.docs.guestbook.model.Order;
import com.liferay.docs.guestbook.unirest.config.UnirestConfiguration;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONObject;

public class RestApiCall {
	
	public HttpResponse<String> getAnypointAPIs() throws UnirestException{
		
		String token=getLoginToken("iris_user", "Password@1");
		UnirestConfiguration.configuration(token);
		
		Organization organization = new Organization();
		String organizationId=organization.getOrganizationId(token);
		
		Environment env = new Environment();
		String environmentId=env.getEnvironmentId(organizationId, token);
		//String getUrl="https://anypoint.mulesoft.com/apimanager/api/v1/organizations/"+organizationId+"/environments/a6f6c893-4bad-420a-a90d-44f2b53602c0/apis";
		//String getUrl="https://anypoint.mulesoft.com/apimanager/api/v1/organizations/594e0836-a1a1-42da-98ee-dd8195e6f10a/environments/a6f6c893-4bad-420a-a90d-44f2b53602c0/apis";
		String getUrl="https://anypoint.mulesoft.com/apimanager/api/v1/organizations/"+organizationId+"/environments/"+environmentId+"/apis";
		HttpResponse<String> response = Unirest
				.get(getUrl)
				.asString();
		return response;
	}

	public HttpResponse<JsonNode> getCall() throws UnirestException {
		UnirestConfiguration.configuration();
		HttpResponse<JsonNode> response = Unirest
				.get("http://create-order-api-with-oauth.us-e2.cloudhub.io/api/get-orders").asJson();
		return response;
	}

	public HttpResponse<String> getCallAsString() throws UnirestException {
		UnirestConfiguration.configuration();
		HttpResponse<String> response = Unirest
				.get("http://create-order-api-with-oauth.us-e2.cloudhub.io/api/get-orders").asString();
		return response;
	}
	
	public HttpResponse<String> getCallAsStringWithoutToken() throws UnirestException {
		UnirestConfiguration.configuration();
		HttpResponse<String> response = Unirest
				.get("https://my-create-order-api-with-https.us-e2.cloudhub.io/v1/get-orders").asString();
		return response;
	}
	
	public HttpResponse<String> getCallAsStringWithURIParam(String orderId) throws UnirestException {
		UnirestConfiguration.configuration();
		HttpResponse<String> response = Unirest
				.get("http://create-order-api-with-oauth.us-e2.cloudhub.io/api/get-order/{orderId}")
				.routeParam("orderId", orderId).asString();
		return response;
	}

	public HttpResponse<String> postRequest() throws UnirestException {
		UnirestConfiguration.configuration();
		Order order = new Order();
		order.setOrderId("9876");
		order.setCustomerName("Deepak Kumar");
		order.setCustomerAddress("Noida");
		order.setCustomerMobile("1234567890");

		/*
		 * HttpResponse<String> response = Unirest .post(
		 * "http://create-order-api-with-oauth.us-e2.cloudhub.io/api/create-order")
		 * .body("{\"orderId\": \"1234\",\r\n" +
		 * "    \"customerName\": \"Mahfooz\",\r\n" +
		 * "    \"customerAddress\": \"Noida\",\r\n" +
		 * "    \"customerMobile\": \"9717395464\"\r\n" + "  }") .asString();
		 */

		
		  HttpResponse<String> response = Unirest .post(
		  "http://create-order-api-with-oauth.us-e2.cloudhub.io/api/create-order")
		  .body(order) .asString();
		  
		return response;
	}
	
	private HttpResponse<String> login(String userName, String password) throws UnirestException {
		UnirestConfiguration.configuration();
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername(userName);
		loginRequest.setPassword(password);
		HttpResponse<String> response = Unirest.post( "https://anypoint.mulesoft.com/accounts/login").body(loginRequest).asString();
		return response;
	}
	
	public String getLoginToken(String userName, String password) throws UnirestException {
		HttpResponse<String> loginResponse = login(userName,password);
		JSONObject jsonObj=new JSONObject(loginResponse.getBody().toString());
		String token = (String)jsonObj.get("access_token");
		return token;
	}
	
}
