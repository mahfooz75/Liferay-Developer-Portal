/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.docs.guestbook.portlet.portlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.docs.guestbook.model.Result;
import com.liferay.docs.guestbook.portlet.constants.GuestbookPortletKeys;
import com.liferay.docs.guestbook.unirest.RestApiCall;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.mashape.unirest.http.HttpResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK,
			"mvc.command.name=list_api",
		},
		service = MVCActionCommand.class
	)
public class ListAPIs extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		RestApiCall restApiCall = new RestApiCall();
		HttpResponse<String> asJson = restApiCall.getAnypointAPIs();
		/*
		 * System.out.println(asJson.getStatus());
		 * System.out.println(asJson.getStatusText());
		 * System.out.println(asJson.getBody());
		 */
		JSONObject root = new JSONObject(asJson.getBody().toString());
		JSONArray assets = root.getJSONArray("assets");
		List<Result> results = new ArrayList<Result>();
		for(Object obj:assets) {
			Result result=readJson(obj.toString());
			/*
			 * JSONObject jsonObject = new JSONObject(obj.toString()); Result result = new
			 * Result(); result.setAssetId((String)jsonObject.get("assetId"));
			 * result.setGroupId((String)jsonObject.get("groupId"));
			 * result.setId((Integer)jsonObject.get("id"));
			 * result.setExchangeAssetName((String)jsonObject.get("exchangeAssetName"));
			 */
			results.add(result);
		}
		
		//Object object = assets.get(0);
		//JSONObject jsonObject = new JSONObject(assets.get(0).toString());
		//String id = (String) jsonObject.get("assetId");
		//System.out.println(id);		
		//actionRequest.setAttribute("id", id);
		//List<String> names = new ArrayList<>();
		//names.add("Mahfooz");
		//names.add("Deepak");
		//names.add("Satya");
		actionRequest.setAttribute("results", results);
		//actionResponse.setRenderParameter("mvcPath", "/list.jsp");
	}

	private Result readJson(String asJson) throws JsonMappingException, JsonProcessingException {
		
		JsonNode assetsNode = new ObjectMapper().readTree(asJson);
		
		Result result = new Result();
		String assetId = assetsNode.get("assetId").textValue();
		result.setAssetId(assetId);
		
		String groupId = assetsNode.get("groupId").textValue();
		result.setGroupId(groupId);
		
		int id = assetsNode.get("id").intValue();
		result.setId(id);
		
		String exchangeAssetName=assetsNode.get("exchangeAssetName").textValue();
		result.setExchangeAssetName(exchangeAssetName);
		
		boolean array = assetsNode.get("apis").isArray();
		if(array && !assetsNode.get("apis").isEmpty()) {
			JsonNode apiNodes = assetsNode.get("apis");
			
			JsonNode apiNode = apiNodes.get(0);
			
			String instanceLabel=apiNode.get("instanceLabel").textValue();
			result.setInstanceLabel(instanceLabel);
			
			int apiId=apiNode.get("id").intValue();
			result.setApiId(apiId);
		}
		return result;
	}

	private void getFieldsAndValue(JsonNode apiNodes) {
		Iterator<Entry<String, JsonNode>> fields = apiNodes.get(0).fields();
		while(fields.hasNext()) {
			Entry<String, JsonNode> next = fields.next();
			//System.out.println(next.getKey()+" >> "+next.getValue());
			//setValue(next.getKey(),next.getValue());
		}
	}

	private void setValue(String key, JsonNode value) {
		if("id".equalsIgnoreCase(key)) {
			System.out.println("apiId >> "+value.intValue());
		}
		else if("instanceLabel".equalsIgnoreCase(key)) {
			System.out.println("instanceLabel >> "+value.asText());
		}
	}
	
}