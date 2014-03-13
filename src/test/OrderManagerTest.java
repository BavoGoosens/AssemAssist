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
import businessmodel.GarageHolder;
import businessmodel.Order;
import businessmodel.OrderManager;

public class OrderManagerTest {
	
	private OrderManager om;
	private GarageHolder garageholder;
	private ArrayList<Component> components;
	private Body body;
	private Color color;
	private Engine engine;
	private Gearbox gearbox;
	private Seats seats;
	private Airco airco;
	private Wheels wheels;
	private Order order;
	private Order order2;

	@Before
	public void setUp() throws Exception {
		om = new OrderManager();
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
		order = new Order(garageholder, components);
		order2 = new Order(garageholder, components);
	}

	@Test
	public void test() {
		om.addOrder(order);
		assertTrue(om.getPendingOrders().contains(order));
		assertTrue(om.getPendingOrders(garageholder).contains(order));
		om.finishedOrder(order);
		assertTrue(om.getCompletedOrders().contains(order));
		assertTrue(om.getCompletedOrders(garageholder).contains(order));
		assertTrue(om.getOrders().contains(order));
	}

}
