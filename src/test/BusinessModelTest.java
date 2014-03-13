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
		
		mechanic = new Mechanic("Sander","Geijsen","Sander1","henk");
		garageholder = new GarageHolder("Sander","Geijsen","Sander2","henk");
		manager = new Manager("Sander","Geijsen","Sander3","henk");
		
		date = new Date();

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

		orders = new ArrayList<Order>();

		for(int i = 1; i< 30;i++){
			Order temp = new Order(garageholder,this.components);
			temp.setDate(date);
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

	// A test for the class Component
	@Test 
	public void testComponent() throws Exception {
		assertEquals("sedan",body.getName());
		assertEquals(1000,body.getPrice(),0);
		assertEquals(body.toString(),"name= sedan, price= "+ body.getPrice());

		try {body = new Body(null,1000);}
		catch (IllegalArgumentException e) {}

		try {body = new Body("sedan",-1);}
		catch (IllegalArgumentException e) {}

		try {body = new Body("sedan",1000);}
		catch (IllegalArgumentException e) {}
		
	}	

	// A test for the class User.
	@Test
	public void UserTest(){
		assertEquals(mechanic.getFirstname(),"Sander");
		assertEquals(mechanic.getLastname(),"Geijsen");
		mechanic.authenticate("Sander1","henk");
		mechanic.authenticate("Sander7","henk");
		mechanic.updateUser("Sander1", "henk", "Piet", "jan");
	}
	
	// A test for the class Car.
	@Test
	public void CarTest(){
		assertEquals(this.components,car1.getComponents());
		Body bodytest = new Body("test",500);
		car1.removeComponent(bodytest);
		car1.addComponent(bodytest);
		car1.removeComponent(bodytest);
		assertEquals(car1.toString(),"components= [name= sedan, price= "+1000.0+", name= red, price= "+1000.0+", name= standard 2l 4 cilinders, price= "+1000.0+", name= 6 speed manual, price= "+1000.0+", name= leather black, price= "+1000.0+", name= manual, price= "+1000.0+", name= comfort, price= "+1000.0+"]");
	}
	// A test to test the class CarModel.
	@Test
	public void CarModelTest(){
		assertEquals("Audi A6",this.audiA6.getCarmodel());
		assertEquals(this.cms,audiA6.getCarModelSpecification());
		assertEquals(audiA6.toString(),"carmodel= Audi A6");
	}

	// A test to test the class CarModelSpecification.
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

	// A test for the class Order.
	@Test
	public void testOrder(){

		assertEquals(orders.get(0).getCar().getComponents(),this.components);
		assertEquals(orders.get(0).getUser(),this.garageholder);
		assertEquals(orders.get(0).getDate(),this.date);
		assertEquals(orders.get(0).isCompleted(),false);
		assertEquals(orders.get(0).getUser(),garageholder);

	}

	// A test for the class Work Post.
	@Test
	public void testWorkPost(){
		WorkPost wp = new WorkPost("Test",tasks);
		wp.setNewOrder(orders.get(0));
		assertEquals(wp.getOrder(),orders.get(0));

	}
	
	@Test
	public void testController(){
		Controller controller = new Controller();
		assertEquals(controller.authenticate("Bouwe2014", "henk"), true);
		assertEquals(controller.canPlaceOrder(mechanic), false);
		assertEquals(controller.canAdvanceAssemblyLine(mechanic), false);
		assertEquals(controller.canPerformAssemblyTask(mechanic), true);
		
	}

	// A test for the class Assembly line.
	@Test
	public void testUser(){
		
		mechanic.updateUser("Sander2014", "henk", "Sander015", "henk");
		assertEquals(mechanic.getFirstname(), "Sander");
		assertEquals(mechanic.getLastname(), "Geijsen");
	}
	
	@Test
	public void testAssemblyLine(){	
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

	// A test for the class production Scheduler.
	@Test
	public void testProductionScheduler(){
		ProductionScheduler prodsched = ordermanager.getProductionScheduler();
		for(Order order: this.orders){
			ordermanager.placeOrder(order);
		}
		assertEquals(orders.get(0),prodsched.getScheduledOrders().get(0));
		prodsched.advance(60);
		for(Order order:this.orders){
			for(WorkPost wp: prodsched.getAssemblyline().getWorkPosts()){
				if(wp.getOrder() != null){
					for(AssemblyTask task: wp.getPendingTasks())
					task.completeAssemblytask();
				}
			}
			prodsched.advance(60);
		}
		for(Order order:this.orders){
			for(WorkPost wp: prodsched.getAssemblyline().getWorkPosts()){
				if(wp.getOrder() != null){
					for(AssemblyTask task: wp.getPendingTasks())
					task.completeAssemblytask();
				}
			}
			prodsched.advance(40);
		}
		for(Order order:this.orders){
			for(WorkPost wp: prodsched.getAssemblyline().getWorkPosts()){
				if(wp.getOrder() != null){
					for(AssemblyTask task: wp.getPendingTasks())
					task.completeAssemblytask();
				}
			}
			prodsched.advance(80);
		}
	}
	@Test
	public void testCarModelSpecification_1()
		throws Exception {
		ArrayList<Body> bodies = new ArrayList();
		ArrayList<Color> colors = new ArrayList();
		ArrayList<Engine> engines = new ArrayList();
		ArrayList<Gearbox> gearboxes = new ArrayList();
		ArrayList<Seats> seats = new ArrayList();
		ArrayList<Airco> aircos = new ArrayList();
		ArrayList<Wheels> wheels = new ArrayList();

		CarModelSpecification result = new CarModelSpecification(bodies, colors, engines, gearboxes, seats, aircos, wheels);

		assertNotNull(result);
		assertEquals("bodies= [], colors= [], engines=  [], gearboxes= [], seats= [], aircos= [], wheels= []", result.toString());
	}

	@Test
	public void testCarModelSpecification_2()
		throws Exception {
		ArrayList<Body> bodies = new ArrayList();
		ArrayList<Color> colors = new ArrayList();
		ArrayList<Engine> engines = new ArrayList();
		ArrayList<Gearbox> gearboxes = new ArrayList();
		ArrayList<Seats> seats = new ArrayList();
		ArrayList<Airco> aircos = new ArrayList();
		ArrayList<Wheels> wheels = new ArrayList();

		CarModelSpecification result = new CarModelSpecification(bodies, colors, engines, gearboxes, seats, aircos, wheels);

		assertNotNull(result);
		assertEquals("bodies= [], colors= [], engines=  [], gearboxes= [], seats= [], aircos= [], wheels= []", result.toString());
	}

	@Test
	public void testCarModelSpecification_3()
		throws Exception {
		ArrayList<Body> bodies = new ArrayList();
		ArrayList<Color> colors = new ArrayList();
		ArrayList<Engine> engines = new ArrayList();
		ArrayList<Gearbox> gearboxes = new ArrayList();
		ArrayList<Seats> seats = new ArrayList();
		ArrayList<Airco> aircos = new ArrayList();
		ArrayList<Wheels> wheels = new ArrayList();

		CarModelSpecification result = new CarModelSpecification(bodies, colors, engines, gearboxes, seats, aircos, wheels);

		assertNotNull(result);
		assertEquals("bodies= [], colors= [], engines=  [], gearboxes= [], seats= [], aircos= [], wheels= []", result.toString());
	}

	@Test
	public void testCarModelSpecification_4()
		throws Exception {
		ArrayList<Body> bodies = new ArrayList();
		ArrayList<Color> colors = new ArrayList();
		ArrayList<Engine> engines = new ArrayList();
		ArrayList<Gearbox> gearboxes = new ArrayList();
		ArrayList<Seats> seats = new ArrayList();
		ArrayList<Airco> aircos = new ArrayList();
		ArrayList<Wheels> wheels = new ArrayList();

		CarModelSpecification result = new CarModelSpecification(bodies, colors, engines, gearboxes, seats, aircos, wheels);

		assertNotNull(result);
		assertEquals("bodies= [], colors= [], engines=  [], gearboxes= [], seats= [], aircos= [], wheels= []", result.toString());
	}

	@Test
	public void testCarModelSpecification_5()
		throws Exception {
		ArrayList<Body> bodies = new ArrayList();
		ArrayList<Color> colors = new ArrayList();
		ArrayList<Engine> engines = new ArrayList();
		ArrayList<Gearbox> gearboxes = new ArrayList();
		ArrayList<Seats> seats = new ArrayList();
		ArrayList<Airco> aircos = new ArrayList();
		ArrayList<Wheels> wheels = new ArrayList();

		CarModelSpecification result = new CarModelSpecification(bodies, colors, engines, gearboxes, seats, aircos, wheels);

		assertNotNull(result);
		assertEquals("bodies= [], colors= [], engines=  [], gearboxes= [], seats= [], aircos= [], wheels= []", result.toString());
	}

	@Test
	public void testCarModelSpecification_6()
		throws Exception {
		ArrayList<Body> bodies = new ArrayList();
		ArrayList<Color> colors = new ArrayList();
		ArrayList<Engine> engines = new ArrayList();
		ArrayList<Gearbox> gearboxes = new ArrayList();
		ArrayList<Seats> seats = new ArrayList();
		ArrayList<Airco> aircos = new ArrayList();
		ArrayList<Wheels> wheels = new ArrayList();

		CarModelSpecification result = new CarModelSpecification(bodies, colors, engines, gearboxes, seats, aircos, wheels);

		assertNotNull(result);
		assertEquals("bodies= [], colors= [], engines=  [], gearboxes= [], seats= [], aircos= [], wheels= []", result.toString());
	}

	@Test
	public void testCarModelSpecification_7()
		throws Exception {
		ArrayList<Body> bodies = new ArrayList();
		ArrayList<Color> colors = new ArrayList();
		ArrayList<Engine> engines = new ArrayList();
		ArrayList<Gearbox> gearboxes = new ArrayList();
		ArrayList<Seats> seats = new ArrayList();
		ArrayList<Airco> aircos = new ArrayList();
		ArrayList<Wheels> wheels = new ArrayList();

		CarModelSpecification result = new CarModelSpecification(bodies, colors, engines, gearboxes, seats, aircos, wheels);

		assertNotNull(result);
		assertEquals("bodies= [], colors= [], engines=  [], gearboxes= [], seats= [], aircos= [], wheels= []", result.toString());
	}

	@Test
	public void testCarModelSpecification_8()
		throws Exception {
		ArrayList<Body> bodies = new ArrayList();
		ArrayList<Color> colors = new ArrayList();
		ArrayList<Engine> engines = new ArrayList();
		ArrayList<Gearbox> gearboxes = new ArrayList();
		ArrayList<Seats> seats = new ArrayList();
		ArrayList<Airco> aircos = new ArrayList();
		ArrayList<Wheels> wheels = new ArrayList();

		CarModelSpecification result = new CarModelSpecification(bodies, colors, engines, gearboxes, seats, aircos, wheels);

		assertNotNull(result);
		assertEquals("bodies= [], colors= [], engines=  [], gearboxes= [], seats= [], aircos= [], wheels= []", result.toString());
	}

	@Test
	public void testGetAircos_1()
		throws Exception {
		CarModelSpecification fixture = new CarModelSpecification(new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList());

		ArrayList<Airco> result = fixture.getAircos();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetBodies_1()
		throws Exception {
		CarModelSpecification fixture = new CarModelSpecification(new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList());

		ArrayList<Body> result = fixture.getBodies();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetColors_1()
		throws Exception {
		CarModelSpecification fixture = new CarModelSpecification(new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList());

		ArrayList<Color> result = fixture.getColors();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetEngines_1()
		throws Exception {
		CarModelSpecification fixture = new CarModelSpecification(new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList());

		ArrayList<Engine> result = fixture.getEngines();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetGearboxes_1()
		throws Exception {
		CarModelSpecification fixture = new CarModelSpecification(new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList());

		ArrayList<Gearbox> result = fixture.getGearboxes();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetPosibilities_1()
		throws Exception {
		CarModelSpecification fixture = new CarModelSpecification(new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList());

		ArrayList<Component[]> result = fixture.getPosibilities();

		assertNotNull(result);
		assertEquals(7, result.size());
	}

	@Test
	public void testGetSeats_1()
		throws Exception {
		CarModelSpecification fixture = new CarModelSpecification(new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList());

		ArrayList<Seats> result = fixture.getSeats();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetWheels_1()
		throws Exception {
		CarModelSpecification fixture = new CarModelSpecification(new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList());

		ArrayList<Wheels> result = fixture.getWheels();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testToString_2()
		throws Exception {
		CarModelSpecification fixture = new CarModelSpecification(new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList());

		String result = fixture.toString();

		assertEquals("bodies= [], colors= [], engines=  [], gearboxes= [], seats= [], aircos= [], wheels= []", result);
	}
	@Test
	public void testCarModel_1()
		throws Exception {
		String carmodel = "";
		CarModelSpecification cms = new CarModelSpecification(new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList());

		CarModel result = new CarModel(carmodel, cms);

		assertNotNull(result);
		assertEquals("", result.getCarmodel());
		assertEquals("carmodel= ", result.toString());
	}

	@Test
	public void testGetCarModelSpecification_1()
		throws Exception {
		CarModel fixture = new CarModel("", new CarModelSpecification(new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList()));

		CarModelSpecification result = fixture.getCarModelSpecification();

		assertNotNull(result);
		assertEquals("bodies= [], colors= [], engines=  [], gearboxes= [], seats= [], aircos= [], wheels= []", result.toString());
	}

	@Test
	public void testGetCarmodel_1()
		throws Exception {
		CarModel fixture = new CarModel("", new CarModelSpecification(new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList()));

		String result = fixture.getCarmodel();

		assertEquals("", result);
	}

	@Test
	public void testGetPossibilities_1()
		throws Exception {
		CarModel fixture = new CarModel("", new CarModelSpecification(new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList()));

		ArrayList<Component[]> result = fixture.getPossibilities();

		assertNotNull(result);
		assertEquals(7, result.size());
	}

	@Test
	public void testToString_1()
		throws Exception {
		CarModel fixture = new CarModel("", new CarModelSpecification(new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList()));

		String result = fixture.toString();

		assertEquals("carmodel= ", result);
	}
	
}
