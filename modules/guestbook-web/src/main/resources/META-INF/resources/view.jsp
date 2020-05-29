<%@ include file="/init.jsp"%>

<div id="view-container" >
	<h1>API CATALOG</h1>
	<div class="row align-items-stretch justify-content-start card-deck">
		<c:forEach items="${results}" var="result">
			<portlet:renderURL var="homeURL">
				<portlet:param name="mvcPath" value="/swagger.jsp"></portlet:param>
				<portlet:param name="id" value="${result.id}"></portlet:param>
				<portlet:param name="assetId" value="${result.assetId}"></portlet:param>
			</portlet:renderURL>
			<portlet:renderURL var="reddocURL">
					<portlet:param name="mvcPath" value="/reddoc.jsp"></portlet:param>
					<portlet:param name="id" value="${result.id}"></portlet:param>
					<portlet:param name="assetId" value="${result.assetId}"></portlet:param>
			</portlet:renderURL>
			<div class="col-md-6 mb-3">
				<div class="card h-100">
					<div class="container">
						<div class="card-body">
							<div class="card-text">
								<h4>${result.instanceLabel}</h4>	
								<p>${result.exchangeAssetName}</p>
							</div>
							<a href="<%=homeURL.toString() %>" data-senna-off="true"  class="btn btn-primary">Swagger</a>
					  		<a href="<%=reddocURL.toString() %>" data-senna-off="true"  class="btn btn-primary">Redoc</a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>