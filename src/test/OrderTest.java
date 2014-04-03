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
import businessmodel.Order;
import businessmodel.user.GarageHolder;

public class OrderTest {
	
	private ArrayList<Order> orders;
	private GarageHolder garageholder;
	private ArrayList<Component> components;
	private Body body;
	private Color color;
	private Engine engine;
	private Gearbox gearbox;
	private Seats seats;
	private Airco airco;
	private Wheels wheels;
	private Date date;

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
		date = new Date();
		orders = new ArrayList<Order>();

		for(int i = 1; i< 30;i++){
			Order temp = new Order(garageholder,this.components);
			temp.setDate(date);
			this.orders.add(temp);
		}
	}

	@Test
	public void test() {
		assertEquals(orders.get(0).getCar().getComponents(),this.components);
		assertEquals(orders.get(0).getUser(),this.garageholder);
		assertEquals(orders.get(0).getDate(),this.date);
		assertEquals(orders.get(0).isCompleted(),false);
		assertEquals(orders.get(0).getUser(),garageholder);
	}

}
