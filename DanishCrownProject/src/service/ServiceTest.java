package service;

import static org.junit.Assert.*;

import java.util.Date;

import model.LoadingDock;
import model.Order;
import model.Status;
import model.Suborder;
import model.Trailer;
import model.Type;

import org.junit.Before;
import org.junit.Test;

/**
 * @author tombernold
 *
 */
public class ServiceTest {
	
	Service service=Service.getInstance();
	LoadingDock loadingD=service.createLoadingDock(1, Type.BOX, Status.OPEN);
	LoadingDock loadingDOcc=service.createLoadingDock(2, Type.BOX, Status.OCCUPIED);
	LoadingDock loadingDCl=service.createLoadingDock(3, Type.BOX, Status.CLOSED);
	Order o1=service.createOrder(1, 2000, 10, Type.BOX);
	Trailer t1=service.createTrailer("1", "Cool Company", "Bob Smith", "23123", Type.BOX);
	Suborder s1=service.createSuborder(30, 14000, DU.createDate(), o1, t1);
	Trailer t2=service.createTrailer("10", "Silver Star", "Joe Doe", "67676", Type.BOX);
	Suborder s2=service.createSuborder(25, 200, DU.createDate(), o1, t2);
	Trailer t3=service.createTrailer("5", "Filth Bar", "Joseph Blot", "45454", Type.BOX);
	Date now=DU.createDate();
	Date after4hours=DU.createDatePlusMinuts(now, 240);
	Date inahour=DU.createDatePlusMinuts(now, 60);
	
	
	@Before
	public void setUp() throws Exception {
		
	}
	/**
	 * @author tombernold
	 *Fills in trailer ID, fills in rest time, fills in phone number
	 */
	@Test
	public void testRegisterIn() {
		Service.getInstance().registerIn("1", 2, 30, "3456");
		for(Suborder s : t1.getlSuborders())
		assertFalse(s.getlLoad()== null);
	
		
	}
	/**
	 * @author tombernold
	 * Wrong Id entry
	 */
	@Test
	public void testRegisterIn_2(){
		Service.getInstance().registerIn("14", 3, 24, "4444");
		for(Suborder s : t2.getlSuborders())
			assertTrue(s.getlLoad()== null);
		
		
		
	}
	
	/**
	 * @author tombernold
	 * Test if rest time was invalid
	 */
	@Test
	public void testRegisterIn_3(){
		
		
			assertTrue(Service.getInstance().registerIn("5", 3, -1, "4444")==false);
}
	/**
	 * @author tombernold
	 * Test if to many characters were entered
	 */ 
	@Test
	public void testRegisterIn_4(){
		
		
		assertFalse(Service.getInstance().registerIn("5", 12000, 35, "222222222222222222222222222222"));
		
	}
	
	/**
	 * @author tombernold
	 * Test if fields are blank
	 */
	@Test
	public void testRegisterIn_5(){
		
		
		assertFalse(Service.getInstance().registerIn("5", 12000, 35, ""));
		
	}
	
	
	@Test
	public void testRegisterIn_Test_1(){
		assertTrue(Service.getInstance().registerIn("1", 1000, 50, "3"));
	}
	@Test
	public void testRegisterIn_Test_2(){
		assertFalse(Service.getInstance().registerIn("1", 1000, 50, "8778876778744449"));
	}
	@Test
	public void testRegisterIn_Test_3(){
		assertFalse(Service.getInstance().registerIn("1", 1000, -10, "3"));
	}
	@Test
	public void testRegisterIn_Test_4(){
		assertFalse(Service.getInstance().registerIn("1", 1000, -10, "8778876778744449"));
	}
	@Test
	public void testRegisterIn_Test_5(){
		assertFalse(Service.getInstance().registerIn("1", 1000, 250, "3"));
	}
	@Test
	public void testRegisterIn_Test_6(){
		assertFalse(Service.getInstance().registerIn("1", 1000, 250, "8778876778744449"));
	}
	@Test
	public void testRegisterIn_Test_7(){
		assertFalse(Service.getInstance().registerIn("1", -1000, 50, "3"));
	}
	@Test
	public void testRegisterIn_Test_8(){
		assertFalse(Service.getInstance().registerIn("1", -1000, 50, "8778876778744449"));
	}
	@Test
	public void testRegisterIn_Test_9(){
		assertFalse(Service.getInstance().registerIn("1", -1000, -10, "3"));
	}
	@Test
	public void testRegisterIn_Test_10(){
		assertFalse(Service.getInstance().registerIn("1", -1000, -10, "8778876778744449"));
	}
	@Test
	public void testRegisterIn_Test_11(){
		assertFalse(Service.getInstance().registerIn("1", -1000, 250, "3"));
	}
	@Test
	public void testRegisterIn_Test_12(){
		assertFalse(Service.getInstance().registerIn("1", -1000, 250, "8778876778744449"));
	}
	@Test
	public void testRegisterIn_Test_13(){
		assertFalse(Service.getInstance().registerIn("1", 30000, 50, "3"));
	}
	@Test
	public void testRegisterIn_Test_14(){
		assertFalse(Service.getInstance().registerIn("1", 30000, 50, "8778876778744449"));
	}
	@Test
	public void testRegisterIn_Test_15(){
		assertFalse(Service.getInstance().registerIn("1", 30000, -10, "3"));
	}
	@Test
	public void testRegisterIn_Test_16(){
		assertFalse(Service.getInstance().registerIn("1", 30000, -10, "8778876778744449"));
	}
	@Test
	public void testRegisterIn_Test_17(){
		assertFalse(Service.getInstance().registerIn("1", 30000, 250, "3"));
	}
	@Test
	public void testRegisterIn_Test_18(){
		assertFalse(Service.getInstance().registerIn("1", 30000, 250, "8778876778744449"));
	}
	@Test
	public void testRegisterIn_Test_19(){
		assertFalse(Service.getInstance().registerIn("2222222222", 1000, 50, "3"));
	}
	@Test
	public void testRegisterIn_Test_20(){
		assertFalse(Service.getInstance().registerIn("2222222222", 1000, 50, "8778876778744449"));
	}
	@Test
	public void testRegisterIn_Test_21(){
		assertFalse(Service.getInstance().registerIn("2222222222", 1000, -10, "3"));
	}
	@Test
	public void testRegisterIn_Test_22(){
		assertFalse(Service.getInstance().registerIn("2222222222", 1000, -10, "8778876778744449"));
	}
	@Test
	public void testRegisterIn_Test_23(){
		assertFalse(Service.getInstance().registerIn("2222222222", 1000, 250, "3"));
	}
	@Test
	public void testRegisterIn_Test_24(){
		assertFalse(Service.getInstance().registerIn("2222222222", 1000, 250, "8778876778744449"));
	}
	@Test
	public void testRegisterIn_Test_25(){
		assertFalse(Service.getInstance().registerIn("2222222222", -1000, 50, "3"));
	}
	@Test
	public void testRegisterIn_Test_26(){
		assertFalse(Service.getInstance().registerIn("2222222222", -1000, 50, "8778876778744449"));
	}
	@Test
	public void testRegisterIn_Test_27(){
		assertFalse(Service.getInstance().registerIn("2222222222", -1000, -10, "3"));
	}
	@Test
	public void testRegisterIn_Test_28(){
		assertFalse(Service.getInstance().registerIn("2222222222", -1000, -10, "8778876778744449"));
	}
	@Test
	public void testRegisterIn_Test_29(){
		assertFalse(Service.getInstance().registerIn("2222222222", -1000, 250, "3"));
	}
	@Test
	public void testRegisterIn_Test_30(){
		assertFalse(Service.getInstance().registerIn("2222222222", -1000, 250, "8778876778744449"));
	}
	@Test
	public void testRegisterIn_Test_31(){
		assertFalse(Service.getInstance().registerIn("2222222222", 30000, 50, "3"));
	}
	@Test
	public void testRegisterIn_Test_32(){
		assertFalse(Service.getInstance().registerIn("2222222222", 30000, 50, "8778876778744449"));
	}
	@Test
	public void testRegisterIn_Test_33(){
		assertFalse(Service.getInstance().registerIn("2222222222", 30000, -10, "3"));
	}
	@Test
	public void testRegisterIn_Test_34(){
		assertFalse(Service.getInstance().registerIn("2222222222", 30000, -10, "8778876778744449"));
	}
	@Test
	public void testRegisterIn_Test_35(){
		assertFalse(Service.getInstance().registerIn("2222222222", 30000, 250, "3"));
	}
	@Test
	public void testRegisterIn_Test_36(){
		assertFalse(Service.getInstance().registerIn("2222222222", 30000, 250, "8778876778744449"));
	}
	@Test
	public void testCreateLoad_Test_1(){
		
		assertTrue(Service.getInstance().createLoad(now, now, s1, loadingD)==null);
	}
	@Test
	public void testCreateLoad_Test_2(){
		assertFalse(Service.getInstance().createLoad(now, after4hours, s1, loadingD)==null);
	}
	@Test
	public void testCreateLoad_Test_3(){
		assertFalse(Service.getInstance().createLoad(now, inahour, s1, loadingD)==null);
	}
	@Test
	public void testCreateLoad_Test_4(){
		
		assertTrue(Service.getInstance().createLoad(now, now, s1, loadingDOcc)==null);
	}
	@Test
	public void testCreateLoad_Test_5(){
		assertFalse(Service.getInstance().createLoad(now, after4hours, s1, loadingDOcc)==null);
	}
	@Test
	public void testCreateLoad_Test_6()
	{	
		assertFalse(Service.getInstance().createLoad(now, inahour, s1, loadingDOcc)==null);
		}
	
		@Test
		public void testCreateLoad_Test_7(){
			
			assertTrue(Service.getInstance().createLoad(now, now, s1, loadingDCl)==null);}
		

		@Test
		public void testCreateLoad_Test_8(){
			assertTrue(Service.getInstance().createLoad(now, after4hours, s1, loadingDCl)==null);}
		
		
		@Test
		public void testCreateLoad_Test_9(){
			assertTrue(Service.getInstance().createLoad(now, inahour, s1, loadingDCl)==null);
			}
	@Test
	public void testWeightOut_Test_1(){
		t1.setWeighIn(10000);
		s1.setWeight(14000);
		o1.setMargin(10);
			assertTrue(Service.getInstance().weightOut(t1, 24000, 10));
	}
	
	
	@Test
	public void testWeightOut_Test_2(){
		t1.setWeighIn(10000);
		s1.setWeight(14000);
		o1.setMargin(-50);
			assertFalse(Service.getInstance().weightOut(t1, 24000, -50));
	}
	@Test
	public void testWeightOut_Test_3(){
		t1.setWeighIn(10000);
		s1.setWeight(14000);
		o1.setMargin(10);
		assertFalse(Service.getInstance().weightOut(t1, 30000, 10));
	}
	@Test
	public void testWeightOut_Test_4(){
		t1.setWeighIn(10000);
		s1.setWeight(14000);
		o1.setMargin(-50);
		assertFalse(Service.getInstance().weightOut(t1, 30000, -50));
	}
	@Test
	public void testWeightOut_Test_5(){
		t1.setWeighIn(20000);
		s1.setWeight(14000);
		o1.setMargin(10);
		assertFalse(Service.getInstance().weightOut(t1, 24000, 10));
	}
	@Test
	public void testWeightOut_Test_6(){
		t1.setWeighIn(20000);
		s1.setWeight(14000);
		o1.setMargin(-50);
		assertFalse(Service.getInstance().weightOut(t1, 24000, -50));
	}
	@Test
	public void testWeightOut_Test_7(){
		t1.setWeighIn(20000);
		s1.setWeight(14000);
		o1.setMargin(10);
		assertFalse(Service.getInstance().weightOut(t1, 30000, 10));
	}
	@Test
	public void testWeightOut_Test_8(){
		t1.setWeighIn(20000);
		s1.setWeight(14000);
		o1.setMargin(-50);
		assertFalse(Service.getInstance().weightOut(t1, 30000, -50));
	}
	
	
	
}
