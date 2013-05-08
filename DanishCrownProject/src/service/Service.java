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

import javax.crypto.spec.OAEPParameterSpec;



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
	public Order createOrder(int orderId, int totalWeight, int margin, model.Type lType)
	{
		Order order=new Order(orderId,totalWeight,margin,lType);
		DataBase.getInstance().addOrder(order);
		return order;
	}
	 
	public void updateOrder(Order order,int totalWeight,int margin,Type lType){
		
		order.setlType(lType);
		order.setMargin(margin);
		order.setTotalWeight(totalWeight);
		DataBase.getInstance().addOrder(order);
	}

	public void removeOrder(Order order)
	{
		DataBase.getInstance().removeOrder(order);
	}
	
	public  Trailer createTrailer(String truckID, String company,String driver,String driverPhNum,Type lType)
	{
		Trailer trailer=new Trailer(truckID, company, driver, driverPhNum, lType);
		DataBase.getInstance().addTrailer(trailer);
		return trailer;
	}
	
	public void removeTrailer(Trailer trailer)
	{
		DataBase.getInstance().removeTrailer(trailer);
	}
	
	
	public void updateTrailer(Trailer trailer,String company,String driver,String driverPhNum,Type lType )
	{
		trailer.setCompany(company);
		trailer.setDriver(driver);
		trailer.setDriverPhNum(driverPhNum);
		trailer.setlType(lType);
		DataBase.getInstance().addTrailer(trailer);
		
	}
	public LoadingDock createLoadingDock(int dockID,Type lType,Status lStatus)
	{
		LoadingDock loadingDock=new LoadingDock(dockID, lType, lStatus);
		DataBase.getInstance().addLoadingDock(loadingDock);
		return loadingDock;
	}
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
	public Load createLoad(Date estStartTime, Date estEndTime,Suborder suborder)
	{
		Load load=new Load(estStartTime, estEndTime);
		suborder.setlLoad(load);
		load.setlSuborder(suborder);
		return load;
	}
	
	public void beginLoad(Load load)
	{
		load.setActualBegTime(DU.createDate());
	}

	/**
	 * @author Momo
	 *
	 */
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
	public Suborder createSuborder(int loadingTime,int weight, Date loadingDate,Order order,Trailer lTrailer)
	{
		Suborder suborder=new Suborder(loadingTime, weight, loadingDate);
		suborder.setlTrailer(lTrailer);
		lTrailer.addlSuborders(suborder);
		order.addSuborder(suborder);
		return suborder;
	}
	
	public void removeSuborder(Order order, Suborder suborder)
	{
		order.getlSuborder().remove(suborder);
	}
	
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
	
	public void loadToDock(Load load,LoadingDock loadingDock)
	{
	
			if(load.getlSuborder().getlTrailer().getlType()==loadingDock.getlType() && (loadingDock.getlStatus()==Status.OPEN || loadingDock.getlStatus()==Status.OCCUPIED) )
			{
				loadingDock.addLoad(load);
			}
			loadingDock.setlStatus(Status.OCCUPIED);
	}
	
	public Date findLastLoad(LoadingDock loadingDock)
	{Date lastLoad = DU.createDate();
		for( Load l:loadingDock.getlLoad())
		{
			if(l.getEstEndTime().compareTo(lastLoad)>0)
				lastLoad=l.getEstEndTime();
		}
		return lastLoad;
	}
	
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
	public Status DockStatus(LoadingDock loadingDock)
	{
		return loadingDock.getlStatus();
	}
	

	private  <T> void swap(ArrayList<T> list, int i1, int i2)
	{
		T temp = list.get(i1);
		list.set(i1, list.get(i2));
		list.set(i2, temp);
	}
	
    public <T> ArrayList<T> quicSort(ArrayList<T> list,Comparator<T> comparator) 
    {
    	quicksortRec(list, comparator, 0, list.size()-1);
    	
		return list;
 
    }
    
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
    
    public ArrayList<Trailer> getAvailbleTrailers()
    {ArrayList<Trailer> availableTrailers = new ArrayList<Trailer>();
    	
     for(Trailer t: DataBase.getInstance().getAllTrailers())
     {if(!t.isDeparted())
    	 availableTrailers.add(t);
    	 
     }
     
     
     return quicSort(availableTrailers, new TrailerIDComparator());
    }

    
    public ArrayList<Load> getLoadsFrom(LoadingDock lDock)
     {ArrayList<Load> loads=new ArrayList<Load>();
      loads= lDock.getlLoad();
      return quicSort(loads, new LoadTimeComparator());
    	
     }
    
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
