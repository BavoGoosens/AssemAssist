package businessmodel.assemblyline;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.category.*;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.Order;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;
import businessmodel.user.User;

public class AssemblyLineSchedulerTest {

	@Before
	public void setUp() throws Exception {
		Catalog catalog = new Catalog();
        ArrayList<VehicleModel> models = catalog.getAvailaleModelsClone();
        GarageHolder michiel = new GarageHolder("Michiel", "Vandendriessche", "michielvdd");
        OrderManager om = new OrderManager(models);
        VehicleModel AModel = null;
        VehicleModel BModel = null;
        VehicleModel CModel = null;
        for (VehicleModel model: models) {
            if (model.getName().equalsIgnoreCase("Vehicle Model A")) AModel = model;
            else if (model.getName().equalsIgnoreCase("Vehicle Model B")) BModel = model;
            else if (model.getName().equalsIgnoreCase("Vehicle Model C")) CModel = model;
        }
        Order orderA = extractAOrder(michiel, AModel);
        Order orderB = extractBOrder(michiel, BModel);
        Order orderC = extractCOrder(michiel, CModel);
        om.placeOrder(orderA);
        om.placeOrder(orderB);
        om.placeOrder(orderC);
	}
	
	private Order extractAOrder(User user, VehicleModel model) {
		ArrayList<VehicleOption> options = new ArrayList<VehicleOption>();
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Body()).get(0));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Color()).get(1));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Engine()).get(1));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Gearbox()).get(0));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Seats()).get(0));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Airco()).get(0));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Wheels()).get(0));
		try {
			return new StandardVehicleOrder(user, options, model);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (NoClearanceException e) {
			System.out.println(e.getMessage());
		} catch (UnsatisfiedRestrictionException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private Order extractBOrder(User user, VehicleModel model) {
		ArrayList<VehicleOption> options = new ArrayList<VehicleOption>();
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Body()).get(0));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Color()).get(1));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Engine()).get(2));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Gearbox()).get(0));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Seats()).get(0));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Airco()).get(0));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Wheels()).get(0));
		try {
			return new StandardVehicleOrder(user, options, model);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (NoClearanceException e) {
			System.out.println(e.getMessage());
		} catch (UnsatisfiedRestrictionException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private Order extractCOrder(User user, VehicleModel model) {
		ArrayList<VehicleOption> options = new ArrayList<VehicleOption>();
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Body()).get(0));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Color()).get(0));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Engine()).get(1));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Gearbox()).get(0));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Seats()).get(0));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Airco()).get(0));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Wheels()).get(0));
		options.add(model.getVehicleModelSpecification().getOptionsOfCategory(new Spoiler()).get(0));
		try {
			return new StandardVehicleOrder(user, options, model);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (NoClearanceException e) {
			System.out.println(e.getMessage());
		} catch (UnsatisfiedRestrictionException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
