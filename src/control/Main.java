package control;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import component.Airco;
import component.Body;
import component.Color;
import component.Component;
import component.Engine;
import component.Gearbox;
import component.Seats;
import component.Wheels;
import ui.UserInterFace;
import businessmodel.Car;
import businessmodel.CarManufacturingCompany;
import businessmodel.CarModel;
import businessmodel.CarModelSpecification;
import businessmodel.GarageHolder;
import businessmodel.Manager;
import businessmodel.Mechanic;
import businessmodel.Order;
import businessmodel.OrderManager;
import businessmodel.ProductionScheduler;
import businessmodel.User;
import businessmodel.UserManagement;


/**
 * @author Team 10
 *
 */
public class Main {

	private Car car1;
	private Body body;	private Body body2;
	private Color color; private Color color2;
	private Engine engine; private Engine engine2;
	private Gearbox gearbox; private Gearbox gearbox2;
	private Seats seats; private Seats seats2;
	private Airco airco; private Airco airco2;
	private Wheels wheels; private Wheels wheels2;
	private GarageHolder garageholder;
	private Mechanic mechanic;
	private Manager manager;
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
	private OrderManager ordermanager;

	private Controller controller;
	private UserInterFace interfac;
	private CarManufacturingCompany cmc;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create some components
		Body body = new Body("sedan",1000);
		Body body2 = new Body("sedan",1000);
		ArrayList<Body> bodies = new ArrayList<Body>();
		bodies.add(body2);
		bodies.add(body);
		
		Color color = new Color("red",1000);
		Color color2 = new Color("red",1000);
		ArrayList<Color> colors = new ArrayList<Color>();
		colors.add(color);
		colors.add(color2);
		
		Engine engine = new Engine("standard 2l 4 cilinders",1000);
		Engine engine2 = new Engine("standard 2l 4 cilinders",1000);
		ArrayList<Engine> engines = new ArrayList<Engine>();
		engines.add(engine);
		engines.add(engine2);
		
		Gearbox gearbox = new Gearbox("6 speed manual",1000);
		Gearbox gearbox2 = new Gearbox("5 speed manual",1000);
		ArrayList<Gearbox> gearboxes = new ArrayList<Gearbox>();
		gearboxes.add(gearbox);
		gearboxes.add(gearbox2);
		
		Seats seats = new Seats("leather black",1000);
		Seats seats2 = new Seats("leather black",1000);
		ArrayList<Seats> seatss = new ArrayList<Seats>();
		seatss.add(seats);
		seatss.add(seats2);
		
		Airco airco = new Airco("manual",1000);
		Airco airco2 = new Airco("manual",1000);
		ArrayList<Airco> aircos = new ArrayList<Airco>();
		aircos.add(airco);
		aircos.add(airco2);
		
		Wheels wheels = new Wheels("comfort",1000);
		Wheels wheels2 = new Wheels("comfort",1000);
		ArrayList<Wheels> wheelss = new ArrayList<Wheels>();
		wheelss.add(wheels);
		wheelss.add(wheels2);

		GarageHolder garageholder = new GarageHolder("Bavo","Goosens","BBB", "BBB");
		Mechanic mechanic = new Mechanic("Sander","Geijsen","HENK","DE POTVIS");
		Manager manager = new Manager("Jef", "Vermeulen", "JV", "JV");
		HashMap<String, User> map = new HashMap<String, User>();
		map.put("HENK", mechanic);
		map.put("BBB", garageholder);
		map.put("JV", manager);
		UserManagement um = new UserManagement(map);		
		
		CarModelSpecification cms = new CarModelSpecification(bodies,colors,engines,gearboxes,seatss,aircos,wheelss);
		CarModel audiA6 = new CarModel("Audi A6",cms);
		ArrayList<CarModel> carmodels = new ArrayList<CarModel>();
		carmodels.add(audiA6);
		OrderManager om = new OrderManager(carmodels);
		
		ProductionScheduler ps = new ProductionScheduler();
		
		Controller ctrl = new Controller(um,ps,om,null );
		ctrl.run();
	}
}
