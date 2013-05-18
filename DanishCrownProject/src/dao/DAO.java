package dao;

import java.util.ArrayList;
import java.util.Date;


import model.Load;
import model.LoadingDock;
import model.Order;
import model.Status;
import model.Suborder;
import model.Trailer;
import model.Type;

public interface DAO {
	
	


	public ArrayList<Trailer> getAllTrailers();
	public void addTrailer(Trailer Trailer);
	public void removeTrailer(Trailer Trailer);
	public void updateTrailer(Trailer trailer,Date arrivalTime,int restTime,boolean departed ,int weighIn,String truckID, String company,String driver,String driverPhNum,Type lType);
	
	
	public ArrayList<Order> getOrder();
	public void addOrder(Order order);
	public void removeOrder(Order order);
	public void updateOrder(Order order,int totalWeight,int margin,Type lType);
	
	public ArrayList<LoadingDock> getAllLoadingDocks();
	public void addLoadingDock(LoadingDock loadingDock);
	public void removeLoadingDock(LoadingDock loadingDock);
	public void updateLoadingDock(LoadingDock loadingDock,int dockID,Type lType,Status lStatus);
	
	public void updateLoad(Load load,Date estStartTime, Date estEndTime,LoadingDock loadingDock,boolean completed,Date actualBegTime,Date actualEndTime);
	public void addLoad(Date estStartTime, Date estEndTime,Suborder suborder,LoadingDock loadingDock);
	
	public void addSuborder(int loadingTime,int weight, Date loadingDate,Order order,Trailer lTrailer);
	
	 
	
	
	
}
