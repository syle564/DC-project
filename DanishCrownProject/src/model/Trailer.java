package model;

import java.util.ArrayList;
import java.util.Date;


public class Trailer implements Comparable<String> {
private String truckID;
private String company;
private int weighIn;
private String driver;
private String driverPhNum;
private Date arrivalTime;
private int restTime;
private Type lType;
private ArrayList<Suborder> lSuborders;


public Trailer(String truckID, String company, String driver,
		String driverPhNum, Type lType) {
	super();
	this.truckID = truckID;
	this.company = company;
	this.driver = driver;
	this.driverPhNum = driverPhNum;
	this.lType = lType;
	lSuborders=new ArrayList<Suborder>();
}

public Trailer() {
	
}

public String getTruckID() {
	return truckID;
}

public void setTruckID(String truckID) {
	this.truckID = truckID;
}

public String getCompany() {
	return company;
}

public void setCompany(String company) {
	this.company = company;
}


public String getDriver() {
	return driver;
}

public void setDriver(String driver) {
	this.driver = driver;
}

public String getDriverPhNum() {
	return driverPhNum;
}

public void setDriverPhNum(String driverPhNum) {
	this.driverPhNum = driverPhNum;
}




public int getWeighIn() {
	return weighIn;
}

public void setWeighIn(int weighIn) {
	this.weighIn = weighIn;
}

public Date getArrivalTime() {
	return arrivalTime;
}

public void setArrivalTime(Date arrivalTime) {
	this.arrivalTime = arrivalTime;
}



public int getRestTime() {
	return restTime;
}

public void setRestTime(int restTime) {
	this.restTime = restTime;
}

public Type getlType() {
	return lType;
}

public void setlType(Type lType) {
	this.lType = lType;
}


public ArrayList<Suborder> getlSuborders() {
	return lSuborders;
}

public void addlSuborders(Suborder suborder) {
	this.lSuborders.add(suborder);
}

public void removeSuborder(Suborder suborder)
{this.lSuborders.remove(suborder);
	}

@Override
public String toString() {
	return "ID:" + truckID + " weighIn:" + weighIn ;
}


/**
 * @author The Smokiing ace's
 *Compares two trailers by their ID 
 */
@Override
public int compareTo(String ID) {
	
	return this.truckID.compareTo(ID);
}



}
