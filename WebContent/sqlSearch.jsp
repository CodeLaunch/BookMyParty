
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>Book My Party</title>
<style>
div {text-align:center;}
</style>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDq5GAE3tOpXqt2ig9jXSYOh5PUx1tPTCU"
	type="text/javascript"></script>
<script type="text/javascript">
	//         
	var map;
	var markers = [];
	var infoWindow;
	var locationSelect;

	function load() {
		map = new google.maps.Map(document.getElementById("map"), {
			center : new google.maps.LatLng(40, -100),
			zoom : 4,
			mapTypeId : 'roadmap',
			mapTypeControlOptions : {
				style : google.maps.MapTypeControlStyle.DROPDOWN_MENU
			}
		});
		infoWindow = new google.maps.InfoWindow();

		locationSelect = document.getElementById("locationSelect");
		locationSelect.onchange = function() {
			var markerNum = locationSelect.options[locationSelect.selectedIndex].value;
			if (markerNum != "none") {
				google.maps.event.trigger(markers[markerNum], 'click');
			}
		};
	}

	function searchLocations() {
		var address = document.getElementById("addressInput").value;
		var geocoder = new google.maps.Geocoder();
		geocoder.geocode({
			address : address
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				searchLocationsNear(results[0].geometry.location);
			} else {
				alert(address + ' not found');
			}
		});
	}

	function clearLocations() {
		infoWindow.close();
		for ( var i = 0; i < markers.length; i++) {
			markers[i].setMap(null);
		}
		markers.length = 0;

		locationSelect.innerHTML = "";
		var option = document.createElement("option");
		option.value = "none";
		option.innerHTML = "See all results:";
		locationSelect.appendChild(option);
	}

	function searchLocationsNear(center) {
		clearLocations();

		var radius = document.getElementById('radiusSelect').value;
		var searchUrl = 'http://localhost:8080/bukParty.com/home';
		downloadUrl(
				searchUrl,
				function(data) {
					var xml = parseXml(data);
					var markerNodes = xml.documentElement
							.getElementsByTagName("marker");
					var bounds = new google.maps.LatLngBounds();
					var Result = [];
					for ( var i = 0; i < markerNodes.length; i++) {
						var name = markerNodes[i].getAttribute("name");
						var address = markerNodes[i].getAttribute("address");
						var distance = parseFloat(markerNodes[i]
								.getAttribute("distance"));
						var latlng = new google.maps.LatLng(
								parseFloat(markerNodes[i].getAttribute("lat")),
								parseFloat(markerNodes[i].getAttribute("lng")));
						Result.push(name);
						createOption(name, distance, i);
						createMarker(latlng, name, address);
						bounds.extend(latlng);
					}
					getResult(Result);
					map.fitBounds(bounds);
					locationSelect.style.visibility = "visible";
					locationSelect.onchange = function() {
						var markerNum = locationSelect.options[locationSelect.selectedIndex].value;
						google.maps.event.trigger(markers[markerNum], 'click');
					};
				});
	}

	function createMarker(latlng, name, address) {
		var html = "<b>" + name + "</b> <br/>" + address;
		var marker = new google.maps.Marker({
			map : map,
			position : latlng,
			url: 'http://localhost:8080/bukParty.com/CaterView.jsp?id='+name
		});
		google.maps.event.addListener(marker, 'click', function() {
			infoWindow.setContent(html);
			window.location.href = marker.url;
			infoWindow.open(map, marker);
			
		});
		markers.push(marker);
	}

	function createOption(name, distance, num) {
		var option = document.createElement("option");
		option.value = num;
		option.innerHTML = name + "(" + distance.toFixed(1) + ")";
		locationSelect.appendChild(option);
	}

	function downloadUrl(url, callback) {
		var request = window.ActiveXObject ? new ActiveXObject(
				'Microsoft.XMLHTTP') : new XMLHttpRequest;

		request.onreadystatechange = function() {
			if (request.readyState == 4) {
				request.onreadystatechange = doNothing;
				callback(request.responseText, request.status);
			}
		};
		request.open('GET', url, true);
		request.send(null);
	}

	function parseXml(str) {
		if (window.ActiveXObject) {
			var doc = new ActiveXObject('Microsoft.XMLDOM');
			doc.loadXML(str);
			return doc;
		} else if (window.DOMParser) {
			return (new DOMParser).parseFromString(str, 'text/xml');
		}
	}

	function doNothing() {
	}
	
	function getResult(str){

		var xhttp; 
		  if (str == "") {
		    document.getElementById("Result").innerHTML = "";
		    return;
		  }
		  xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		    document.getElementById("Result").innerHTML = xhttp.responseText;
		    }
		  };
		  xhttp.open("POST", "http://localhost:8080/bukParty.com/Result.jsp?q="+str, true);
		  xhttp.send();

		}

	//
</script>
</head>

<body style="margin: 0px; padding: 0px;" onload="load()">
<center>
	<div>
		<input type="text" id="addressInput" size="10" /> <select
			id="radiusSelect">
			<option value="25" selected>25mi</option>
			<option value="100">100mi</option>
			<option value="200">200mi</option>
		</select> <input type="button" onclick="searchLocations()" value="Search" />
	</div>
	<div>
		<select id="locationSelect" style="width: 60%; visibility: hidden"></select>
	</div>
	
	<div id="map" style="width: 60%; height: 60%"></div>
	<div id="Result" style="width: 60%; height: 60%">	</div>
	</center>
</body>
</html>
