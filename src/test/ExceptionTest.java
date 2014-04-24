package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.CarModel;
import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.category.ModelAFactory;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.Manager;



public class ExceptionTest {
	private OrderManager orderManager;
	private Manager manager;
	private Catalog catalog;
	private ArrayList<CarOptionCategory> categories;

	@Before
	public void setUp() throws Exception {
		ArrayList<CarModel> carmodels = new ArrayList<CarModel>();
		orderManager = new OrderManager(carmodels);
		manager = new Manager("bouwe", "ceunen", "bouwe");
		
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
		try{
		orderManager.placeOrder(new StandardCarOrder(manager, chosen,modelA));
		
		}catch (NoClearanceException ex){
			assertEquals(ex.getUser(), manager);
		}
		
	}

	@Test
	public void test() {
		
	}
}
