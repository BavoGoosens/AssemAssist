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
