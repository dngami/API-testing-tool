package com.bootspringrest.example.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "flights")
@EntityListeners(AuditingEntityListener.class)
public class Flights {
	@Id/*
	@GeneratedValue(strategy=GenerationType.AUTO)*/
	private Long id;
	@Column(name = "deptApt")
	private String DeptApt;
	private String ArrApt;
	private String DeptTime;
	private String ArrTime;
	private int Distance;
	private int Price;
	private String type;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeptApt() {
		return DeptApt;
	}
	public void setDeptApt(String deptApt) {
		DeptApt = deptApt;
	}
	public String getArrApt() {
		return ArrApt;
	}
	public void setArrApt(String arrApt) {
		ArrApt = arrApt;
	}
	public String getDeptTime() {
		return DeptTime;
	}
	public void setDeptTime(String deptTime) {
		DeptTime = deptTime;
	}
	public String getArrTime() {
		return ArrTime;
	}
	public void setArrTime(String arrTime) {
		ArrTime = arrTime;
	}
	public int getDistance() {
		return Distance;
	}
	public void setDistance(int distance) {
		Distance = distance;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
