package com.bootspringrest.example.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootspringrest.example.model.Flights;
//import com.bootspringrest.example.repository.EmployeeRepository;
import com.bootspringrest.example.repository.FlightRepository;



@Service
public class FlightDAO {
	@Autowired
	FlightRepository flightRepository;
	
	public Flights save(Flights emp){
		return flightRepository.save(emp);
	}
	
	public List<Flights> findAll(){
		return flightRepository.findAll();
	}
	
	public Flights findOne(Long id){
		return flightRepository.findOne(id);
	}
	
	public List<Flights> findByType(String type){
		return flightRepository.findbytype(type);
	}
	
	public List<Flights> findByEndpoints(String deptApt, String arrApt){
		return flightRepository.findbyendpoints(deptApt, arrApt);
	}
	/*public void delete(Employee emp){
		employeeRepository.delete(emp);
	}*/

	public List<Flights> findByArrapt(String ArrApt){
		return flightRepository.findByArrApt(ArrApt);
	}
	
	public List<Flights> findByDeptapt(String DeptApt){
		return flightRepository.findByDeptApt(DeptApt);
	}
	
	public void deletebyID(Long id){
		Flights f = flightRepository.findOne(id);
		if (f==null) return;
		flightRepository.delete(f);

	}
}
