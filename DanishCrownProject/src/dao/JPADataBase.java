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
		em.persist(trailer);
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
	public void updateTrailer(Trailer trailer, String truckID, String company,
			String driver, String driverPhNum, Type lType) {
		
		em.getTransaction().begin();
		Trailer t = em.find(Trailer.class,trailer.getTrailerID());
		t.setTrailerID(truckID);
		t.setCompany(company);
		t.setDriver(driver);
		t.setDriverPhNum(driverPhNum);
		t.setlType(lType);
		em.getTransaction().commit();
	}

	@Override
	public ArrayList<Order> getOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOrder(Order order, int totalWeight, int margin, Type lType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<LoadingDock> getAllLoadingDocks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLoadingDock(LoadingDock loadingDock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeLoadingDock(LoadingDock loadingDock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLoadingDock(LoadingDock loadingDock, int dockID,
			Type lType, Status lStatus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLoad(Load load, Date estStartTime, Date estEndTime,
			LoadingDock loadingDock, boolean completed, Date actualBegTime,
			Date actualEndTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLoad(Date estStartTime, Date estEndTime, Suborder suborder,
			LoadingDock loadingDock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSuborder(int loadingTime, int weight, Date loadingDate,
			Order order, Trailer lTrailer) {
		// TODO Auto-generated method stub
		
	}

	
}
