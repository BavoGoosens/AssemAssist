package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import component.*;
import control.Controller;
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
	private GarageHolder garageholder;
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

	Action actie;
	ArrayList<Action> acties;
	AssemblyTask assem;
	ArrayList<AssemblyTask> tasks;
	private CarModelSpecification cms;
	private CarModel audiA6;
	private ArrayList<CarModel> carmodels;
	private OrderManager ordermanager;
	private Calendar calendar;


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
		garageholder = new GarageHolder("Sander","Geijsen","HENK","DE POTVIS");
		date = new Date();

		order = new Order(garageholder, this.components);
		order.setDate(calendar);
		bodies = new ArrayList<Body>();
		colors = new ArrayList<Color>();
		engines = new ArrayList<Engine>();
		gearboxes = new ArrayList<Gearbox>();
		seatss = new ArrayList<Seats>();
		aircos = new ArrayList<Airco>();
		wheelss = new ArrayList<Wheels>();

		try {cms = new CarModelSpecification(null,colors,engines,gearboxes,seatss,aircos,wheelss);} 
		catch (IllegalArgumentException e) {}
		try {cms = new CarModelSpecification(bodies,null,engines,gearboxes,seatss,aircos,wheelss);} 
		catch (IllegalArgumentException e) {}
		try {cms = new CarModelSpecification(bodies,colors,null,gearboxes,seatss,aircos,wheelss);} 
		catch (IllegalArgumentException e) {}
		try {cms = new CarModelSpecification(bodies,colors,engines,null,seatss,aircos,wheelss);} 
		catch (IllegalArgumentException e) {}
		try {cms = new CarModelSpecification(bodies,colors,engines,gearboxes,null,aircos,wheelss);} 
		catch (IllegalArgumentException e) {}
		try {cms = new CarModelSpecification(bodies,colors,engines,gearboxes,seatss,null,wheelss);} 
		catch (IllegalArgumentException e) {}
		try {cms = new CarModelSpecification(bodies,colors,engines,gearboxes,seatss,aircos,null);} 
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
		ordermanager = new OrderManager(carmodels);
		ordermanager.addOrder(order);

		actie = new Action("Henk");
		acties = new ArrayList<Action>();
		assem = new AssemblyTask("Assembly task",acties);
		tasks = new ArrayList<AssemblyTask>();
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

	//	@Test
	//	public void testUI(){
	//		Controller ctrl = new Controller();
	//		ctrl.run();
	//		System.out.println("HENK");
	//		System.out.println("DE POTVIS");
	//		
	//	}


	// A test method for the class Order.
	@Test
	public void testOrder(){
		assertEquals(order.getCar().getComponents(),this.components);
		assertEquals(order.getUser(),this.mechanic);
		assertEquals(order.getDate(),this.date);
		assertEquals(order.isCompleted(),false);
	}

	@Test
	public void testWorkPost(){
		WorkPost wp = new WorkPost("Test",tasks);
		wp.setOrder(order);
		wp.moveAlong(order);

	}

	@Test
	public void testAssemblyLine(){
		AssemblyLine assem1 = new AssemblyLine();
		assem1.getWorkposts().get(0).setOrder(order);
		assem1.advance(order);
	}

	@Test
	public void testShit(){
		System.out.println(this.order.toString());
		System.out.println(this.mechanic.toString());
		System.out.println(this.ordermanager.toString());
		assertEquals(this.cms.getPosibilities().get(0)[0].getClass(),cms.getBodies().get(0).getClass());
	}

	// A test method for the class production Scheduler.
	@Test
	public void testProductionScheduler(){
		ProductionScheduler prodsched = ordermanager.getProductionScheduler();
		assertEquals(prodsched.getOrderManager(),ordermanager);
		prodsched.makeDaySchedule();
		ordermanager.addOrder(order);
		prodsched.advance(50);
		assertEquals(prodsched.getAvailableTime(),790);
		assertEquals(prodsched.getDelayTime(),-10);
		prodsched.advance(90);
		assertEquals(prodsched.getAvailableTime(),700);
		prodsched.advance(60);
		prodsched.advance(90);
		assertEquals(prodsched.getDelayTime(),20);
		assertEquals(order.isCompleted(),false);
		assertEquals(prodsched.getAvailableTime(),840);
	}
}
