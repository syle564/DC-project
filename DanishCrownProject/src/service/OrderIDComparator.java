package service;

import java.util.Comparator;

import model.Order;

public class OrderIDComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		return o1.getOrderID()-o2.getOrderID();
	}

	
}
