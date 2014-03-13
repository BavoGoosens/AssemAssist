package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Car;
import component.Airco;
import component.Body;
import component.Color;
import component.Component;
import component.Engine;
import component.Gearbox;
import component.Seats;
import component.Wheels;

public class CarTest {
	
	private Body body;
	private Color color;
	private Engine engine;
	private Gearbox gearbox;
	private Seats seats;
	private Airco airco;
	private Wheels wheels;
	private Car car1;
	private ArrayList<Component> components;

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
		car1 = new Car(components);
	}

	@Test
	public void test() {
		assertEquals(this.components,car1.getComponents());
		Body bodytest = new Body("test",500);
		car1.removeComponent(bodytest);
		car1.addComponent(bodytest);
		car1.removeComponent(bodytest);
		assertEquals(car1.toString(),"components= [name= sedan, price= "+1000.0+","
				+ " name= red, price= "+1000.0+", name= standard 2l 4 cilinders, price= "
						+ ""+1000.0+", name= 6 speed manual, price= "+1000.0+", name= leather black, "
								+ "price= "+1000.0+", name= manual, price= "+1000.0+", "
										+ "name= comfort, price= "+1000.0+"]");
	}

}
