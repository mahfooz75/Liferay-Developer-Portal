<%@ include file="/init.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="UTF-8">
    <title>XPO Developer Portal</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script> -->


 
    <!-- <link rel="stylesheet" type="text/css" href="https://unpkg.com/swagger-ui-dist@3.12.1/swagger-ui.css"> -->
    <link rel="stylesheet" type="text/css" href="https://unpkg.com/swagger-ui-dist@3.25.2/swagger-ui.css">
   
 <style>

    .col, .col-1, .col-10, .col-11, .col-12, .col-2, .col-3, .col-4, .col-5, .col-6, .col-7, .col-8, .col-9, .col-auto, .col-lg, .col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-lg-auto, .col-md, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-md-auto, .col-sm, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-sm-auto, .col-xl, .col-xl-1, .col-xl-10, .col-xl-11, .col-xl-12, .col-xl-2, .col-xl-3, .col-xl-4, .col-xl-5, .col-xl-6, .col-xl-7, .col-xl-8, .col-xl-9, .col-xl-auto {
    position: relative;
    width: auto;
    padding-right: 15px;
    padding-left: 15px;
}
     .parameters-col_name{
    width:20%;
}

    </style>
</head>
<body>
	
	<c:set var = "id" value = "<%=renderRequest.getParameter("id") %>"/>
	<c:set var = "assetId" value = "<%=renderRequest.getParameter("assetId") %>"/>
	
	<%-- assetId : ${assetId}
	id : ${id} --%>
	
	<div id="swagger-ui"></div>

    <!-- <script src="https://unpkg.com/swagger-ui-dist@3.12.1/swagger-ui-standalone-preset.js"></script>
    <script src="https://unpkg.com/swagger-ui-dist@3.12.1/swagger-ui-bundle.js"></script> -->
    <script src="https://unpkg.com/swagger-ui-dist@3.25.2/swagger-ui-standalone-preset.js"></script>
    <script src="https://unpkg.com/swagger-ui-dist@3.25.2/swagger-ui-bundle.js"></script>

    <script>
        window.onload = function () {
            var ui = SwaggerUIBundle({
                
                //url: "/o/com.liferay.docs.guestbook.portlet/create-order-api.json",
                url: '<c:url value="/api-json/" />'+'<c:out value="${assetId}" />'+'.json',

                urlHeaders: {
                    //"Authorization": "@ViewBag.authorization"
                    "Authorization": ""
                },
                dom_id: '#swagger-ui',
                presets: [
                    SwaggerUIBundle.presets.apis,
                    SwaggerUIStandalonePreset
                ],
                plugins: [
                    SwaggerUIBundle.plugins.DownloadUrl
                ],
                layout: "StandaloneLayout",
                validatorUrl: null,
                oauth2RedirectUrl: "http://vm-72563.irissoftware.com:9090/meta/rest/ui/oauth2-redirect.html",
                securityDefinitions: null
            });
            window.ui = ui;
        }
    </script>
</body>
</html>