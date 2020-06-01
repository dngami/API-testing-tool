# API-testing-tool
Aim : To build a tool using Jmeter to test performance of APIs in lower environment.

Test API For Flight Scheduling/ Database has been made the functioning of which is described below

The following RESTful API has been developed in Jersey.

Class : Flight
1. Id   - id
2. Departure Airport - DeptApt 
3. Arrival Airport - ArrApt
4. Departure Time - DeptTime
5. Arrival Time - ArrTime
6. Distance - Distance
7. Price - Price
8. Type - [International/Domestic] - type

To connect on postman: http://apitesting.ap-south-1.elasticbeanstalk.com/webapi/ is to be used as the base path.

Airports have been named by their three letter codes. For eg. DEL, MUM etc.

GET Methods: “{}” represents the input variable in the path. 
> List all flights: /flightInfo

> Search by Flight id -   /flightInfo/{FlightId}

> Search by Departure and arrival (returns a List of flights with the given arrival and departure airports) -  /flightInfo/departure/{depart}/arrival/{arrival}

> Search by Type (Domestic/International) (returns a List of flights with the given type i.e. International or Domestic) -     /flightInfo/airport/{I || D}    

> All Flights on a Particular Airport (returns a List of flights departing from a particular airport) -  /flightInfo/depart/{airport}

> All Flights on a Particular Airport (returns a List of flights arriving on a particular airport)- /flightInfo/{airport}/arrival/{arrival}

POST Method: 
> Add a new Flight-   /addFlight/

NOTE: Duplicate flight Ids are not allowed. I.e. each flight requires a unique id.

PUT Methods: The flight id of the required flight is fetched so that its particulars can be edited.
“{}” represents the input variable in the path. 

> Edit Arrival time                               /edit/{id}/arr/{time}
> Edit Departure time                      /edit/{id}/dept/{time} 
> Edit Arrival destination/Airport            /edit/{id}/arrapt/{Airport}
> Edit Departure destination/Airport     /edit/{id}/arrapt/{Airport}
> Edit Price                                    /edit/{id}/price/{price}


DELETE Method: The required flight fetched by its Id is deleted.	
> By flight ID -  /delflight/byid/{id}   


Following classes were used so as to handle the requests and deal with the MySQL database. 
FlightRepo: deals with the database
Get/Put/Post/DeleteResource:  handles all the HTTP requests.  


MySQL Database: 
Hosted on:  AWS RDS
Database name: restDB
Table name: flights


Sample http requests:
GET methods:
> http://apitesting.ap-south-1.elasticbeanstalk.com/webapi/flightInfo/airport/D
> http://apitesting.ap-south-1.elasticbeanstalk.com/webapi/flightInfo/1
> http://apitesting.ap-south-1.elasticbeanstalk.com/webapi/flightInfo/departure/MUM/arrival/DEL


