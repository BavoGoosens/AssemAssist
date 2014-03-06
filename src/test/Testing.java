package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import component.Component;

import businessmodel.Car;


public class Testing {

	private Car car1;
	private Component body,color,engine,gearbox,seats,airco,wheels;

	@Before @Test
	public void setUp() throws Exception {
		body = new Component("sedan",1000);
		color = new Component("red",1000);
		engine = new Component("standard 2l 4 cilinders",1000);
		gearbox = new Component("6 speed manual",1000);
		seats = new Component("leather black",1000);
		airco = new Component("manual",1000);
		wheels = new Component("comfort",1000);
		car1 = new Car(body,color,engine,gearbox,seats,airco,wheels);
		
		// test voor body
		try {car1 = new Car(null,color,engine,gearbox,seats,airco,wheels);}
		catch (IllegalArgumentException e) {}

		try {car1 = new Car(color,color,engine,gearbox,seats,airco,wheels);}
		catch (IllegalArgumentException e) {}
		
		// test voor color
		try {car1 = new Car(body,null,engine,gearbox,seats,airco,wheels);}
		catch (IllegalArgumentException e) {}
		
		try {car1 = new Car(body,body,engine,gearbox,seats,airco,wheels);}
		catch (IllegalArgumentException e) {}

		// test voor engine
		try {car1 = new Car(body,color,null,gearbox,seats,airco,wheels);}
		catch (IllegalArgumentException e) {}

		try {car1 = new Car(body,color,body,gearbox,seats,airco,wheels);}
		catch (IllegalArgumentException e) {}		

		// test voor gearbox
		try {car1 = new Car(body,color,engine,null,seats,airco,wheels);}
		catch (IllegalArgumentException e) {}

		try {car1 = new Car(body,color,engine,body,seats,airco,wheels);}
		catch (IllegalArgumentException e) {}		
		
		// test voor seats
		try {car1 = new Car(body,color,engine,gearbox,null,airco,wheels);}
		catch (IllegalArgumentException e) {}

		try {car1 = new Car(body,color,engine,gearbox,body,airco,wheels);}
		catch (IllegalArgumentException e) {}
		
		// test voor airco
		try {car1 = new Car(body,color,engine,gearbox,seats,null,wheels);}
		catch (IllegalArgumentException e) {}

		try {car1 = new Car(body,color,engine,gearbox,seats,body,wheels);}
		catch (IllegalArgumentException e) {}
		
		// test voor body
		try {car1 = new Car(body,color,engine,gearbox,seats,airco,null);}
		catch (IllegalArgumentException e) {}

		try {car1 = new Car(body,color,engine,gearbox,seats,airco,body);}
		catch (IllegalArgumentException e) {}
	}

	@Test   // TODO: test voor getType + toString
	public void testComponent() throws Exception {
		assertEquals("sedan",body.getName());
		assertEquals(1000,body.getPrice(),0);

		//assertEauals(Component.Type.BODY,body.getType());

		try {body = new Component(null,1000);}
		catch (IllegalArgumentException e) {}
		
		try {body = new Component("sedan",-1);}
		catch (IllegalArgumentException e) {}
		
		try {body = new Component("sedan",1000);}
		catch (IllegalArgumentException e) {}

		//assertEquals("BODY: sedan ("+1000+" euro)",body.toString());
	}

	@Test  // TODO: testen voor de klasse Car
	public void testCar() throws Exception {

	}
	
	public void ranDomTest() {
		
	}
}
