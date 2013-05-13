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
	public void updateTrailer(Trailer trailer,String truckID, String company,String driver,String driverPhNum,Type lType){
		Trailer tr=null;
		for(Trailer t:trailers){
			if(t.equals(trailer)){
				tr=t;	
			}
		}
		if(tr!=null){
			tr.setTrailerID(truckID);
			tr.setCompany(company);
			tr.setDriver(driver);
			tr.setDriverPhNum(driverPhNum);
			tr.setlType(lType);
		}
		
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
	
	public void updateOrder(Order order,int totalWeight,int margin,Type lType){
		Order or=null;
		for(Order o:orders){
			if(o.equals(order)){
				or=o;
			}
		}
		or.setlType(lType);
		or.setMargin(margin);
		or.setTotalWeight(totalWeight);
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
	
	public void updateLoadingDock(LoadingDock loadingDock,int dockID,Type lType,Status lStatus){
		LoadingDock ld=null;
		for(LoadingDock l:loadingDocks){
			if(l.equals(loadingDock)){
				ld=l;
			}
		}
		if(ld!=null){
		ld.setDockID(dockID);
		ld.setlType(lType);
		ld.setlStatus(lStatus);
	}
		}

	@Override
	public void addLoad(Date estStartTime, Date estEndTime, Suborder suborder,LoadingDock loadingDock) {
		LoadingDock ld=null;
		for(LoadingDock l:loadingDocks){
			if(l.equals(loadingDock)){
				ld=l;
			}
		}
		ld.addLoad(new Load(estStartTime, estEndTime, suborder, loadingDock));
	}

	@Override
	public void updateLoad(Load load,Date estStartTime, Date estEndTime,
			LoadingDock loadingDock, boolean completed, Date actualBegTime,
			Date actualEndTime) {
		LoadingDock lo=null;
		Load loadd=null;
		for(LoadingDock l:loadingDocks){
			if(l.equals(loadingDock)){
				lo=l;
			}
		}
		for(Load loa:lo.getlLoad()){
			if(loa.equals(load)){
				loadd=loa;
			}
		}
		loadd.setActtualEndTime(actualEndTime);
		loadd.setActualBegTime(actualBegTime);
		loadd.setCompleted(completed);
		loadd.setEstEndTime(estEndTime);
		loadd.setEstStartTime(estStartTime);
		
	}

	@Override
	public void addSuborder(int loadingTime, int weight, Date loadingDate,
			Order order, Trailer lTrailer) {
		Order or=null;
		Trailer tr=null;
		for(Order o:orders){
			if(o.equals(order)){
				or=o;
			}
		}
		for(Trailer t:trailers){
			if(t.equals(lTrailer)){
				tr=t;
			}
		}
		Suborder sub= new Suborder(loadingTime, weight, loadingDate,lTrailer);
		or.addSuborder(sub);
		tr.addlSuborders(sub);
		
	}
	
		
}
