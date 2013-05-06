package dao;

import java.util.ArrayList;





import model.LoadingDock;
import model.Order;
import model.Trailer;

public class DataBase implements DAO {
	
	private static DAO instance=new DataBase();

	ArrayList<Trailer> trailers = new ArrayList<Trailer>();
	ArrayList<Order> orders = new ArrayList<Order>();
	ArrayList<LoadingDock> loadingDocks = new ArrayList<LoadingDock>();
	
	private DataBase()
	{
		
	}
	
	public static DAO getInstance() {
		if (instance == null) {
			instance = new DataBase();

		}
		
		return instance;
	}

	
	
	@Override
	public ArrayList<Trailer> getAllTrailers() {
		
		return new ArrayList<Trailer>(trailers);
	}

	@Override
	public void addTrailer(Trailer trailer) {
		this.trailers.add(trailer);
		
	}

	@Override
	public void removeTrailer(Trailer trailer) {
		this.trailers.remove(trailer);
		
	}

	@Override
	public ArrayList<Order> getOrder() {
		
		return new ArrayList<Order>(orders);
	}

	@Override
	public void addOrder(Order order) {
		this.orders.add(order);
		
	}

	@Override
	public void removeOrder(Order order) {
		this.orders.remove(order);
		
	}

	@Override
	public ArrayList<LoadingDock> getAllLoadingDocks() {
		return new ArrayList<LoadingDock>(loadingDocks);
	}

	@Override
	public void addLoadingDock(LoadingDock loadingDock) {
		this.loadingDocks.add(loadingDock);
		
	}

	@Override
	public void removeLoadingDock(LoadingDock loadingDock) {
		this.loadingDocks.remove(loadingDock);
		
	}
	
	

	
	

	

}
