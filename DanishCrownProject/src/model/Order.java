package model;

import java.util.ArrayList;
import java.util.List;

import javax.jws.Oneway;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Order {
	@Id
	@GeneratedValue
	private int OrderID;
	private int totalWeight;
	private int margin;
	
	@Enumerated(EnumType.STRING)
	private Type lType;
	
	@OneToMany(cascade=CascadeType.REMOVE)
	private List <Suborder> lSuborder = new ArrayList<Suborder>(); 
	
	
	public Order() {
	}
	public Order(int orderID, int totalWeight, int margin, Type lType) {
		super();
		OrderID = orderID;
		this.totalWeight = totalWeight;
		this.margin = margin;
		this.lType = lType;
	}
	public int getOrderID() {
		return OrderID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public int getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(int totalWeight) {
		this.totalWeight = totalWeight;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	public Type getlType() {
		return lType;
	}

	public void setlType(Type lType) {
		this.lType = lType;
	}
	
	public List<Suborder> getlSuborder() {
		return lSuborder;
	}

	public void addSuborder(Suborder suborder){
		lSuborder.add(suborder);
	}
	
	public void removeSuborder(Suborder suborder){
		lSuborder.remove(suborder);
	}
	
	@Override
	public String toString() {
		return "Order [OrderID=" + OrderID + ", totalWeight=" + totalWeight
				+ ", margin=" + margin + "]";
	}

	
	}
	


