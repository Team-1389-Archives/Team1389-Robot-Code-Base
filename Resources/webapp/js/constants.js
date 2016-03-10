//TODO: put this function in a global util file
function httpGetAsync(theUrl, callback)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
            callback(xmlHttp.responseText);
    }
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function httpPostAsync(theUrl, data, callback)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
            callback(xmlHttp.responseText);
    }
    xmlHttp.open("POST", theUrl, true); // true for asynchronous
    xmlHttp.send(data);
}

var nameElementMap = {};

function makeConstantElement(name, value){
	var div = document.createElement("div");
	var nameP = document.createElement("p");
	var valueP = document.createElement("p");
	var editSubmitButton = document.createElement("button");
	var input = document.createElement("input");
	input.hidden = true;
	
	//create editing ability
	editSubmitButton.innerHTML = "Edit";
	var buttonMode = "Edit";
	editSubmitButton.addEventListener("click", function(){
	    if (buttonMode === "Edit") {
	        buttonMode = "Submit";
	        input.value = value;
	        input.hidden = false;
	    }
	    else {
	        buttonMode = "Edit";
	        input.hidden = true;
	        httpPostAsync("/servlet/setConstant",
	            JSON.stringify({
	                    name: name,
	                    value: input.value
	                }),
	                function() {
	                    makePage();
	                });
	    }
	    editSubmitButton.innerHTML = buttonMode;
	});
	
	nameP.innerHTML = name;
	valueP.innerHTML = value;
	
	div.appendChild(nameP);
	div.appendChild(valueP);
	div.appendChild(editSubmitButton);
	div.appendChild(input);
	div.style.border = "thick solid black";
	return div;
}

function makePage(){
	httpGetAsync("/servlet/getConstants", function(data){
		var dataObj = JSON.parse(data);
		var constants = dataObj.constants;
		var listDiv = document.querySelector("#constants-list");
		listDiv.innerHTML = "";
		
		for (var name in constants){
			var element = makeConstantElement(name, constants[name]);
			nameElementMap[name] = element;
			listDiv.appendChild(element);
		}
	});

}

window.onload = function(){
    makePage();
}