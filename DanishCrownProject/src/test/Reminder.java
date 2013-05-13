package test;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

import dao.DataBase;

import model.Load;
import model.LoadingDock;
import model.Order;
import model.Status;
import model.Suborder;
import model.Trailer;
import model.Type;

import service.DU;
import service.LoadTimeComparator;
import service.Service;

	
public class Reminder {
    Timer timer;

    public Reminder(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
	
    }

    class RemindTask extends TimerTask {
        public void run() {
           
            timer.cancel(); //Terminate the timer thread
        }
    }
	

	

public static void main(String[] args) throws InterruptedException {
	new Reminder(1);
	

	Service service=Service.getInstance();
	LoadingDock loadingD=service.createLoadingDock(1, Type.BOX, Status.OPEN);
	Order o1=service.createOrder(1, 2000, 10, Type.BOX);
	Trailer t1=service.createTrailer("1", "Cock", "Douche", "23123", Type.BOX);
	Suborder s1=service.createSuborder(30, 200, DU.createDate(), o1, t1);
	Suborder s2=service.createSuborder(25, 200, DU.createDate(), o1, t1);
	service.registerIn("1", 100, 30);
	Trailer t2=service.createTrailer("5", "Cock", "Douche", "23123", Type.CHRISTMAS_TREE);
	Trailer t3=service.createTrailer("2", "Cock", "Douche", "23123", Type.BIN);
	service.beginLoad(s1.getlLoad());
	service.beginLoad(s2.getlLoad());
	for(LoadingDock l:DataBase.getInstance().getAllLoadingDocks() )
		
		//System.out.println(l.getlLoad());

	service.completeLoad(s1);
	//System.out.println(s1.getlLoad());
	Comparator c=new LoadTimeComparator();
	
//	loadingD.addLoad(new Load(DU.createDate(), DU.createDate()));
	System.out.println(loadingD.getlLoad());
	Thread.sleep(1000);
for(Object l: service.quicSort(loadingD.getlLoad(),c))
		System.out.println(l.toString());
		//System.out.println(l.getlLoad());
	System.out.println();
	System.out.println(service.getAvailbleTrailers());
	
	
}


}