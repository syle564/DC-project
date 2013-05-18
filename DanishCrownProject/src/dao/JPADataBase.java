package dao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Load;
import model.LoadingDock;
import model.Order;
import model.Status;
import model.Suborder;
import model.Trailer;
import model.Type;

public class JPADataBase implements DAO
{
	

	EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("DCproject");
	EntityManager em = emf.createEntityManager();

	private static DAO instance;
	
	
	
	private JPADataBase() {
		
	}
	
	
	
	public static DAO getInstance() {
		if (instance == null) {
			instance = new JPADataBase();

		}
		
		return instance;
	}




	@Override
	public ArrayList<Trailer> getAllTrailers() {
		Query query= em.createQuery("Select t from Trailer t", Trailer.class);
		List<Trailer> trailers=query.getResultList();
		
		ArrayList<Trailer> arrTrailers=new ArrayList<Trailer>();
		for(Trailer t: trailers)
		{arrTrailers.add(t);
			
		}
		return arrTrailers;
	}

	@Override
	public void addTrailer(Trailer trailer) {
		em.getTransaction().begin();
		em.merge(trailer);
		em.getTransaction().commit();
		
	}

	@Override
	public void removeTrailer(Trailer trailer) {
		Trailer t = em.find(Trailer.class,trailer.getTrailerID());
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	}

	@Override
	public void updateTrailer(Trailer trailer, Date arrivalTime, int restTime,
			boolean departed,int weighIn, String truckID, String company, String driver,
			String driverPhNum, Type lType) {
		em.getTransaction().begin();
		Trailer t = em.find(Trailer.class,trailer.getTrailerID());
		t.setArrivalTime(arrivalTime);
		t.setDeparted(departed);
		t.setRestTime(restTime);
		t.setWeighIn(weighIn);
		t.setTrailerID(truckID);
		t.setCompany(company);
		t.setDriver(driver);
		t.setDriverPhNum(driverPhNum);
		t.setlType(lType);
		em.getTransaction().commit();
	}


	@Override
	public ArrayList<Order> getOrder() {
		Query query= em.createQuery("Select t from Order t", Order.class);
		List<Order> orders=query.getResultList();
		
		ArrayList<Order> arrOrders=new ArrayList<Order>();
		for(Order o: orders)
		{arrOrders.add(o);
			
		}
		return arrOrders;
		
	}

	@Override
	public void addOrder(Order order) {
		em.getTransaction().begin();
		em.merge(order);
		em.getTransaction().commit();
	}

	@Override
	public void removeOrder(Order order) {
		
		em.getTransaction().begin();
		em.remove(order);
		em.getTransaction().commit();
	}

	@Override
	public void updateOrder(Order order, int totalWeight, int margin, Type lType) {
		
		
		
		em.getTransaction().begin();
		Order o = em.find(Order.class,order.getOrderID());
		o.setMargin(margin);
		o.setTotalWeight(totalWeight);
		o.setlType(lType);
		em.getTransaction().commit(); 
	}

	@Override
	public ArrayList<LoadingDock> getAllLoadingDocks() {
		
		Query query= em.createQuery("Select t from LoadingDock t", LoadingDock.class);
		List<LoadingDock> loadingDocks=query.getResultList();
		
		ArrayList<LoadingDock> arrDocks=new ArrayList<LoadingDock>();
		for(LoadingDock d:loadingDocks )
		{arrDocks.add(d);
			
		}
		return arrDocks;
	}

	@Override
	public void addLoadingDock(LoadingDock loadingDock) {
		em.getTransaction().begin();
		em.merge(loadingDock);
		em.getTransaction().commit();
	}

	@Override
	public void removeLoadingDock(LoadingDock loadingDock) {
		
		em.getTransaction().begin();
		em.remove(loadingDock);
		em.getTransaction().commit();
	}

	@Override
	public void updateLoadingDock(LoadingDock loadingDock, int dockID,
			Type lType, Status lStatus) {
		
		em.getTransaction().begin();
		LoadingDock d= em.find(LoadingDock.class,loadingDock.getDockID());
		d.setlType(lType);
		d.setlStatus(lStatus);
		em.getTransaction().commit(); 
	}

	@Override
	public void updateLoad(Load load, Date estStartTime, Date estEndTime,
			LoadingDock loadingDock, boolean completed, Date actualBegTime,
			Date actualEndTime) {
		
		em.getTransaction().begin();
		LoadingDock d= em.find(LoadingDock.class,loadingDock.getDockID());
		Load loadPlaceHolder = null;
		for(Load l:loadingDock.getlLoad())
		{
			if(l.getEstStartTime().equals(load.getEstStartTime()))
				loadPlaceHolder=l;
		}
		loadPlaceHolder.setEstStartTime(estStartTime);
		loadPlaceHolder.setEstEndTime(estEndTime);
		loadPlaceHolder.setActualBegTime(actualBegTime);
		loadPlaceHolder.setActualEndTime(actualEndTime);
		loadPlaceHolder.setCompleted(completed);
		em.getTransaction().commit(); 
		
	}

	@Override
	public void addLoad(Date estStartTime, Date estEndTime, Suborder suborder,
			LoadingDock loadingDock) {
		
		em.getTransaction().begin();
		LoadingDock d= em.find(LoadingDock.class,loadingDock.getDockID());
		Load l =new Load(estStartTime, estEndTime, suborder);
		loadingDock.addLoad(l);
		suborder.setlLoad(l);
		em.getTransaction().commit(); 
	}

	@Override
	public void addSuborder(int loadingTime, int weight, Date loadingDate,
			Order order, Trailer lTrailer) {
		em.getTransaction().begin();
		Suborder sub= new Suborder(loadingTime,weight,loadingDate,lTrailer);
		Order or=em.find(Order.class, order.getOrderID());
		or.addSuborder(sub);
		Trailer t=em.find(Trailer.class,lTrailer.getTrailerID());
		t.addlSuborders(sub);
		em.getTransaction().commit();
	}



	
	
}
