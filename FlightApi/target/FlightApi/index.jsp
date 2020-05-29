<html>
<head>
</head>
<body>
    <h2>Jersey RESTful Web Application</h2>
    
    <h3>   Some basic operations: </h3>
    
    <p>1.<a href="webapi/flightInfo"> List all the Flights</a>
   	<p>2. 
    <input id = "id_" placeholder = "enter flight id"> 
    <button > <a href = "javascript:;" onclick = "this.href='webapi/flightInfo/' + document.getElementById('id_').value">Get details</a> </button>
    </p>
    
    
    <p>3. 
    <input id = "dept" placeholder = "Departure">&nbsp;  to &nbsp; <input id = "arrival" placeholder = "Arrival"> 
    <button > <a href = "javascript:;" onclick = "this.href='webapi/flightInfo/departure/' + document.getElementById('dept').value + '/arrival/' + document.getElementById('arrival').value ">Get details</a> </button>
    
    </p>
    <p>Visit <a href="https://github.com/dngami/API-testing-tool">here</a>
    for the source code!
    </p>
</body>
</html>
