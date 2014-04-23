package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import businessmodel.CarModel;
import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.category.Body;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.category.Color;
import businessmodel.category.Engine;
import businessmodel.category.ModelAFactory;
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
			CarModel modelA = new ModelAFactory().createModel();
			ArrayList<CarOption> chosen = new ArrayList<CarOption>();
			for (CarOptionCategory category: this.categories) {
				ArrayList<CarOption> options = modelA.getCarModelSpecification().getOptionsOfCategory(category);
				if (options.size() > 0) {
					chosen.add(options.get(0));
				}
			}
			Order order1 = new StandardCarOrder(new GarageHolder("bouwe", "ceunen", "bouwe"), chosen);
			orderManager.addOrder(order1);

			CarOption option1 = new CarOption("medium engine", new Engine());
			CarOption option21 = new CarOption("big body", new Body() );
			ArrayList<CarOption> options1 = new ArrayList<CarOption>();
			options1.add(option1);
			options1.add(option21);
			Order order2 = new StandardCarOrder(new GarageHolder("sander", "ceunen", "bouwe"), options1);
			orderManager.addOrder(order2);

			CarOption option11 = new CarOption("medium engine",new Engine());
			CarOption option211 = new CarOption("big body", new Body() );
			ArrayList<CarOption> options11 = new ArrayList<CarOption>();
			options11.add(option11);
			options11.add(option211);
			Order order3 = new StandardCarOrder(new GarageHolder("bavo", "ceunen", "bouwe"), options11);
			orderManager.addOrder(order3);

			CarOption option111 = new CarOption("small engine",new Engine());
			ArrayList<CarOption> options111 = new ArrayList<CarOption>();
			options111.add(option111);
			Order order4 = new StandardCarOrder(new GarageHolder("michiel", "ceunen", "bouwe"), options111);
			orderManager.addOrder(order4);

			CarOption option1111 = new CarOption("medium engine",new Engine());
			ArrayList<CarOption> options1111 = new ArrayList<CarOption>();
			options1111.add(option1111);
			Order order5 = new StandardCarOrder(new GarageHolder("lol", "ceunen", "bouwe"), options1111);
			orderManager.addOrder(order5);

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
