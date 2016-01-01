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

function makeChart(name, dataObj){
    console.log(name);
    console.log(dataObj);
    var div = document.createElement("div");
    var nameP = document.createElement("p");
    var chart = document.createElement("canvas");
    
    var ctx = chart.getContext("2d");
    
    nameP.innerHTML = name;
    
    var data = [
    {
      label: 'data',
      strokeColor: '#007ACC',
      pointColor: '#007ACC',
      pointStrokeColor: '#fff',
      data: dataObj.dataPoints
    }
  ];
    
    var myNewChart = new Chart(ctx).Scatter(data);
    
    div.appendChild(nameP);
    div.appendChild(chart);
    return div;
}

window.onload = function(){
    httpGetAsync("/servlet/getCharts", function(data){
        var dataObj = JSON.parse(data);
        var chartList = document.querySelector("#charts-list");
        for (var name in dataObj){
            var obj = dataObj[name];
            var chartDiv = makeChart(name, obj);
            chartList.appendChild(chartDiv);
        }
    })
}