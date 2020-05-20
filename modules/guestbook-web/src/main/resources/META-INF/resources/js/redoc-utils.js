function initializeRedoc(containerDivId, specUrl, options){
	var container = document.getElementById(containerDivId);
	/*if (container != null ){
		container.parentNode.removeChild(container);
	}*/
	var newDiv = document.createElement("div");
	newDiv.id = containerDivId;
	document.body.appendChild(newDiv);
	Redoc.init(specUrl, options , document.getElementById(containerDivId));
	
}