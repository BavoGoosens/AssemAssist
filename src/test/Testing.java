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
	private Mechanic mechanic;
	private Date date;
	private Order order;
	private ArrayList<Component> components;
	private ArrayList<Body> bodies;
	private ArrayList<Color> colors;
	private ArrayList<Engine> engines;
	private ArrayList<Airco> aircos;
	private ArrayList<Gearbox> gearboxes;
	private ArrayList<Wheels> wheelss;
	private ArrayList<Seats> seatss;

	private CarModelSpecification cms;
	private CarModel audiA6;
	private ArrayList<CarModel> carmodels;
	private OrderManagement ordermanager;


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
		mechanic = new Mechanic("Sander","Geijsen","HENK","DE POTVIS");
		date = new Date();
		order = new Order(mechanic, this.components,date);


		bodies = new ArrayList<Body>();
		colors = new ArrayList<Color>();
		engines = new ArrayList<Engine>();
		gearboxes = new ArrayList<Gearbox>();
		seatss = new ArrayList<Seats>();
		aircos = new ArrayList<Airco>();
		wheelss = new ArrayList<Wheels>();

		try {cms = new CarModelSpecification(bodies,colors,engines,gearboxes,seatss,aircos,wheelss);} 
		catch (IllegalArgumentException e) {}

		bodies.add(body2);
		bodies.add(body);
		colors.add(color);
		colors.add(color2);
		engines.add(engine);
		engines.add(engine2);
		gearboxes.add(gearbox);
		gearboxes.add(gearbox2);
		seatss.add(seats);
		seatss.add(seats2);
		aircos.add(airco);
		aircos.add(airco2);
		wheelss.add(wheels);
		wheelss.add(wheels2);

		cms = new CarModelSpecification(bodies,colors,engines,gearboxes,seatss,aircos,wheelss);
		audiA6 = new CarModel("Audi A6",cms);
		carmodels = new ArrayList<CarModel>();
		carmodels.add(audiA6);
		ordermanager = new OrderManagement(carmodels);
		ordermanager.addOrder(order);
	}

	// A test method for the class Component
	@Test
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

	// A test method for the class Car.
	@Test
	public void CarTest(){
		assertEquals(this.components,car1.getComponents());
		Body bodytest = new Body("test",500);
		car1.removeComponent(bodytest);
		car1.addComponent(bodytest);
		car1.removeComponent(bodytest);
	}
	// A test method to test the class CarModel.
	@Test
	public void CarModelTest(){
		assertEquals("Audi A6",this.audiA6.getCarmodel());
		assertEquals(this.cms,audiA6.getCarModelSpecification());
	}

	// A test method to test the class CarModelSpecification.
	@Test
	public void testCarModelSpecification(){
		assertEquals(cms.getBodies(),this.bodies);
		assertEquals(cms.getColors(),this.colors);
		assertEquals(cms.getEngines(),this.engines);
		assertEquals(cms.getAircos(),this.aircos);
		assertEquals(cms.getGearboxes(),this.gearboxes);
		assertEquals(cms.getSeats(),this.seatss);
		assertEquals(cms.getWheels(),this.wheelss);
	}
}
