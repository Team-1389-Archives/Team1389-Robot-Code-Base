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

function download(filename, text) {
  var element = document.createElement('a');
  element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
  element.setAttribute('download', filename);

  element.style.display = 'none';
  document.body.appendChild(element);

  element.click();

  document.body.removeChild(element);
}

function dataToCsv(dataObj){
    var text = dataObj.xAxisLabel + "," + dataObj.yAxisLabel;
    for (var point of dataObj.dataPoints){
        text += "\n" + point.x + "," + point.y;
    }
    return text;
}

function makeChart(name, dataObj){
    var div = document.createElement("div");
    var nameP = document.createElement("p");
    var chart = document.createElement("canvas");
    var downloadButton = document.createElement("button");
    
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
    
    downloadButton.addEventListener("click", function(){
       download(name + ".csv", dataToCsv(dataObj));
    });
    downloadButton.innerHTML = "Download Chart";
    
    var myNewChart = new Chart(ctx).Scatter(data);
    
    div.appendChild(nameP);
    div.appendChild(chart);
    div.appendChild(downloadButton);
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