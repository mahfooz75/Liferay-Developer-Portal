<%@ include file="/init.jsp"%>

<%-- <portlet:renderURL var="addEntryURL">
	<portlet:param name="mvcPath" value="/edit_entry.jsp"></portlet:param>
</portlet:renderURL>

<portlet:renderURL var="testURL">
	<portlet:param name="mvcPath" value="/test.jsp"></portlet:param>
</portlet:renderURL>

<jsp:useBean id="entries" class="java.util.ArrayList" scope="request" />

<liferay-ui:search-container>
	<liferay-ui:search-container-results results="<%= entries %>" />

	<liferay-ui:search-container-row
		className="com.liferay.docs.guestbook.model.Entry" modelVar="entry">
		<liferay-ui:search-container-column-text property="message" />

		<liferay-ui:search-container-column-text property="name" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<aui:button-row>
	<aui:button onClick="<%=addEntryURL.toString() %>" value="Add Entry"></aui:button>
</aui:button-row> --%>


<%-- <portlet:actionURL name="list_api" var="listApi" />
<aui:a href="${listApi}">List</aui:a>  --%> 

<c:forEach items="${results}" var="result">
	<div class="card">
	  <div class="container">
	  <aui:row>
	  	<aui:col>
	  		<h4><i>${result.assetId}</i></h4>
	  	</aui:col>
	  </aui:row>
	  
	  <aui:row>
	  	<aui:col>
	  		Group Id : ${result.groupId}
	  	</aui:col>
	  </aui:row>
	  
	  <aui:row>
	  	<aui:col>
	  		Id : ${result.id}
	  	</aui:col>
	  </aui:row>
	  
	  <aui:row>
	  	<aui:col>
	  		Asset Name : ${result.exchangeAssetName}
	  	</aui:col>
	  </aui:row>
	  
	  <aui:row>
	  	<aui:col>
	  		API Id : ${result.apiId}
	  	</aui:col>
	  </aui:row>
	  
	  <aui:row>
	  	<aui:col>
	  		<b>Label : ${result.instanceLabel}</b>
	  	</aui:col>
	  </aui:row>
	  </div>
	</div>
</c:forEach>

<%-- <h2><c:out value="${listApi}"/></h2> --%>

<%-- <aui:a href="<%=testURL.toString() %>" label="Test Page"></aui:a> --%>