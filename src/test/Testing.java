package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import component.*;
import businessmodel.*;

public class Testing {

	private Car car1;
	private Body body;	private Body body2;
	private Color color; private Color color2;
	private Engine engine; private Engine engine2;
	private Gearbox gearbox; private Gearbox gearbox2;
	private Seats seats; private Seats seats2;
	private Airco airco; private Airco airco2;
	private Wheels wheels; private Wheels wheels2;
	ArrayList<Component> components;

	@Before @Test
	public void setUp() throws Exception {
		body = new Body("sedan",1000);
		color = new Color("red",1000);
		engine = new Engine("standard 2l 4 cilinders",1000);
		gearbox = new Gearbox("6 speed manual",1000);
		seats = new Seats("leather black",1000);
		airco = new Airco("manual",1000);
		wheels = new Wheels("comfort",1000);
		body2 = new Body("sedan",1000);
		color2 = new Color("red",1000);
		engine2 = new Engine("standard 2l 4 cilinders",1000);
		gearbox2 = new Gearbox("6 speed manual",1000);
		seats2 = new Seats("leather black",1000);
		airco2 = new Airco("manual",1000);
		wheels2 = new Wheels("comfort",1000);
		this.components = new ArrayList<Component>();
		car1 = new Car(components);
		
//		// test voor body
//		try {car1 = new Car(null,color,engine,gearbox,seats,airco,wheels);}
//		catch (IllegalArgumentException e) {}
//
//		try {car1 = new Car(color,color,engine,gearbox,seats,airco,wheels);}
//		catch (IllegalArgumentException e) {}
//		
//		// test voor color
//		try {car1 = new Car(body,null,engine,gearbox,seats,airco,wheels);}
//		catch (IllegalArgumentException e) {}
//		
//		try {car1 = new Car(body,body,engine,gearbox,seats,airco,wheels);}
//		catch (IllegalArgumentException e) {}
//
//		// test voor engine
//		try {car1 = new Car(body,color,null,gearbox,seats,airco,wheels);}
//		catch (IllegalArgumentException e) {}
//
//		try {car1 = new Car(body,color,body,gearbox,seats,airco,wheels);}
//		catch (IllegalArgumentException e) {}		
//
//		// test voor gearbox
//		try {car1 = new Car(body,color,engine,null,seats,airco,wheels);}
//		catch (IllegalArgumentException e) {}
//
//		try {car1 = new Car(body,color,engine,body,seats,airco,wheels);}
//		catch (IllegalArgumentException e) {}		
//		
//		// test voor seats
//		try {car1 = new Car(body,color,engine,gearbox,null,airco,wheels);}
//		catch (IllegalArgumentException e) {}
//
//		try {car1 = new Car(body,color,engine,gearbox,body,airco,wheels);}
//		catch (IllegalArgumentException e) {}
//		
//		// test voor airco
//		try {car1 = new Car(body,color,engine,gearbox,seats,null,wheels);}
//		catch (IllegalArgumentException e) {}
//
//		try {car1 = new Car(body,color,engine,gearbox,seats,body,wheels);}
//		catch (IllegalArgumentException e) {}
//		
//		// test voor body
//		try {car1 = new Car(body,color,engine,gearbox,seats,airco,null);}
//		catch (IllegalArgumentException e) {}
//
//		try {car1 = new Car(body,color,engine,gearbox,seats,airco,body);}
//		catch (IllegalArgumentException e) {}
		}

	@Test   // TODO: test voor getType + toString
	public void testComponent() throws Exception {
		assertEquals("sedan",body.getName());
		assertEquals(1000,body.getPrice(),0);

		try {body = new Body(null,1000);}
		catch (IllegalArgumentException e) {}
		
		try {body = new Body("sedan",-1);}
		catch (IllegalArgumentException e) {}
		
		try {body = new Body("sedan",1000);}
		catch (IllegalArgumentException e) {}
	}

	@Test  // TODO:
	public void testCar() throws Exception {

	}
	
	@Test
	public void randomTest() {
		Mechanic mechanic = new Mechanic("Sander","Geijsen","HENK","DE POTVIS");
		Date date = new Date();
		Order order = new Order(mechanic, this.components,date);
		ArrayList<Body> bodies = new ArrayList<Body>();
		bodies.add(body2);
		bodies.add(body);
		ArrayList<Color> colors = new ArrayList<Color>();
		colors.add(color);
		colors.add(color2);
		ArrayList<Engine> engines = new ArrayList<Engine>();
		engines.add(engine);
		engines.add(engine2);
		ArrayList<Gearbox> gearboxes = new ArrayList<Gearbox>();
		gearboxes.add(gearbox);
		gearboxes.add(gearbox2);
		ArrayList<Seats> seatss = new ArrayList<Seats>();
		seatss.add(seats);
		seatss.add(seats2);
		ArrayList<Airco> aircos = new ArrayList<Airco>();
		aircos.add(airco);
		aircos.add(airco2);
		ArrayList<Wheels> wheelss = new ArrayList<Wheels>();
		wheelss.add(wheels);
		wheelss.add(wheels2);
	
		CarModelSpecification cms = new CarModelSpecification(bodies,colors,engines,gearboxes,seatss,aircos,wheelss);
		CarModel audiA6 = new CarModel("Audi A6",cms);
		ArrayList<CarModel> carmodels = new ArrayList<CarModel>();
		carmodels.add(audiA6);
		OrderManagement ordermanager = new OrderManagement(carmodels);
		ordermanager.addOrder(order);
	}
}
