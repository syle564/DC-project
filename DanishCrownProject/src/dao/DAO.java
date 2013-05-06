package dao;

import java.util.ArrayList;

import model.LoadingDock;
import model.Order;
import model.Trailer;

public interface DAO {
	
	


	public ArrayList<Trailer> getAllTrailers();
	public void addTrailer(Trailer Trailer);
	public void removeTrailer(Trailer Trailer);
	
	
	public ArrayList<Order> getOrder();
	public void addOrder(Order order);
	public void removeOrder(Order order);
	
	public ArrayList<LoadingDock> getAllLoadingDocks();
	public void addLoadingDock(LoadingDock loadingDock);
	public void removeLoadingDock(LoadingDock loadingDock);
	
	 
	
	
	
}
