package model;

import java.util.ArrayList;
import java.util.Date;


public class Trailer implements Comparable<String> {
private String trailerID;
private String company;
private int weighIn;
private String driver;
private String driverPhNum;
private Date arrivalTime;
private int restTime;
private boolean departed;

public boolean isDeparted() {
	return departed;
}

public void setDeparted(boolean departed) {
	this.departed = departed;
}



private Type lType;
private ArrayList<Suborder> lSuborders;



public Trailer(String trailerID, String company, String driver,
		String driverPhNum, Type lType) {
	super();
	this.trailerID = trailerID;
	this.company = company;
	this.driver = driver;
	this.driverPhNum = driverPhNum;
	this.lType = lType;
	lSuborders=new ArrayList<Suborder>();
}

public Trailer() {
	
}

public String getTrailerID() {
	return trailerID;
}

public void setTrailerID(String trailerID) {
	this.trailerID = trailerID;
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
	return "ID:" + trailerID + " weighIn:" + weighIn ;
}


/**
 * @author The Smokiing ace's
 *Compares two trailers by their ID 
 */
@Override
public int compareTo(String ID) {
	
	return this.trailerID.compareTo(ID);
}



}
