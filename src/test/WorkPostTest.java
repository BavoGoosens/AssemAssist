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
import businessmodel.Order;
import businessmodel.WorkPost;
import businessmodel.user.GarageHolder;

public class WorkPostTest {
	
	private GarageHolder garageholder;
	private Body body;
	private Color color;
	private Engine engine;
	private Gearbox gearbox;
	private Seats seats;
	private Airco airco;
	private Wheels wheels;
	private ArrayList<AssemblyTask> tasks;
	private ArrayList<Order> orders;
	private Date date;
	private ArrayList<Component> components;

	@Before
	public void setUp() throws Exception {
		garageholder = new GarageHolder("Sander","Geijsen","Sander2","henk");
		tasks = new ArrayList<AssemblyTask>();
		orders = new ArrayList<Order>();
		body = new Body("sedan",1000);
		color = new Color("red",1000);
		engine = new Engine("standard 2l 4 cilinders",1000);
		gearbox = new Gearbox("6 speed manual",1000);
		seats = new Seats("leather black",1000);
		airco = new Airco("manual",1000);
		wheels = new Wheels("comfort",1000);
		
		date = new Date();
		
		this.components = new ArrayList<Component>();
		this.components.add(body);
		this.components.add(color);
		this.components.add(engine);
		this.components.add(gearbox);
		this.components.add(seats);
		this.components.add(airco);
		this.components.add(wheels);

		for(int i = 1; i< 30;i++){
			Order temp = new Order(garageholder,this.components);
			temp.setDate(date);
			this.orders.add(temp);
		}
	}

	@Test
	public void test() {
		WorkPost wp = new WorkPost("Test",tasks);
		wp.setNewOrder(orders.get(0));
		assertEquals(wp.getOrder(),orders.get(0));
	}

}
