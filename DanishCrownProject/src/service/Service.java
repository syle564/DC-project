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

	private DAO dao=JPADataBase.getInstance(); 
	
	private Service()
	{
		
	}
	
	/**
	 * @author Momo
	 *Gets the instance of a single Service object
	 */
	public static Service getInstance()
	{
		if (instance == null) {
			instance = new Service();

		}
		
		return instance;
	}
	
	

/**
 * Creates new order. Represents Register order from our use cases 
 */
	
	/**
	 * @param orderId
	 * @param totalWeight
	 * @param margin
	 * @param lType
	 * @return
	 */
	// Register Order
	public Order createOrder(int orderId, int totalWeight, int margin, model.Type lType)
	{
		Order order=new Order(orderId,totalWeight,margin,lType);
		dao.addOrder(order);
		return order;
	}
	
	//returns a sorted list of the orders
	public ArrayList<Order> getAllOrders()
	{
		return quicSort(dao.getOrder(), new OrderIDComparator());
	}
	 //Modify Order
	public void updateOrder(Order order,int totalWeight,int margin,Type lType){
		
		
		dao.updateOrder(order,totalWeight,margin,lType);
	}

	//Remove Order
	public void removeOrder(Order order)
	{
		dao.removeOrder(order);
	}
	//Create Trailer
	public  Trailer createTrailer(String truckID, String company,String driver,String driverPhNum,Type lType)
	{
		Trailer trailer=new Trailer(truckID, company, driver, driverPhNum, lType);
		dao.addTrailer(trailer);
		return trailer;
	}
	//Remove trailer(not a use case)
	public void removeTrailer(Trailer trailer)
	{
		dao.removeTrailer(trailer);
	}
	
	//Maintain trailer information
	public void updateTrailer(Trailer trailer,Date arrivalTime,int restTime,boolean departed,int weighIn ,String truckID,String company,String driver,String driverPhNum,Type lType )
	{
		dao.updateTrailer(trailer,arrivalTime, restTime,departed ,weighIn, truckID, company, driver, driverPhNum, lType);	
	}
	
	//not a use case
	public LoadingDock createLoadingDock(int dockID,Type lType,Status lStatus)
	{
		LoadingDock loadingDock=new LoadingDock(dockID, lType, lStatus);
		dao.addLoadingDock(loadingDock);
		return loadingDock;
	}
	//not a use case
	public void removeLoadingDock(LoadingDock loadingDock)
	{
		dao.removeLoadingDock(loadingDock);
	}
	
	/**
	 * @author Momo
	 *Creates a load which is connected to a suborder and is attached to a loading dock
	 */
	/**
	 * @param estStartTime
	 * @param estEndTime
	 * @param suborder
	 * @param loadingDock
	 * @return
	 */
	//included in the Register trailer
	public boolean createLoad(Date estStartTime, Date estEndTime,Suborder suborder,LoadingDock loadingDock)
	{
//		if(estStartTime.compareTo(estEndTime)>=0 || estEndTime.getHours()==23L || estStartTime.getHours()<6L || loadingDock.getlStatus()==Status.CLOSED )
//			return false;
		
		 dao.addLoad(estStartTime, estEndTime, suborder, loadingDock);
		return true;
		
	}
	//not a use case
	public void beginLoad(Load load,LoadingDock loadingDock)
	{
		dao.updateLoad(load, load.getEstStartTime(), load.getEstEndTime(), loadingDock, load.isCompleted(), DU.createDate(), null);
		
	}

	/**
	 * @author Momo
	 *
	 */
	//Load approved
    public boolean completeLoad(Load load, LoadingDock loadingDock,String trailerID)
    {
    	dao.updateLoad(load, load.getEstStartTime(), load.getEstEndTime(), loadingDock, true, load.getActualBegTime(),DU.createDate());
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
	 * @author The smoking ace's
	 *Creates a suborder belonging to order and that is attached to a Trailer s
	 */
	/**
	 * @param loadingTime
	 * @param weight
	 * @param loadingDate
	 * @param order
	 * @param lTrailer
	 * @return
	 */
  //not a use case
	public Suborder createSuborder(int loadingTime,int weight, Date loadingDate,Order order,Trailer lTrailer)
	{
		Suborder suborder=new Suborder(loadingTime, weight, loadingDate,lTrailer);
		lTrailer.addlSuborders(suborder);
		dao.addSuborder(loadingTime, weight, loadingDate, order, lTrailer);
		return suborder;
	}
	//not a use case
	public void removeSuborder(Order order, Suborder suborder)
	{
		order.getlSuborder().remove(suborder);
	}
	
	
	//Register trailer as available
	public boolean registerIn(String trailerID, int weightIn,int restTime,String phoneNumb)
	{
		if(restTime < 0 || restTime > 240)
			return false;
		if(phoneNumb.length() > 15 || phoneNumb.length() <= 0 ||trailerID.length() > 10 )
			return false;
		if(weightIn>14000 || weightIn<0)
		return false;
		
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
			 if(findLastLoad(lD).compareTo(plannedDate1)<0)
					appropriateDock=lD;
		 }
		 
		 
	for(Suborder s : foundT.getlSuborders()){
		//finding the shortest queue 
		
		Date plannedDate=findLastLoad(appropriateDock); 
		
		if(appropriateDock==null)
		{
			
			appropriateDock=loadingDocks.get(0);
		}
	
		createLoad(DU.createDatePlusMinuts(plannedDate, 5), DU.createDatePlusMinuts(plannedDate, 5+s.getLoadingTime()), s,appropriateDock);
		//s.setlLoad(load);
		//beginLoad(load);
		//loadToDock(load, appropriateDock);
		
		
		
	}
	return true;
	 }
	 
	
	 else return false;
	
	}
	
	//obsolete
	//included in the register trailer case
	public void loadToDock(Load load,LoadingDock loadingDock)
	{
	
			if(load.getlSuborder().getlTrailer().getlType()==loadingDock.getlType() && (loadingDock.getlStatus()==Status.OPEN || loadingDock.getlStatus()==Status.OCCUPIED) )
			{
				loadingDock.addLoad(load);
			}
			loadingDock.setlStatus(Status.OCCUPIED);
	}
	
	//not a use case
	public Date findLastLoad(LoadingDock loadingDock)
	{Date lastLoad = DU.createDate();
	
		for( Load l:loadingDock.getlLoad())
		{
			if(l.getEstEndTime().compareTo(lastLoad)>0)
			{	lastLoad=l.getEstEndTime();
			
			System.out.println("found");
			}
		}
//	if(!loadingDock.getlLoad().isEmpty())
//	{
//			
//		return loadingDock.getlLoad().get(loadingDock.getlLoad().size()-1).getEstEndTime();
//		}
		System.out.println(lastLoad);
	return lastLoad;
	}
	//Register weight of the trailer before departure
	public boolean weightOut(Trailer trailer,int weightOut,int margin)
	{
		if(margin<0 || weightOut>26000)
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
	//Get loading dock status
	public Status DockStatus(LoadingDock loadingDock)
	{
		return loadingDock.getlStatus();
	}
	
	//not a use case
	private  <T> void swap(ArrayList<T> list, int i1, int i2)
	{
		T temp = list.get(i1);
		list.set(i1, list.get(i2));
		list.set(i2, temp);
	}
	//not a use case
    public <T> ArrayList<T> quicSort(ArrayList<T> list,Comparator<T> comparator) 
    {
    	quicksortRec(list, comparator, 0, list.size()-1);
    	
		return list;
 
    }
  //not a use case
    private <T> void quicksortRec(ArrayList<T> list,Comparator<T> comparator, 
            int low, int high){

    	if (low < high) {

    		int p = partition(list, comparator,low, high);

    		quicksortRec(list,comparator, low, p-1);

    		quicksortRec(list,comparator ,p+1, high);
    		}
    }	
    
    
  //not a use case
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
    
    
    //Get list of available trailers
    public ArrayList<Trailer> getAvailbleTrailers()
    {ArrayList<Trailer> availableTrailers = new ArrayList<Trailer>();
    	
     for(Trailer t: dao.getAllTrailers())
     {if(!t.isDeparted())
    	 availableTrailers.add(t);
    	 
     }
     
     return quicSort(availableTrailers, new TrailerIDComparator());
    }

    //Get list of loading times(from specific loading dock's loads)
    public ArrayList<Load> getLoadsFrom(LoadingDock lDock)
     {ArrayList<Load> loads=new ArrayList<Load>();
      for(Load l: lDock.getlLoad())
      {
    	  loads.add(l);
      }
      return quicSort(loads, new LoadTimeComparator());
    	
     }
    //Get list of availabale docks
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
