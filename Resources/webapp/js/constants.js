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

var nameElementMap = {};

function makeConstantElement(name, value){
	var div = document.createElement("div");
	var nameP = document.createElement("p");
	var valueP = document.createElement("p");
	
	nameP.innerText = name;
	valueP.innerText = value;
	
	div.appendChild(nameP);
	div.appendChild(valueP);
	div.style.border = "1px solid black;";
	return div;
}

window.onload = function(){
	httpGetAsync("/servlet/getConstants", function(data){
		var data = JSON.parse(data);
		var constants = data.constants;
		console.log(constants);
		var listDiv = document.querySelector("#constants-list");
		
		for (var name in constants){
			var element = makeConstantElement(name, constants[name]);
			nameElementMap[name] = element;
			listDiv.appendChild(element);
		}
	});
}