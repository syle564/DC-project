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

    public class RemindTask extends TimerTask {
        public void run() {
           //System.out.println("lol");
            timer.cancel(); //Terminate the timer thread
        }
    }

	

public static void main(String[] args) throws InterruptedException {
	new Reminder(1);
	
	

	Service service=Service.getInstance();
//	LoadingDock loadingD=service.createLoadingDock(1, Type.BOX, Status.OPEN);
//	Order o1=service.createOrder(1, 2000, 10, Type.BOX);
//	Trailer t1=service.createTrailer("1", "Cock", "Douche", "23123", Type.BOX);
//	Suborder s1=service.createSuborder(30, 200, DU.createDate(), o1, t1);
//	Suborder s2=service.createSuborder(25, 200, DU.createDate(), o1, t1);
//	//service.registerIn("1", 100, 30);
Trailer t2=service.createTrailer("7", "Cock", "Douche", "23123", Type.CHRISTMAS_TREE);
//	Trailer t4=service.createTrailer("4", "Cock", "Douche", "23123", Type.CHRISTMAS_TREE);
//	Trailer t3=service.createTrailer("2", "Cock", "Douche", "23123", Type.BIN);
//	Service service=Service.getInstance();
	//LoadingDock loadingD=service.createLoadingDock(1, Type.BOX, Status.OPEN);
//	Order o1=service.createOrder(1, 2000, 10, Type.BOX);
//	Trailer t1=service.createTrailer("1", "Cock", "Douche", "23123", Type.BOX);
//	Suborder s1=service.createSuborder(30, 200, DU.createDate(), o1, t1);
//	Trailer t2=service.createTrailer("600", "Cock", "Douche", "23123", Type.BOX);
//	Trailer t3=service.createTrailer("5", "Cock", "Douche", "23123", Type.BOX);
//	Trailer t4=service.createTrailer("4", "Cock", "Douche", "23123", Type.CHRISTMAS_TREE);
//	Suborder s2=service.createSuborder(25, 200, DU.createDate(), o1, t2);
//
service.updateTrailer(t2, "7","dsds", "SDs", "sdsd", Type.BOX);
System.out.println(service.getAvailbleTrailers());

//	
	
	
}


}