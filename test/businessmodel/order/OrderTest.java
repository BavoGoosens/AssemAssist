package businessmodel.order;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.category.Color;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.user.CustomShopManager;
import businessmodel.user.GarageHolder;
import businessmodel.user.Mechanic;

public class OrderTest {
	
	private StandardVehicleOrder svo;
	private SingleTaskOrder sto;
	private ArrayList<VehicleOption> chosen;
	private ArrayList<VehicleOption> chosen2;
	private GarageHolder gh;
	private CustomShopManager csm;
	private VehicleModel model;
	private DateTime userEndDate;
	
	@Before
	public void setUp() throws Exception {
		Catalog catalog = new Catalog();
		this.model = catalog.getAvailaleModelsClone().get(0);
		this.gh = new GarageHolder("Michiel", "Vandendriessche", "MichielVDD");
		this.csm = new CustomShopManager("Sander", "Geijsen", "SanderG");
		this.chosen = new ArrayList<VehicleOption>();
		this.chosen2 = new ArrayList<VehicleOption>();
		ArrayList<VehicleOptionCategory> categories = catalog.getAllCategories();
		for (VehicleOptionCategory category: categories) {
			if (model.getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				this.chosen.add(model.getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
			}
		}
		this.svo = new StandardVehicleOrder(this.gh, chosen, model);
		
		chosen2.add(new VehicleOption("Appelblauwzeegroen", new Color()));
		this.userEndDate = new DateTime(2014, 6, 1, 8, 0);
		this.sto = new SingleTaskOrder(this.csm, chosen2, this.userEndDate);
	}

	@Test
	public void testStandard() {
		assertArrayEquals(this.chosen.toArray(), this.svo.getOptions().toArray());
		assertEquals(this.gh, this.svo.getUser());
		assertFalse(this.svo.isCompleted());
		assertEquals(this.model.getStandardTimeToFinish(), this.svo.getStandardTimeToFinish());
		DateTime estimatedDeliveryDate = new DateTime(2014, 6, 2, 11, 0);
		this.svo.setEstimatedDeliveryDateOfOrder(estimatedDeliveryDate);
		assertEquals(estimatedDeliveryDate, this.svo.getEstimatedDeliveryDate());
		this.svo.updateEstimatedDate(20);
		assertEquals(estimatedDeliveryDate.plusMinutes(20), this.svo.getEstimatedDeliveryDate());
		DateTime timestamp = new DateTime(2014, 6, 1, 8, 0);
		this.svo.setTimestampOfOrder(timestamp);
		assertEquals(timestamp, this.svo.getTimestamp());
		DateTime timeOnAssemblyLine = new DateTime(2014, 6, 2, 8, 0);
		this.svo.setPlacedOnAssemblyLineOfOrder(timeOnAssemblyLine);
		assertEquals(timeOnAssemblyLine, this.svo.getOrderPlacedOnAssemblyLine());
		DateTime completionDate = new DateTime(2014, 6, 2, 14, 0);
		this.svo.setCompletionDateOfOrder(completionDate);
		assertEquals(completionDate, this.svo.getCompletionDate());
		this.svo.setCompleted();
		assertTrue(this.svo.isCompleted());
	}
	
	@Test
	public void testSingle() {
		assertArrayEquals(this.chosen2.toArray(), this.sto.getOptions().toArray());
		assertEquals(this.userEndDate, this.sto.getUserEndDate());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testWrongOrder() throws NoClearanceException, UnsatisfiedRestrictionException {
		new SingleTaskOrder(this.csm, null, this.userEndDate);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testWrongOrder2() throws NoClearanceException, UnsatisfiedRestrictionException {
		new SingleTaskOrder(this.csm, this.chosen2, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testWrongOrder3() {
		this.svo.setCompletionDateOfOrder(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testWrongOrder4() {
		this.svo.setTimestampOfOrder(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testWrongOrder5() {
		this.svo.setEstimatedDeliveryDateOfOrder(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testWrongOrder6() throws NoClearanceException, UnsatisfiedRestrictionException {
		new StandardVehicleOrder(null, null, null);
	}
	
	@Test (expected = NoClearanceException.class)
	public void testNoClearance() throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
		Mechanic mechanic = new Mechanic("Bouwe", "Ceunen", "BouweC");
		new StandardVehicleOrder(mechanic, null, null);
	}

}
