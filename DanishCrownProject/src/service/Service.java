package service;


import model.Order;
import dao.DAO;
import dao.DataBase;
import dao.JPADataBase;
import model.Type;
import model.Suborder;
import model.Trailer;
import model.LoadingDock;
import model.Status;
import model.Load;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;






public class Service {

	private static Service instance;

	private DAO dao=DataBase.getInstance(); 
	
	/**
	 *The constructor is private so that it is impossible to instantiate the Class externally.
	 */
	private Service()
	{
		
	}
	
	/**
	 *Gets the instance of a single Service object. If it does not exist the method instantiates it.
	 */
	public static Service getInstance()
	{
		if (instance == null) {
			instance = new Service();

		}
		
		return instance;
	}
	
	

/**
 * Creates new order. Represents Register order from our use cases.
 */
	
	/**
	 * @param orderId
	 * @param totalWeight
	 * @param margin
	 * @param lType
	 * @return
	 */
	public Order createOrder(int orderId, int totalWeight, int margin, model.Type lType)
	{
		Order order=new Order(orderId,totalWeight,margin,lType);
		dao.addOrder(order);
		return order;
	}
	
	/**
	 * Returns a sorted ArrayList of the orders from the Data Base 
	 */
	public ArrayList<Order> getAllOrders()
	{
		return quicSort(dao.getOrder(), new OrderIDComparator());
	}
	 
	/**
	 * Modifies a single order 
	 */
	public void updateOrder(Order order,int totalWeight,int margin,Type lType){
		
		
		dao.updateOrder(order,totalWeight,margin,lType);
	}

	/**
	 *Removes a specific order
	 */
	public void removeOrder(Order order)
	{
		dao.removeOrder(order);
	}
	
	/**
	 *Creates a Trailer object and stores it in the DataBase
	 */
	public  Trailer createTrailer(String truckID, String company,String driver,String driverPhNum,Type lType)
	{
		Trailer trailer=new Trailer(truckID, company, driver, driverPhNum, lType);
		dao.addTrailer(trailer);
		return trailer;
	}
	
	/**
	 *Removes a specific Trailer from the DataBase
	 */
	public void removeTrailer(Trailer trailer)
	{
		dao.removeTrailer(trailer);
	}
	
	/**
	 *Updates a specific Trailers's information 
	 */
	public void updateTrailer(Trailer trailer,Date arrivalTime,int restTime,boolean departed,int weighIn ,String truckID,String company,String driver,String driverPhNum,Type lType )
	{
		dao.updateTrailer(trailer,arrivalTime, restTime,departed ,weighIn, truckID, company, driver, driverPhNum, lType);	
	}
	
	
	/**
	 *Creates and stores a new LoadingDock in the DataBase
	 */
	public LoadingDock createLoadingDock(int dockID,Type lType,Status lStatus)
	{
		LoadingDock loadingDock=new LoadingDock(dockID, lType, lStatus);
		dao.addLoadingDock(loadingDock);
		return loadingDock;
	}
	
	
	/**
	 *Removes the specified LoadingDock from the DataBase
	 */
	public void removeLoadingDock(LoadingDock loadingDock)
	{
		dao.removeLoadingDock(loadingDock);
	}
	
	/**
	 *Creates a load which is connected to a sub-order and is attached to a loading dock. 
	 *Returns true if successful.
	 */
	/**
	 * @param estStartTime
	 * @param estEndTime
	 * @param suborder
	 * @param loadingDock
	 * @return
	 */
	public boolean createLoad(Date estStartTime, Date estEndTime,Suborder suborder,LoadingDock loadingDock)
	{
//		if(estStartTime.compareTo(estEndTime)>=0 || estEndTime.getHours()==23L || estStartTime.getHours()<6L || loadingDock.getlStatus()==Status.CLOSED )
//			return false;
		
		 dao.addLoad(estStartTime, estEndTime, suborder, loadingDock);
		return true;
		
	}
	
	/**
	 *Sets the actualBegTime of the specified load.
	 */
	public void beginLoad(Load load,LoadingDock loadingDock)
	{
		dao.updateLoad(load, load.getEstStartTime(), 
		load.getEstEndTime(), loadingDock, load.isCompleted(), DU.createDate(), null);
		
	}

	/**
	 *Sets the actualEndTime of the specified Load, marks it as completed.
	 * If it is the last completed load from a particular trailer the method returns true.
	 */
    public boolean completeLoad(Load load, LoadingDock loadingDock,String trailerID)
    {
    	dao.updateLoad(load, load.getEstStartTime(), load.getEstEndTime(),
    			loadingDock, true, load.getActualBegTime(),DU.createDate());
    	ArrayList<Trailer> trailers=dao.getAllTrailers();
    	 Trailer foundT = null;
    	 for(Trailer t : trailers){
    			if(t.getTrailerID().equals(trailerID))
    				foundT = t;
    				}
    	
    	boolean  truckReady=true;
    	
    	for(Suborder s:foundT.getlSuborders())
    	{
    		if(!s.getlLoad().isCompleted())
    			truckReady=false;
 
    	}
    	return truckReady;
    }
        
	/**
	 *Creates a sub-order belonging to order and that is attached to a Trailer s
	 */
	/**
	 * @param loadingTime
	 * @param weight
	 * @param loadingDate
	 * @param order
	 * @param lTrailer
	 * @return
	 */
	public Suborder createSuborder(int loadingTime,int weight, Date loadingDate,Order order,Trailer lTrailer)
	{
	    Suborder suborder=new Suborder(loadingTime, weight, loadingDate,lTrailer);
		dao.addSuborder(loadingTime, weight, loadingDate, order, lTrailer);
		return suborder;
	}
	/**
	 *Removes a sub-order from a specific order
	 */
	public void removeSuborder(Order order, Suborder suborder)
	{
		order.getlSuborder().remove(suborder);
	}
	
	
	/**
	 *Finds a particular trailer. Registers it by scheduling Loads for all it's Sub-orders.
	 *Returns true if registration successful.
	 */
	public boolean registerIn(String trailerID, int weightIn,int restTime,String phoneNumb)
	{
		//checks for the parameters
		if(restTime < 0 || restTime > 240)
			return false;
		if(phoneNumb.length() > 15 || phoneNumb.length() <= 0 ||trailerID.length() > 10 )
			return false;
		if(weightIn>14000 || weightIn<0)
		return false;
		
		//finding the correct trailer from the database;
	 ArrayList<Trailer> trailers=dao.getAllTrailers();
	 ArrayList<LoadingDock> loadingDocks=getAvailableDocks();
	 Trailer foundT = null;
	 for(Trailer t : trailers){
		if(t.getTrailerID().equals(trailerID)){
			foundT = t;	
			dao.updateTrailer(foundT,DU.createDate(),restTime,false,weightIn
					,foundT.getTrailerID(), foundT.getCompany(),foundT.getDriver(),phoneNumb, foundT.getlType());
	foundT.setRestTime(restTime);
	foundT.setWeighIn(weightIn);
	foundT.setDriverPhNum(phoneNumb);
	foundT.setArrivalTime(DU.createDate());
	
		}
	
	 }
	
	 
	 if(foundT!=null)
	 {
		 
		 LoadingDock appropriateDock=null;
		 for (LoadingDock lD : loadingDocks)
		 {
			 Date plannedDate1 = DU.createDate(1);
			 if(findLastLoad(lD).compareTo(plannedDate1)<0 &&
					 lD.getlType().equals(foundT.getlType()) && !lD.getlStatus().equals(Status.CLOSED))
					appropriateDock=lD;
			 
			 
			 else{
				 plannedDate1=findLastLoad(lD);
			 }
		 }
		 
		 if(appropriateDock==null)
			{
				
				appropriateDock=loadingDocks.get(0);
			}
		 
		 
	for(Suborder s : foundT.getlSuborders()){
		//finding the shortest queue 
		Date plannedDate=findLastLoad(appropriateDock); 
		appropriateDock.setlStatus(Status.OCCUPIED);
		createLoad(DU.createDatePlusMinuts(plannedDate, 5), DU.createDatePlusMinuts(plannedDate, 5+s.getLoadingTime()), s,appropriateDock);
		
		
		
		
	}
	return true;
	 }
	 
	
	 else return false;
	
	}
	

	/**
	 *Deprecated 
	 */
	public void loadToDock(Load load,LoadingDock loadingDock)
	{
	
			if(load.getlSuborder().getlTrailer().getlType()==loadingDock.getlType() && (loadingDock.getlStatus()==Status.OPEN || loadingDock.getlStatus()==Status.OCCUPIED) )
			{
				loadingDock.addLoad(load);
			}
			loadingDock.setlStatus(Status.OCCUPIED);
	}
	
	//not a use case
	/**
	 * Finds the estimatedEndTime of  the last load from a specific LoadingDock
	 * @param loadingDock
	 * @return
	 */
	public Date findLastLoad(LoadingDock loadingDock)
	{Date lastLoad = DU.createDate();
	
	
	
		for( Load l:loadingDock.getlLoad())
		{
			if(l.getEstEndTime().compareTo(lastLoad)>0)
			{	lastLoad=l.getEstEndTime();
			
			}
		}
	if(!loadingDock.getlLoad().isEmpty())
	{
			
		return loadingDock.getlLoad().get(loadingDock.getlLoad().size()-1).getEstEndTime();
		}
		
	return lastLoad;
	}
	
	/**
	 * Weighs out the specified Trailer and if it is successful returns true. 
	 */
	public boolean weightOut(Trailer trailer,int weightOut,int margin)
	{
		if(margin<0 || weightOut>26000 || trailer.isDeparted())
			return false;
		
		int totalWeight = 0;
		
		for(Suborder s : trailer.getlSuborders()){
			totalWeight+= s.getWeight();
			}
		totalWeight+=trailer.getWeighIn();
		if(totalWeight <= weightOut + margin && totalWeight > weightOut - margin  ){
			
			trailer.setDeparted(true);
			return true;
		}
		else{
			
			registerIn(trailer.getTrailerID(), trailer.getWeighIn(), trailer.getRestTime(),trailer.getDriverPhNum());
			return false;
		}
	}
	
	/**
	 *Signs out a Trailer and also weighs it out.
	 */
	public boolean signOut(String trailerID,int weightOut)
	{
		ArrayList<Trailer> trailers=dao.getAllTrailers();
		 
		Trailer foundT = null;
		 for(Trailer t : trailers){
			if(t.getTrailerID().equals(trailerID))
				foundT = t;
		 }
		 
		 if(foundT!=null)
		 {
		 Order foundOrder=null; 
		
		 Suborder suborder=foundT.getlSuborders().get(0);
		 for(Order o:dao.getOrder())
			 if(o.getlSuborder().contains(suborder))
			 foundOrder=o;
		 
		  if(foundOrder!=null)
			return weightOut(foundT, weightOut, foundOrder.getMargin()); 
		 
		  else
		   return false;}
		 
		else return false;
		
	}
	/**
	 *Gets the specified LoadingDock Status.
	 */
	public Status DockStatus(LoadingDock loadingDock)
	{
		return loadingDock.getlStatus();
	}
	
	/**
	 *Swaps two elements in an ArrayList.
	 */
	private  <T> void swap(ArrayList<T> list, int i1, int i2)
	{
		T temp = list.get(i1);
		list.set(i1, list.get(i2));
		list.set(i2, temp);
	}
	/**
	 * Wrapper method for the quick sorting.
	 */
    public <T> ArrayList<T> quicSort(ArrayList<T> list,Comparator<T> comparator) 
    {
    	quicksortRec(list, comparator, 0, list.size()-1);
    	
		return list;
 
    }
    /**
	 *Quick sorts a partition from a specific ArrayList
	 */
    private <T> void quicksortRec(ArrayList<T> list,Comparator<T> comparator, 
            int low, int high){

    	if (low < high) {

    		int p = partition(list, comparator,low, high);

    		quicksortRec(list,comparator, low, p-1);

    		quicksortRec(list,comparator ,p+1, high);
    		}
    }	
    private <T>  int partition(ArrayList<T> list,Comparator<T> comparator, int low, int high)
	{
		T e = list.get(low);
		int i = low + 1;
		int j = high;
		while (i <= j) {
			if (comparator.compare(list.get(i), e) <= 0 )
				i++;
			else if (comparator.compare(list.get(j), e) > 0)
				j--;
			else {
				swap(list,i,j);
				i++;
				j--;
			}
		}
		swap(list,low,j);
		return j;
	}
    
    
    /**
	 *Gets an ArrayList of available trailers sorted by their IDs.
	 */
    public ArrayList<Trailer> getAvailbleTrailers()
    {ArrayList<Trailer> availableTrailers = new ArrayList<Trailer>();
    	
     for(Trailer t: dao.getAllTrailers())
     {if(!t.isDeparted())
    	 availableTrailers.add(t);
    	 
     }
     
     return quicSort(availableTrailers, new TrailerIDComparator());
    }

    /**
     * Get list of loading times(from specific loading dock's loads)
     * @param lDock
     * @return
     */
    public ArrayList<Load> getLoadsFrom(LoadingDock lDock)
     {ArrayList<Load> loads=new ArrayList<Load>();
      for(Load l: lDock.getlLoad())
      {
    	  loads.add(l);
      }
      return quicSort(loads, new LoadTimeComparator());
    	
     }
    /**
     * Get list of available docks
     * @return
     */
    public ArrayList<LoadingDock> getAvailableDocks()
    {
    	ArrayList<LoadingDock> loadingDocks=new ArrayList<LoadingDock>();
    	for(LoadingDock l:dao.getAllLoadingDocks())
    	{
    		if(l.getlStatus()!=Status.CLOSED)
    			loadingDocks.add(l);
    	}
    	
    	return quicSort(loadingDocks, new DockIDComparator());
    }
	
	}
