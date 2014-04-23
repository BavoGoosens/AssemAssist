package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;


import businessmodel.*;
import businessmodel.category.*;
import businessmodel.order.Order;
import businessmodel.user.GarageHolder;
import businessmodel.user.Manager;
import businessmodel.user.Mechanic;

public class BusinessModelTest {

	private Body body;	private Body body2;
	private Color color; private Color color2;
	private Engine engine; private Engine engine2;
	private Gearbox gearbox; private Gearbox gearbox2;
	private Seats seats; private Seats seats2;
	private Airco airco; private Airco airco2;
	private Wheels wheels; private Wheels wheels2;
	private Car car1;
	
	private Mechanic mechanic; private GarageHolder garageholder; private Manager manager;
	
	private Date date;
	
	private ArrayList<Order> orders;

	private ArrayList<Component> components;
	private ArrayList<Body> bodies;
	private ArrayList<Color> colors;
	private ArrayList<Engine> engines;
	private ArrayList<Airco> aircos;
	private ArrayList<Gearbox> gearboxes;
	private ArrayList<Wheels> wheelss;
	private ArrayList<Seats> seatss;

	Action action1;
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
		this.components.add(body);
		this.components.add(color);
		this.components.add(engine);
		this.components.add(gearbox);
		this.components.add(seats);
		this.components.add(airco);
		this.components.add(wheels);
		
		car1 = new Car(components);
		
		mechanic = new Mechanic("Sander","Geijsen","Sander1");
		garageholder = new GarageHolder("Sander","Geijsen","Sander2");
		manager = new Manager("Sander","Geijsen","Sander3");
		
		date = new Date();

		bodies = new ArrayList<Body>();
		colors = new ArrayList<Color>();
		engines = new ArrayList<Engine>();
		gearboxes = new ArrayList<Gearbox>();
		seatss = new ArrayList<Seats>();
		aircos = new ArrayList<Airco>();
		wheelss = new ArrayList<Wheels>();

		try {cms = new CarModelSpecification(null);} 
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

		orders = new ArrayList<Order>();

		for(int i = 1; i< 30;i++){
			Order temp = new Order(garageholder,this.components);
			temp.setEstimateDate(date);
			this.orders.add(temp);
		}
		
		try {Order temp = new Order(mechanic,this.components);}
		catch (IllegalArgumentException e) {
			
		}

		cms = new CarModelSpecification(bodies,colors,engines,gearboxes,seatss,aircos,wheelss);
		audiA6 = new CarModel("Audi A6",cms);
		carmodels = new ArrayList<CarModel>();
		carmodels.add(audiA6);
		ordermanager = new OrderManager();

		action1 = new Action("Paint Car");
		acties = new ArrayList<Action>();
		acties.add(action1);
		assem = new AssemblyTask("Assembly task",acties);
		tasks = new ArrayList<AssemblyTask>();
	}

}