package service;


import model.Order;
import dao.DataBase;
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
		DataBase.getInstance().addOrder(order);
		return order;
	}
	 //Modify Order
	public void updateOrder(Order order,int totalWeight,int margin,Type lType){
		
		order.setlType(lType);
		order.setMargin(margin);
		order.setTotalWeight(totalWeight);
		DataBase.getInstance().addOrder(order);
	}

	//Remove Order
	public void removeOrder(Order order)
	{
		DataBase.getInstance().removeOrder(order);
	}
	//Create Trailer
	public  Trailer createTrailer(String truckID, String company,String driver,String driverPhNum,Type lType)
	{
		Trailer trailer=new Trailer(truckID, company, driver, driverPhNum, lType);
		DataBase.getInstance().addTrailer(trailer);
		return trailer;
	}
	//Remove trailer(not a use case)
	public void removeTrailer(Trailer trailer)
	{
		DataBase.getInstance().removeTrailer(trailer);
	}
	
	//Maintain trailer information
	public void updateTrailer(Trailer trailer,String company,String driver,String driverPhNum,Type lType )
	{
		trailer.setCompany(company);
		trailer.setDriver(driver);
		trailer.setDriverPhNum(driverPhNum);
		trailer.setlType(lType);
		DataBase.getInstance().addTrailer(trailer);
		
	}
	
	//not a use case
	public LoadingDock createLoadingDock(int dockID,Type lType,Status lStatus)
	{
		LoadingDock loadingDock=new LoadingDock(dockID, lType, lStatus);
		DataBase.getInstance().addLoadingDock(loadingDock);
		return loadingDock;
	}
	//not a use case
	public void removeLoadingDock(LoadingDock loadingDock)
	{
		DataBase.getInstance().removeLoadingDock(loadingDock);
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
	public Load createLoad(Date estStartTime, Date estEndTime,Suborder suborder)
	{
		Load load=new Load(estStartTime, estEndTime);
		suborder.setlLoad(load);
		load.setlSuborder(suborder);
		return load;
	}
	//not a use case
	public void beginLoad(Load load)
	{
		load.setActualBegTime(DU.createDate());
	}

	/**
	 * @author Momo
	 *
	 */
	//Load approved
    public boolean completeLoad(Suborder suborder)
    {
    	boolean  truckReady=true;
    	Trailer trailer =suborder.getlTrailer();
    	if(suborder.getlLoad()!=null)
    	suborder.getlLoad().setCompleted(true);
    	suborder.getlLoad().setActtualEndTime(DU.createDate());
    	
    	for(Suborder s:trailer.getlSuborders())
    	{
    		if(!s.getlLoad().isCompleted())
    			truckReady=false;
 
    	}
    	return truckReady;
    }
        
	/**
	 * @author The smoking ace's
	 *Creates a suborder belonging to order and that is attached to a Trailer 
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
		Suborder suborder=new Suborder(loadingTime, weight, loadingDate);
		suborder.setlTrailer(lTrailer);
		lTrailer.addlSuborders(suborder);
		order.addSuborder(suborder);
		return suborder;
	}
	//not a use case
	public void removeSuborder(Order order, Suborder suborder)
	{
		order.getlSuborder().remove(suborder);
	}
	//Register trailer as available
	public void registerIn(String trailerID, int weightIn,int restTime)
	{
	 ArrayList<Trailer> trailers=DataBase.getInstance().getAllTrailers();
	
	 Trailer foundT = null;
	 for(Trailer t : trailers){
		if(t.getTrailerID().compareTo(trailerID) == 0){
			foundT = t;
		}
	foundT.setRestTime(restTime);
	foundT.setWeighIn(weightIn);
	foundT.setArrivalTime(DU.createDate());
	ArrayList<LoadingDock> loadingDocks=DataBase.getInstance().getAllLoadingDocks();
	
	
	
	for(Suborder s : foundT.getlSuborders()){
		//finding the shortest queue 
		Date plannedDate = DU.createDate();
		LoadingDock appropriateDock = null;
		for (LoadingDock lD : loadingDocks) {
			if(findLastLoad(lD).compareTo(plannedDate)>0)
				{plannedDate= findLastLoad(lD);
				appropriateDock=lD;
				}
			else{
				appropriateDock=loadingDocks.get(0);
			}
		}
		
		Load load=createLoad(DU.createDatePlusMinuts(plannedDate, 5), DU.createDatePlusMinuts(plannedDate, 5+s.getLoadingTime()), s);
		s.setlLoad(load);
		beginLoad(load);
		loadToDock(load, appropriateDock);

		
		
	}
	}
	 
	}
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
				lastLoad=l.getEstEndTime();
		}
		return lastLoad;
	}
	//Register weight of the trailer before departure
	public boolean weightOut(Trailer trailer,int weightOut,int margin)
	{
		int totalWeight = 0;
		
		for(Suborder s : trailer.getlSuborders()){
			totalWeight+= s.getWeight();
			}
		if(totalWeight <= weightOut + margin && totalWeight > weightOut - margin  ){
			trailer.setDeparted(true);
			return true;
		}
		else{
			registerIn(trailer.getTrailerID(), trailer.getWeighIn(), trailer.getRestTime());
			return false;
		}
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
    	
     for(Trailer t: DataBase.getInstance().getAllTrailers())
     {if(!t.isDeparted())
    	 availableTrailers.add(t);
    	 
     }
     
     
     return quicSort(availableTrailers, new TrailerIDComparator());
    }

    //Get list of loading times(from specific loading dock's loads)
    public ArrayList<Load> getLoadsFrom(LoadingDock lDock)
     {ArrayList<Load> loads=new ArrayList<Load>();
      loads= lDock.getlLoad();
      return quicSort(loads, new LoadTimeComparator());
    	
     }
    //Get list of availabale docks
    public ArrayList<LoadingDock> getAvailableDocks()
    {
    	ArrayList<LoadingDock> loadingDocks=new ArrayList<LoadingDock>();
    	for(LoadingDock l:DataBase.getInstance().getAllLoadingDocks())
    	{
    		if(l.getlStatus()!=Status.CLOSED)
    			loadingDocks.add(l);
    	}
    	
    	return quicSort(loadingDocks, new DockIDComparator());
    }
	
	}
