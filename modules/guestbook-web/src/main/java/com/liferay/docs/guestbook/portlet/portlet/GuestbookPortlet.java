package com.liferay.docs.guestbook.portlet.portlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.docs.guestbook.model.Result;
import com.liferay.docs.guestbook.portlet.constants.GuestbookPortletKeys;
import com.liferay.docs.guestbook.unirest.RestApiCall;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

/**
 * @author mahfo
 */
@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=GUESTBOOK",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/view.jsp",
			"javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user",
			"com.liferay.portlet.single-page-application=false"
		},
		service = Portlet.class
	)
public class GuestbookPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		List<Result> results = null;
		try {
			results = doProcess();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		renderRequest.setAttribute("results", results);
		super.doView(renderRequest, renderResponse);
	}

	public List<Result> doProcess() throws UnirestException, JsonMappingException, JsonProcessingException {
		RestApiCall restApiCall = new RestApiCall();
		HttpResponse<String> asJson = restApiCall.getAnypointAPIs();
		JSONObject root = new JSONObject(asJson.getBody().toString());
		JSONArray assets = root.getJSONArray("assets");
		List<Result> results = new ArrayList<Result>();
		for (Object obj : assets) {
			Result result = readJson(obj.toString());
			results.add(result);
		}
		return results;
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

		String exchangeAssetName = assetsNode.get("exchangeAssetName").textValue();
		result.setExchangeAssetName(exchangeAssetName);

		boolean array = assetsNode.get("apis").isArray();
		if (array && !assetsNode.get("apis").isEmpty()) {
			JsonNode apiNodes = assetsNode.get("apis");

			JsonNode apiNode = apiNodes.get(0);

			String instanceLabel = apiNode.get("instanceLabel").textValue();
			result.setInstanceLabel(instanceLabel);

			int apiId = apiNode.get("id").intValue();
			result.setApiId(apiId);
		}
		return result;
	}
}
