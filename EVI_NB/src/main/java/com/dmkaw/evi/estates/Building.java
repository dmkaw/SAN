package com.dmkaw.evi.estates;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BUILDINGS")
public class Building {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private BuildingType bt;
	
	private int floors;
	
	private int undergroundFloors;
	
	private float area;
	
	private boolean status = true;
	
	public Building() {
	}
	
	public Building(BuildingType bt, float area) {
		this.bt = bt;
		this.area = area;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BuildingType getBt() {
		return bt;
	}

	public void setBt(BuildingType bt) {
		this.bt = bt;
	}

	public int getFloors() {
		return floors;
	}

	public void setFloors(int floors) {
		this.floors = floors;
	}

	public int getUndergroundFloors() {
		return undergroundFloors;
	}

	public void setUndergroundFloors(int undergroundFloors) {
		this.undergroundFloors = undergroundFloors;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
