package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import component.Airco;
import component.Body;
import component.Color;
import component.Component;
import component.Engine;
import component.Gearbox;
import component.Seats;
import component.Wheels;
import businessmodel.AssemblyTask;
import businessmodel.GarageHolder;
import businessmodel.Order;
import businessmodel.OrderManager;
import businessmodel.ProductionScheduler;
import businessmodel.WorkPost;

public class ProductionSchedulerTest {
	
	private Body body;
	private Color color;
	private Engine engine;
	private Gearbox gearbox;
	private Seats seats;
	private Airco airco;
	private Wheels wheels;
	private ArrayList<Component> components;
	
	private Date date;
	
	private GarageHolder garageholder;
	private ArrayList<Order> orders;
	private OrderManager ordermanager;

	@Before
	public void setUp() throws Exception {
		body = new Body("sedan",1000);
		color = new Color("red",1000);
		engine = new Engine("standard 2l 4 cilinders",1000);
		gearbox = new Gearbox("6 speed manual",1000);
		seats = new Seats("leather black",1000);
		airco = new Airco("manual",1000);
		wheels = new Wheels("comfort",1000);
		this.components = new ArrayList<Component>();
		this.components.add(body);
		this.components.add(color);
		this.components.add(engine);
		this.components.add(gearbox);
		this.components.add(seats);
		this.components.add(airco);
		this.components.add(wheels);
		date = new Date();
		
		garageholder = new GarageHolder("Sander","Geijsen","Sander2","henk");
		
		orders = new ArrayList<Order>();

		for(int i = 1; i< 30;i++){
			Order temp = new Order(garageholder,this.components);
			temp.setDate(date);
			this.orders.add(temp);
		}
		ordermanager = new OrderManager();
	}

	@SuppressWarnings("unused")
	@Test
	public void test() {
		ProductionScheduler prodsched = ordermanager.getProductionScheduler();
		assertTrue(prodsched.getFutureAssemblyTasks().isEmpty());
		for(Order order: this.orders){
			ordermanager.placeOrder(order);
		}
		assertTrue(!prodsched.getFutureAssemblyTasks().isEmpty());
		assertEquals(orders.get(0),prodsched.getScheduledOrders().get(0));
		prodsched.advance(60);
		for(Order order:this.orders){
			for(WorkPost wp: prodsched.getAssemblyline().getWorkPosts()){
				if(wp.getOrder() != null){
					for(AssemblyTask task: wp.getPendingTasks())
					task.completeAssemblytask();
				}
			}
			prodsched.advance(60); 
		}
		
		for(Order order:this.orders){
			for(WorkPost wp: prodsched.getAssemblyline().getWorkPosts()){
				if(wp.getOrder() != null){
					for(AssemblyTask task: wp.getPendingTasks())
					task.completeAssemblytask();
				}
			}
			prodsched.advance(40);
		}
		for(Order order:this.orders){
			for(WorkPost wp: prodsched.getAssemblyline().getWorkPosts()){
				if(wp.getOrder() != null){
					for(AssemblyTask task: wp.getPendingTasks())
					task.completeAssemblytask();
				}
			}
			prodsched.advance(80);
		}
		assertTrue(prodsched.getFutureAssemblyTasks().isEmpty());
	}

}
