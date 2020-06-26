package com.bootspringrest.example.repository;
import com.bootspringrest.example.model.Flights;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flights, Long>{
	@Query("select flight from Flights flight where flight.type = ?1")
	List<Flights> findbytype(String type);
	
	@Query("select flight from Flights flight where flight.DeptApt = ?1 and flight.ArrApt= ?2")
	List<Flights> findbyendpoints(String deptApt, String arrApt);
	
	@Query("select flight from Flights flight where flight.ArrApt= ?1")
	List<Flights> findByArrApt(String arrApt);
	
	@Query("select flight from Flights flight where flight.DeptApt= ?1")
	List<Flights> findByDeptApt(String deptApt);
}
