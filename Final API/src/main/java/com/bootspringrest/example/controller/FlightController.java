package com.bootspringrest.example.controller;

import java.util.List;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.bootspringrest.Exception.APIrequestException;
//import com.bootspringrest.example.dao.EmployeeDAO;
import com.bootspringrest.example.dao.FlightDAO;
import com.bootspringrest.example.model.Flights;




@RestController
@RequestMapping("/FlightApi")
public class FlightController {
	@Autowired
	FlightDAO flightdao;
	Logger log= LoggerFactory.getLogger(this.getClass());
	@PostMapping("/addFlight")
	public Flights createFlight(@Valid @RequestBody Flights f){
		log.info("Someone tried to add a new flight to the database!");
		Flights temp=flightdao.findOne(f.getId());
		if (temp!=null) 
			throw new RuntimeException("Flight with the given id already exists, give a new id");
		temp= flightdao.save(f);
		if (temp.getArrApt()==null||temp.getDeptApt()==null||temp.getArrTime()==null||temp.getDeptTime()==null||temp.getPrice()==0||temp.getDistance()==0||temp.getType()==null)
		{
			log.error("Paramters not provided properly, which lead to error!");
			flightdao.deletebyID(f.getId());
			throw new RuntimeException("please provide all the parameters properly");
		}
		// As ID is the primary key, the info is added to the logfile whenever a new flight is added so as to avoid same ID errors
		log.info("A new flight with id "+f.getId()+" has been added successfully!");
		return temp;
	}
	
	@PostMapping("/addList")
	public String createFlights(@Valid @RequestBody List<Flights> fl){
		log.info("Someone tried to add a list of new flights to the database!");
		for (Flights f:fl)
		{
			 Flights temp = flightdao.findOne(f.getId());
			 if (temp!=null)
				 throw new RuntimeException("Flight with id="+f.getId()+" already exists, give a new id");
			 temp=flightdao.save(f);
			 if (temp.getArrApt()==null||temp.getDeptApt()==null||temp.getArrTime()==null||temp.getDeptTime()==null||temp.getPrice()==0||temp.getDistance()==0||temp.getType()==null)
				{
				 	log.error("Parameters for the flight with id "+f.getId()+" are not provided properly");
				 	flightdao.deletebyID(f.getId());
					throw new RuntimeException("please provide all the parameters properly for the flight with id="+f.getId());
				}	

		}
		log.info("The given flights are successfully added");
		return "The flights are added successfully";
	}
	@GetMapping("/flightInfo")
	public List<Flights> getAllFlights(){
		log.info("Someone tried to access the list of all flights!");
		List<Flights> flights = flightdao.findAll();
		if (flights.size()==0)
		{
			log.warn("There are no flights in the database");
			throw new RuntimeException("There are no flights available");
		}
		log.info("Flight information is successfully sent!");
		return flights;
	}
	
	@GetMapping("/flightInfo/{id}")
	public ResponseEntity<Flights> getFlightsById(@PathVariable(value = "id") Long empid){
		Flights flight = flightdao.findOne(empid);
		log.info("Someone tried to access the details of flight with id="+empid);
		if(flight == null){
			log.warn("there's no flight present with the required id!");
			throw new RuntimeException("There is no flight available with the given id");
		}
		log.info("Flight info is successfully returned!");
			return ResponseEntity.ok().body(flight);
	}
	
	@GetMapping("/flightInfo/airport/{type}")
	public List<Flights> getFlightByType(@PathVariable(value = "type") String type){
		log.info("Someone tried to access the details of all fflights with type "+type);
		List<Flights> flights = flightdao.findByType(type);
		if (flights.size()==0)
		{
			log.warn("No flights of the given type are available");
			throw new RuntimeException("There are no flights available with the given type");
		}
		log.info("Flight info is successfully returned!");
		return flights;
	}
	
	@GetMapping("/flightInfo/departure/{departure}/arrival/{arrival}")
	public List<Flights> getFlightByEndpoints(@PathVariable(value = "departure") String deptApt, @PathVariable(value= "arrival") String arrApt){
		List<Flights> flights = flightdao.findByEndpoints(deptApt,arrApt);
		log.info("Someone tried to access the list of all flights going from "+deptApt+" to"+arrApt);
		if (flights.size()==0)
		{
			log.warn("No flights are running between the given airports");
			throw new RuntimeException("There are no flights running between the given airports");
		}
		log.info("Flight info is successfully returned!");
		return flights;
	}
	
	@GetMapping("/flightInfo/arrival/{airport}")
	public List<Flights> getFlightByArrApt(@PathVariable(value ="airport") String arrApt){
		
		List<Flights> flights = flightdao.findByArrapt(arrApt);
		log.info("Someone tried to access the details of all flights arriving to "+arrApt);
		if (flights.size()==0)
		{
			log.warn("There are no flights arriving to the given airport");
			throw new RuntimeException("There are no flights arriving to the given airport");
		}
		log.info("Flight info is successfully returned!");
		return flights;
	}
	@GetMapping("/flightInfo/departure/{airport}")
	public List<Flights> getFlightByDeptApt(@PathVariable(value ="airport") String deptApt){
		log.info("Someone tried to access the details of all flights departing from "+deptApt);
		List<Flights> flights = flightdao.findByDeptapt(deptApt);
		if (flights.size()==0)
		{
			log.warn("There are no flights departing from the given airport");
			throw new RuntimeException("There are no flights departing from the given airport");
		}
		log.info("Flight info is successfully returned!");
		return flights;
	}
	@PutMapping("/edit/{id}/arr/{time}")
	public ResponseEntity<Flights> updatearrTime(@PathVariable(value="id") Long id,@PathVariable(value="time") String arrtime){
		
		Flights f=flightdao.findOne(id);
		log.info("Someone tried to update the arrival time of flight with id="+id);
		if (f==null){
			log.error("No flight available with the given id");
			throw new RuntimeException("No flight available with the given id");
		}
		f.setArrTime(arrtime);
		
		Flights updateFlight = flightdao.save(f);
		log.info("Flight details are successfully updated!");
		return ResponseEntity.ok().body(updateFlight);
	}	
	@PutMapping("/edit/{id}/dept/{time}")
	public ResponseEntity<Flights> updatedeptTime(@PathVariable(value="id") Long id,@PathVariable(value="time") String depttime){
		
		Flights f=flightdao.findOne(id);
		log.info("Someone tried to update the departure time of flight with id="+id);
		if (f==null){
			log.error("No flight available with the given id");
			throw new RuntimeException("No flight available with the given id");
		}
			f.setDeptTime(depttime);
			
		Flights updateFlight = flightdao.save(f);
		log.info("Flight details are successfully updated!");
		return ResponseEntity.ok().body(updateFlight);
	}
	@PutMapping("/edit/{id}/arrapt/{airport}")
	public ResponseEntity<Flights> updatearrApt(@PathVariable(value="id") Long id,@PathVariable(value="airport") String airport){
		
		Flights f=flightdao.findOne(id);
		log.info("Someone tried to update the arrival airport of flight with id="+id);
		if (f==null){
			log.error("No flight available with the given id");
			throw new RuntimeException("No flight available with the given id");
		}	
		f.setArrApt(airport);
		
		Flights updateFlight = flightdao.save(f);
		log.info("Flight details are successfully updated!");
		return ResponseEntity.ok().body(updateFlight);
	}	
	
	@PutMapping("/edit/{id}/deptapt/{airport}")
	public ResponseEntity<Flights> updatedeptApt(@PathVariable(value="id") Long id,@PathVariable(value="airport") String airport){
		
		Flights f=flightdao.findOne(id);
		log.info("Someone tried to update the departure airport of flight with id="+id);
		if (f==null){
			log.error("No flight available with the given id");
			throw new RuntimeException("No flight available with the given id");	
		}
			f.setDeptApt(airport);
			
		Flights updateFlight = flightdao.save(f);
		log.info("Flight details are successfully updated!");
		return ResponseEntity.ok().body(updateFlight);
	}
	@PutMapping("/edit/{id}/price/{price}")
	public ResponseEntity<Flights> updatePrice(@PathVariable(value="id") Long id,@PathVariable(value="price") int price){
		Flights f=flightdao.findOne(id);
		log.info("Someone tried to update the price of flight with id="+id);
		if (f==null){
			log.error("No flight available with the given id");
			throw new RuntimeException("No flight available with the given id");
		}
		
		f.setPrice(price);
		Flights updateFlight = flightdao.save(f);
		log.info("Flight details are successfully updated!");
		return ResponseEntity.ok().body(updateFlight);
	}
	@DeleteMapping("/delflight/byid/{id}")
	public ResponseEntity<Flights> deleteFlight(@PathVariable(value="id") Long id){
		log.info("Someone tried to delete the flight with id="+id);
		Flights f=flightdao.findOne(id);
		
		if (f==null){
			log.error("There's no flight with the given id");
			throw new RuntimeException("No flight available with the given id");
			
		}
		
		flightdao.deletebyID(id);
		log.info("The requested flight is successfully deleted!");
		return ResponseEntity.ok().body(f);
	}
	
}