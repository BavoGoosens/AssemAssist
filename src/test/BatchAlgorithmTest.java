package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import businessmodel.CarModel;
import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.category.Airco;
import businessmodel.category.Body;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.category.Color;
import businessmodel.category.Engine;
import businessmodel.category.Gearbox;
import businessmodel.category.ModelAFactory;
import businessmodel.category.Seats;
import businessmodel.category.Wheels;
import businessmodel.exceptions.IllegalSchedulingAlgorithmException;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.Order;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.GarageHolder;


public class BatchAlgorithmTest {

	private Catalog catalog;
	private ArrayList<CarOptionCategory> categories;

	@Test
	public void test() {

		try {
			this.catalog = new Catalog();
			this.categories = this.catalog.getAllCategories();

			ArrayList<CarModel> carmodels = new ArrayList<CarModel>();
			OrderManager orderManager = new OrderManager(carmodels);

			ArrayList<CarOption> options = new ArrayList<CarOption>();
			options.add(new CarOption("Airco", new Airco()));
			options.add(new CarOption("seats", new Seats()));
			options.add(new CarOption("Color", new Color()));
			options.add(new CarOption("Gearbox", new Gearbox()));
			options.add(new CarOption("Wheels", new Wheels()));
			
			CarOption engine = new CarOption("small engine", new Engine());
			CarOption body = new CarOption("big body", new Body());
			options.add(body);
			options.add(engine);
			
			Order order1 = new StandardCarOrder(new GarageHolder("bouwe", "", ""), options);
			orderManager.addOrder(order1);
			
			//---------------------------------------------------------------------------------
			
			options.remove(body);
			options.remove(engine);
			engine = new CarOption("medium engine", new Engine());
			body = new CarOption("big body", new Body());
			options.add(body);
			options.add(engine);

	
			Order order2 = new StandardCarOrder(new GarageHolder("sander", "", ""), options);
			orderManager.addOrder(order2);
			
			//---------------------------------------------------------------------------------
			
			options.remove(body);
			options.remove(engine);
			engine = new CarOption("medium engine", new Engine());
			body = new CarOption("big body", new Body());
			options.add(body);
			options.add(engine);

			Order order3 = new StandardCarOrder(new GarageHolder("bavo", "", ""), options);
			orderManager.addOrder(order3);

			//---------------------------------------------------------------------------------
			
			options.remove(body);
			options.remove(engine);
			engine = new CarOption("small engine", new Engine());
			body = new CarOption("small body", new Body());
			options.add(body);
			options.add(engine);

			Order order4 = new StandardCarOrder(new GarageHolder("michiel", "", ""), options);
			orderManager.addOrder(order4);

			
			//---------------------------------------------------------------------------------
			
			options.remove(body);
			options.remove(engine);
			engine = new CarOption("medium engine", new Engine());
			body = new CarOption("body", new Body());
			options.add(body);
			options.add(engine);

			Order order5 = new StandardCarOrder(new GarageHolder("lol", "", ""), options);
			orderManager.addOrder(order5);

			
			//----------------------------------------------------------------------------------
			
			
			orderManager.getScheduler().changeAlgorithm("sb",  new CarOption("medium engine",new Engine()));
			try{
				orderManager.getScheduler().changeAlgorithm("dsfsf",  new CarOption("medium engine",new Engine()));
			}catch(IllegalSchedulingAlgorithmException ex){
				ex.getMessage();
			}
			orderManager.getScheduler().ScheduleDay();

			assertEquals(orderManager.getScheduler().getOrders().get(0),order2);
			assertEquals(orderManager.getScheduler().getOrders().get(1),order3);
			assertEquals(orderManager.getScheduler().getOrders().get(2),order5);
			assertEquals(orderManager.getScheduler().getOrders().get(3),order1);
			assertEquals(orderManager.getScheduler().getOrders().get(4),order4);



			//			algo.schedule();


		} catch (IllegalArgumentException e) {

		} catch (NoClearanceException e) {

		} catch (UnsatisfiedRestrictionException e) {
			System.out.println(e.getMessage());
		}
	}

}
