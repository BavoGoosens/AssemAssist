package control;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.category.Body;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.Order;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;

public class ScenarioTestUC1 {
	
	private StandardOrderController soc;
	private VehicleManufacturingCompany vmc;
	private GarageHolder garageHolder;
	private ArrayList<VehicleModel> models = new ArrayList<VehicleModel>();

	@Before
	public void setUp() throws Exception {
		this.vmc = new VehicleManufacturingCompany();
		this.soc = new StandardOrderHandler(vmc);
		this.garageHolder = new GarageHolder("Michiel", "Vandendriessche", "michielvdd");
		Iterator<VehicleModel> modelIterator = this.vmc.getVehicleModels(this.garageHolder);
		while (modelIterator.hasNext()) {
			models.add(modelIterator.next());
		}
	}

	@Test
	public void testAvailableVehicleModels() {
		ArrayList<String> modelNames = new ArrayList<String>();
		modelNames.add("Car Model A");
		modelNames.add("Car Model B");
		modelNames.add("Car Model C");
		modelNames.add("Truck Model X");
		modelNames.add("Truck Model Y");
		for (VehicleModel model: models) {
			int index = models.indexOf(model);
			assertEquals(modelNames.get(index), model.getName());
		}
	}
	
	@Test
	public void testAvailableCarOptionsModelA() {
		ArrayList<String> optionNames = new ArrayList<String>();
		optionNames.add("BODY: sedan");
		optionNames.add("BODY: break");
		optionNames.add("COLOR: red");
		optionNames.add("COLOR: blue");
		optionNames.add("COLOR: black");
		optionNames.add("COLOR: white");
		optionNames.add("ENGINE: standard 2l v4");
		optionNames.add("ENGINE: performance 2.5l v6");
		optionNames.add("GEARBOX: 6 speed manual");
		optionNames.add("GEARBOX: 5 speed manual");
		optionNames.add("GEARBOX: 5 speed automatic");
		optionNames.add("SEATS: leather white");
		optionNames.add("SEATS: leather black");
		optionNames.add("SEATS: vinyl grey");
		optionNames.add("AIRCO: manual");
		optionNames.add("AIRCO: automatic");
		optionNames.add("WHEELS: winter");
		optionNames.add("WHEELS: comfort");
		optionNames.add("WHEELS: sports");
		VehicleModel model = models.get(0);
		for (VehicleOption option: model.getPossibilities()) {
			int index = model.getPossibilities().indexOf(option);
			assertEquals(optionNames.get(index), option.toString());
		}
	}
	
	@Test
	public void testAvailableCarOptionsModelB() {
		ArrayList<String> optionNames = new ArrayList<String>();
		optionNames.add("BODY: sedan");
		optionNames.add("BODY: break");
		optionNames.add("BODY: sport");
		optionNames.add("COLOR: red");
		optionNames.add("COLOR: blue");
		optionNames.add("COLOR: green");
		optionNames.add("COLOR: yellow");
		optionNames.add("ENGINE: standard 2l v4");
		optionNames.add("ENGINE: performance 2.5l v6");
		optionNames.add("ENGINE: ultra 3l v8");
		optionNames.add("GEARBOX: 6 speed manual");
		optionNames.add("GEARBOX: 5 speed automatic");
		optionNames.add("SEATS: leather white");
		optionNames.add("SEATS: leather black");
		optionNames.add("SEATS: vinyl grey");
		optionNames.add("AIRCO: manual");
		optionNames.add("AIRCO: automatic");
		optionNames.add("WHEELS: winter");
		optionNames.add("WHEELS: comfort");
		optionNames.add("WHEELS: sports");
		optionNames.add("SPOILER: low");
		VehicleModel model = models.get(1);
		for (VehicleOption option: model.getPossibilities()) {
			int index = model.getPossibilities().indexOf(option);
			assertEquals(optionNames.get(index), option.toString());
		}
	}
	
	@Test
	public void testAvailableCarOptionsModelC() {
		ArrayList<String> optionNames = new ArrayList<String>();
		optionNames.add("BODY: sport");
		optionNames.add("COLOR: black");
		optionNames.add("COLOR: white");
		optionNames.add("ENGINE: performance 2.5l v6");
		optionNames.add("ENGINE: ultra 3l v8");
		optionNames.add("GEARBOX: 6 speed manual");
		optionNames.add("SEATS: leather white");
		optionNames.add("SEATS: leather black");
		optionNames.add("AIRCO: manual");
		optionNames.add("AIRCO: automatic");
		optionNames.add("WHEELS: winter");
		optionNames.add("WHEELS: sports");
		optionNames.add("SPOILER: low");
		optionNames.add("SPOILER: high");
		VehicleModel model = models.get(2);
		for (VehicleOption option: model.getPossibilities()) {
			int index = model.getPossibilities().indexOf(option);
			assertEquals(optionNames.get(index), option.toString());
		}
	}
	
	@Test
	public void testAvailableCarOptionsModelX() {
		ArrayList<String> optionNames = new ArrayList<String>();
		optionNames.add("BODY: platform");
		optionNames.add("BODY: closed");
		optionNames.add("COLOR: green");
		optionNames.add("COLOR: white");
		optionNames.add("ENGINE: standard");
		optionNames.add("ENGINE: hybrid");
		optionNames.add("GEARBOX: 8 speed manual");
		optionNames.add("GEARBOX: automatic");
		optionNames.add("SEATS: vinyl grey");
		optionNames.add("SEATS: vinyl black");
		optionNames.add("AIRCO: manual");
		optionNames.add("AIRCO: automatic");
		optionNames.add("WHEELS: standard");
		optionNames.add("WHEELS: heavy-duty");
		VehicleModel model = models.get(3);
		for (VehicleOption option: model.getPossibilities()) {
			int index = model.getPossibilities().indexOf(option);
			assertEquals(optionNames.get(index), option.toString());
		}
	}
	
	@Test
	public void testAvailableCarOptionsModelY() {
		ArrayList<String> optionNames = new ArrayList<String>();
		optionNames.add("BODY: platform");
		optionNames.add("COLOR: black");
		optionNames.add("COLOR: white");
		optionNames.add("ENGINE: standard");
		optionNames.add("ENGINE: hybrid");
		optionNames.add("GEARBOX: 8 speed manual");
		optionNames.add("GEARBOX: automatic");
		optionNames.add("SEATS: vinyl grey");
		optionNames.add("SEATS: vinyl black");
		optionNames.add("AIRCO: manual");
		optionNames.add("AIRCO: automatic");
		optionNames.add("WHEELS: standard");
		optionNames.add("WHEELS: heavy-duty");
		VehicleModel model = models.get(4);
		for (VehicleOption option: model.getPossibilities()) {
			int index = model.getPossibilities().indexOf(option);
			assertEquals(optionNames.get(index), option.toString());
		}
	}
	
	@Test
	public void testPlaceOrder() throws NoClearanceException, UnsatisfiedRestrictionException {
		
		ArrayList<VehicleOptionCategory> categories = new Catalog().getAllCategories();
		
		ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();
		for (VehicleOptionCategory category: categories) {
			if (this.models.get(1).getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				chosen.add(this.models.get(1).getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
			}
		}
		
		StandardVehicleOrder order = new StandardVehicleOrder(this.garageHolder, chosen, this.models.get(1));
		this.soc.placeOrder(this.garageHolder, order);
		chosen.clear();
		
		for (VehicleOptionCategory category: categories) {
			if (this.models.get(3).getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				if (category.equals(new Body())) {
					chosen.add(this.models.get(3).getVehicleModelSpecification().getOptionsOfCategory(category).get(1));
				} else {
					chosen.add(this.models.get(3).getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
				}
			}
		}
		
		order = new StandardVehicleOrder(this.garageHolder, chosen, this.models.get(3));
		this.soc.placeOrder(this.garageHolder, order);
		
		ArrayList<Order> pending = new ArrayList<Order>();
		Iterator<Order> pendingIterator = this.vmc.getPendingOrders(this.garageHolder);
		while (pendingIterator.hasNext()) {
			pending.add(pendingIterator.next());
		}
		assertEquals(2, pending.size());
	}

}
