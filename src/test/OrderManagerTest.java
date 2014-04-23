package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.CarModel;
import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.category.Body;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.category.Engine;
import businessmodel.category.ModelAFactory;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.order.Order;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.GarageHolder;

public class OrderManagerTest {

	private OrderManager om;
	private GarageHolder garageholder;
	private Order order;
	CarOption option;
	CarOption option2;
	private Catalog catalog;
	private ArrayList<CarOptionCategory> categories;

	@Before
	public void setUp() throws Exception {
		ArrayList<CarModel> carmodels = new ArrayList<CarModel>();
		om = new OrderManager(carmodels);
		garageholder = new GarageHolder("bouwe", "ceunen", "bouwe");

		this.catalog = new Catalog();
		this.categories = this.catalog.getAllCategories();

		CarModel modelA = new ModelAFactory().createModel();
		ArrayList<CarOption> chosen = new ArrayList<CarOption>();
		for (CarOptionCategory category: this.categories) {
			ArrayList<CarOption> options = modelA.getCarModelSpecification().getOptionsOfCategory(category);
			if (options.size() > 0) {
				chosen.add(options.get(0));
			}
		}
		order = new StandardCarOrder(garageholder, chosen);
		om.placeOrder(order);
	}

	@Test
	public void test() {
		try {
			
			assertTrue(om.getScheduler().getOrders().contains(order));
			assertTrue(om.getPendingOrders(garageholder).contains(order));
			om.finishedOrder(order);
			assertTrue(om.getCompletedOrders().contains(order));
			assertTrue(om.getCompletedOrders(garageholder).contains(order));
			

		} catch (IllegalArgumentException | NoClearanceException e) {
			System.out.println(e.getMessage());
		}
	}

}
