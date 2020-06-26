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

//import com.bootspringrest.example.dao.EmployeeDAO;
import com.bootspringrest.example.dao.FlightDAO;
import com.bootspringrest.example.model.Flights;




@RestController
@RequestMapping("/FlightApi")
public class FlightController {
	@Autowired
	FlightDAO flightdao;
	@PostMapping("/addFlight")
	public Flights createFlight(@Valid @RequestBody Flights f){
		
		Flights temp= flightdao.save(f);
		if (temp.getArrApt()==null||temp.getDeptApt()==null||temp.getArrTime()==null||temp.getDeptTime()==null||temp.getPrice()==0||temp.getDistance()==0||temp.getType()==null)
		{
		
			flightdao.deletebyID(f.getId());
			throw new RuntimeException("please provide all the parameters properly");
		}
		
		return temp;
	}
	
	@PostMapping("/addList")
	public String createFlights(@Valid @RequestBody List<Flights> fl){
		
		for (Flights f:fl)
		{
			 Flights temp=flightdao.save(f);
			 if (temp.getArrApt()==null||temp.getDeptApt()==null||temp.getArrTime()==null||temp.getDeptTime()==null||temp.getPrice()==0||temp.getDistance()==0||temp.getType()==null)
				{
				 	
				 	flightdao.deletebyID(f.getId());
					throw new RuntimeException("please provide all the parameters properly for the flight with id="+f.getId());
				}	

		}
		
		return "The flights are added successfully";
	}
	@GetMapping("/flightInfo")
	public List<Flights> getAllFlights(){
		
		List<Flights> flights = flightdao.findAll();
		if (flights.size()==0)
		{
			
			throw new RuntimeException("There are no flights available");
		}
		
		return flights;
	}
	
	@GetMapping("/flightInfo/{id}")
	public ResponseEntity<Flights> getFlightsById(@PathVariable(value = "id") Long empid){
		Flights flight = flightdao.findOne(empid);
		if(flight == null){
			
			throw new RuntimeException("There is no flight available with the given id");
		}
		
			return ResponseEntity.ok().body(flight);
	}
	
	@GetMapping("/flightInfo/airport/{type}")
	public List<Flights> getFlightByType(@PathVariable(value = "type") String type){
		
		List<Flights> flights = flightdao.findByType(type);
		if (flights.size()==0)
		{
			
			throw new RuntimeException("There are no flights available with the given type");
		}
		
		return flights;
	}
	
	@GetMapping("/flightInfo/departure/{departure}/arrival/{arrival}")
	public List<Flights> getFlightByEndpoints(@PathVariable(value = "departure") String deptApt, @PathVariable(value= "arrival") String arrApt){
		List<Flights> flights = flightdao.findByEndpoints(deptApt,arrApt);
		if (flights.size()==0)
		{
			
			throw new RuntimeException("There are no flights running between the given airports");
		}
		
		return flights;
	}
	
	@GetMapping("/flightInfo/arrival/{airport}")
	public List<Flights> getFlightByArrApt(@PathVariable(value ="airport") String arrApt){
		
		List<Flights> flights = flightdao.findByArrapt(arrApt);
		if (flights.size()==0)
		{
			
			throw new RuntimeException("There are no flights arriving to the given airport");
		}
		
		return flights;
	}
	@GetMapping("/flightInfo/departure/{airport}")
	public List<Flights> getFlightByDeptApt(@PathVariable(value ="airport") String deptApt){
		
		List<Flights> flights = flightdao.findByDeptapt(deptApt);
		if (flights.size()==0)
		{
			
			throw new RuntimeException("There are no flights departing from the given airport");
		}
		
		return flights;
	}
	@PutMapping("/edit/{id}/arr/{time}")
	public ResponseEntity<Flights> updatearrTime(@PathVariable(value="id") Long id,@PathVariable(value="time") String arrtime){
		
		Flights f=flightdao.findOne(id);
		
		if (f==null){
			
			throw new RuntimeException("No flight available with the given id");
		}
		f.setArrTime(arrtime);
		
		Flights updateFlight = flightdao.save(f);
		return ResponseEntity.ok().body(updateFlight);
	}	
	@PutMapping("/edit/{id}/dept/{time}")
	public ResponseEntity<Flights> updatedeptTime(@PathVariable(value="id") Long id,@PathVariable(value="time") String depttime){
		
		Flights f=flightdao.findOne(id);
		
		if (f==null){
			
			throw new RuntimeException("No flight available with the given id");
		}
			f.setDeptTime(depttime);
			
		Flights updateFlight = flightdao.save(f);
		return ResponseEntity.ok().body(updateFlight);
	}
	@PutMapping("/edit/{id}/arrapt/{airport}")
	public ResponseEntity<Flights> updatearrApt(@PathVariable(value="id") Long id,@PathVariable(value="airport") String airport){
		
		Flights f=flightdao.findOne(id);
		
		if (f==null){
			
			throw new RuntimeException("No flight available with the given id");
		}	
		f.setArrApt(airport);
		
		Flights updateFlight = flightdao.save(f);
		return ResponseEntity.ok().body(updateFlight);
	}	
	
	@PutMapping("/edit/{id}/deptapt/{airport}")
	public ResponseEntity<Flights> updatedeptApt(@PathVariable(value="id") Long id,@PathVariable(value="airport") String airport){
		
		Flights f=flightdao.findOne(id);
		
		if (f==null){
			
			throw new RuntimeException("No flight available with the given id");	
		}
			f.setDeptApt(airport);
			
		Flights updateFlight = flightdao.save(f);
		return ResponseEntity.ok().body(updateFlight);
	}
	@PutMapping("/edit/{id}/price/{price}")
	public ResponseEntity<Flights> updatePrice(@PathVariable(value="id") Long id,@PathVariable(value="price") int price){
		Flights f=flightdao.findOne(id);
		
		if (f==null){
			
			throw new RuntimeException("No flight available with the given id");
		}
		
		f.setPrice(price);
		Flights updateFlight = flightdao.save(f);
		return ResponseEntity.ok().body(updateFlight);
	}
	@DeleteMapping("/delflight/byid/{id}")
	public ResponseEntity<Flights> deleteFlight(@PathVariable(value="id") Long id){
		
		Flights f=flightdao.findOne(id);
		
		if (f==null){
			
			throw new RuntimeException("No flight available with the given id");
			
		}
		
		flightdao.deletebyID(id);
		return ResponseEntity.ok().body(f);
	}
	
}
