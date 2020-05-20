<%@ include file="/init.jsp"%>


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