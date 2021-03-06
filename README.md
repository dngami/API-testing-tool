# API-testing-tool
Aim : To build a tool using Jmeter to test performance of APIs in lower environment.

The following RESTful APIs has been developed for input to our Jmeter based testing tool. Since APIs are hosted on AWS RDS and docker image of tool is available on docker hub(Details in footer), testings could also be done remotely. All this APIs are also tested using Postman using this collection : https://www.getpostman.com/collections/482468e327bd012f4aac and are working correctly.

For connection: http://apitesting.ap-south-1.elasticbeanstalk.com/webapi/ is to be used as the BASE PATH followed by the any of the following request method path.

1. GET Methods: “{}” represents the input variable in the path. (Airports have been named by their three letter codes. For eg. DEL, MUM etc.)
> List all flights: /flightInfo

> Search by Flight id -   /flightInfo/{FlightId}

> Search by Departure and arrival (returns a List of flights with the given arrival and departure airports) -  /flightInfo/departure/{depart}/arrival/{arrival}

> Search by Type (Domestic/International) (returns a List of flights with the given type i.e. International or Domestic) -     /flightInfo/airport/{I || D}    

> All Flights on a Particular Airport (returns a List of flights departing from a particular airport) -  /flightInfo/depart/{airport}

> All Flights on a Particular Airport (returns a List of flights arriving on a particular airport)- /flightInfo/{airport}/arrival/{arrival}

  Sample http requests:
  GET methods:
  > http://apitesting.ap-south-1.elasticbeanstalk.com/webapi/flightInfo
  
  > http://apitesting.ap-south-1.elasticbeanstalk.com/webapi/flightInfo/airport/D

  > http://apitesting.ap-south-1.elasticbeanstalk.com/webapi/flightInfo/1

  > http://apitesting.ap-south-1.elasticbeanstalk.com/webapi/flightInfo/departure/MUM/arrival/DEL


2. POST Method: 
> Add a new Flight-   /addFlight/

NOTE: Duplicate flight Ids are not allowed. I.e. each flight requires a unique id.

PUT Methods: The flight id of the required flight is fetched so that its particulars can be edited.
“{}” represents the input variable in the path. 

> Edit Arrival time                        /edit/{id}/arr/{time}

> Edit Departure time                      /edit/{id}/dept/{time} 

> Edit Arrival destination/Airport         /edit/{id}/arrapt/{Airport}

> Edit Departure destination/Airport       /edit/{id}/arrapt/{Airport}

> Edit Price                               /edit/{id}/price/{price}

3. DELETE Method: The required flight fetched by its Id is deleted.	
> By flight ID -  /delflight/byid/{id}   


Following classes were used so as to handle the requests and deal with the MySQL database. 
FlightRepo: deals with the database
Get/Put/Post/DeleteResource:  handles all the HTTP requests.  


How to create Docker image and run it:
>docker pull sarthvitekar/myflightapi
>docker container run -it --publish 8080:8080 sarthvitekar/myflightapi

 While using docker, since the API will use localhost, please use localhost:8080/FlightApi/webapi/ as the base url.
For ex. localhost:8080/FlightApi/webapi/flightInfo/1 for a sample GET request.
