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

function drawSelected(autonName){
	var buttons = document.querySelectorAll(".autonButton");
	[].forEach.call(buttons, function(button){
		button.style.backgroundColor="";
	})
	nameElementMap[autonName].style.backgroundColor="green";
}

function makeAutonElement(modeData){
	var button = document.createElement("button");
	button.innerText = modeData.autonName;
	button.className += "autonButton";
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
		nameElementMap[element.innerText] = element;
	});
});
}