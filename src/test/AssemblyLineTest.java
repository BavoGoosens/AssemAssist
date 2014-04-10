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
import businessmodel.AssemblyLine;
import businessmodel.OrderManager;
import businessmodel.order.Order;
import businessmodel.user.GarageHolder;

public class AssemblyLineTest {
	
	private Body body;
	private Color color;
	private Engine engine;
	private Gearbox gearbox;
	private Seats seats;
	private Airco airco;
	private Wheels wheels;
	
	private GarageHolder garageholder;
	
	private ArrayList<Component> components;
	private OrderManager ordermanager;
	private Date date;
	private ArrayList<Order> orders;

	@Before
	public void setUp() throws Exception {
		
		garageholder = new GarageHolder("Sander","Geijsen","Sander2","henk");
		
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
		
		orders = new ArrayList<Order>();
		
		date = new Date();

		for(int i = 1; i< 30;i++){
			Order temp = new Order(garageholder,this.components);
			temp.setEstimateDate(date);
			this.orders.add(temp);
		}
		
		ordermanager = new OrderManager();
		
	}

	@Test
	public void test() {
		AssemblyLine testassembly = ordermanager.getProductionScheduler().getAssemblyline();
		assertEquals(testassembly.getWorkPosts().get(0).getResponsibletasks().get(0).getActions().get(0).getDescription(),"Paint car");
		testassembly.getWorkPosts().get(0).setNewOrder(orders.get(0));
		testassembly.getWorkPosts().get(1).setNewOrder(orders.get(1));
		testassembly.getWorkPosts().get(2).setNewOrder(orders.get(2));
		testassembly.advance(orders.get(3));
		assertEquals(testassembly.getWorkPosts().get(0).getOrder(),orders.get(3));
		assertEquals(testassembly.getWorkPosts().get(1).getOrder(),orders.get(0));
		assertEquals(testassembly.getWorkPosts().get(2).getOrder(),orders.get(1));
	}

}
