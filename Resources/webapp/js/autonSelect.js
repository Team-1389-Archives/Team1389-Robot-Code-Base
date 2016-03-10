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

function drawSelected(autonName){
	console.log("setting " + autonName + " as selected");
	var buttons = document.querySelectorAll(".autonButton");
	[].forEach.call(buttons, function(button){
		button.style.backgroundColor="";
	})
	nameElementMap[autonName].style.backgroundColor="green";
}

function makeAutonElement(modeData){
	var button = document.createElement("button");
	button.innerHTML = modeData.autonName;
	button.className += "autonButton";
	button.onclick = function(){
		console.log("sending " + JSON.stringify({name: modeData.autonName}));
		httpPostAsync("/servlet/setAuton", JSON.stringify({name: modeData.autonName}), function(response){
			console.log("sent it, yo, recieved " + response);
			drawSelected(modeData.autonName);
		});
	}
	return button;
}

window.onload = function(){
httpGetAsync("/servlet/autonModes", function(data){
	var autonModesData = JSON.parse(data);
	var autonListData = autonModesData.autonModes;
	console.log(autonListData);
	var listDiv = document.querySelector("#mode-list");
	
	var autonDivList = autonListData.map(makeAutonElement);
	
	
	autonDivList.forEach(function(element){
		listDiv.appendChild(element);
		nameElementMap[element.innerHTML] = element;
	});
	
	drawSelected(autonModesData.selectedAuton);
});
}