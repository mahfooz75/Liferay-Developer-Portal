<%@ include file="/init.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <title>Developer Portal</title>
    <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
    <meta content="utf-8" http-equiv="encoding">
    <!-- needed for adaptive design -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    
    <script src="https://cdn.jsdelivr.net/npm/redoc@2.0.0-alpha.17/bundles/redoc.standalone.js"></script>

    <script src="<c:url value="/" />js/redoc-utils.js"></script>
</head>
<body>
	<div id="reddoc-ui"></div>
	<div class="reddoc" id="reddocContainer">
		<c:set var = "id" value = "<%=renderRequest.getParameter("id") %>"/>
		<c:set var = "assetId" value = "<%=renderRequest.getParameter("assetId") %>"/>
		
		<%-- assetId : ${assetId}
		id : ${id} --%>
	   
	    <!-- <script src="redoc.standalone.js"></script>
	    <script src="redoc-utils.js"></script> -->
	    
	    <script>
	        // the below function needs to be called to initialize the documentation of a spec within a div container.
	        // check the function definition in redoc-utils.js  reddocContainer
	        // initializeRedoc('xpo', './GFF.json', {});
	        /**/
	        
	        initializeRedoc('reddocContainer', '<c:url value="/api-json/" /><c:out value="${assetId}" />.json', {});
	        //initializeRedoc('reddocContainer', '/o/com.liferay.docs.guestbook.portlet/create-order-api.json', {});
	         
	    </script>
    </div>
</body>
</html>