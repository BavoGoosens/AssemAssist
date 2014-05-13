package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.order.StandardVehicleOrder;
import org.junit.Before;
import org.junit.Test;

import businessmodel.VehicleModel;
import businessmodel.Catalog;
import businessmodel.category.Airco;
import businessmodel.category.Body;
import businessmodel.category.Color;
import businessmodel.category.Engine;
import businessmodel.category.ModelAFactory;
import businessmodel.category.ModelBFactory;
import businessmodel.category.ModelCFactory;
import businessmodel.category.Spoiler;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.user.GarageHolder;

public class RestrictionTest {

	private Catalog catalog;
	private ArrayList<VehicleOptionCategory> categories;
	private ModelAFactory aFactory;
	private ModelBFactory bFactory;
	private ModelCFactory cFactory;
	private GarageHolder gh;
	
	@Before
	public void setUp() throws Exception {
		this.catalog = new Catalog();
		this.categories = this.catalog.getAllCategories();
		this.aFactory = new ModelAFactory();
		this.bFactory = new ModelBFactory();
		this.cFactory = new ModelCFactory();
		this.gh = new GarageHolder("Michiel", "Vandendriessche", "MichielVDD");
	}

	@Test
	/**
	 * Test for vehicle without an engine
	 */
	public void testDefaultMandatoryOptions() throws NoClearanceException {
		VehicleModel modelA = this.aFactory.createModel();
		ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();
		for (VehicleOptionCategory category: this.categories) {
			if (!category.equals(new Engine()) && !category.equals(new Color())) {
				ArrayList<VehicleOption> options = modelA.getVehicleModelSpecification().getOptionsOfCategory(category);
				if (options.size() > 0) {
					chosen.add(options.get(0));
				}
			}
		}
//		System.out.println("\nChosen options: "+chosen+"\n");
		try {
			new StandardVehicleOrder(gh, chosen, modelA);
//			System.out.println("Success!");
		} catch (UnsatisfiedRestrictionException e) {
//			System.out.println(e.getMessage());
		}
	}
	
	@Test
	/**
	 * Test for a vehicle with a sport body without a spoiler
	 */
	public void testSportBodyRestriction() throws NoClearanceException {
		VehicleModel modelB = this.bFactory.createModel();
		ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();
		for (VehicleOptionCategory category: this.categories) {
			ArrayList<VehicleOption> options = modelB.getVehicleModelSpecification().getOptionsOfCategory(category);
			if (category.equals(new Body())) {
				for (VehicleOption option: options) {
					if (option.getName().equalsIgnoreCase("sport")) {
						chosen.add(option);
					}
				}
			}
			else if (!category.equals(new Spoiler())) {
				chosen.add(options.get(0));
			}
		}
//		System.out.println("\nChosen options: "+chosen+"\n");
		try {
			new StandardVehicleOrder(gh, chosen, modelB);
//			System.out.println("Success!");
		} catch (UnsatisfiedRestrictionException e) {
//			System.out.println(e.getMessage());
		}
	} 
	
	@Test
	/**
	 * Test for a vehicle with a sport body with a standard engine
	 */
	public void testSportBodyRestriction2() throws NoClearanceException {
		VehicleModel modelB = this.bFactory.createModel();
		ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();
		for (VehicleOptionCategory category: categories) {
			ArrayList<VehicleOption> options = modelB.getVehicleModelSpecification().getOptionsOfCategory(category);
			if (category.equals(new Body())) {
				for (VehicleOption option: options) {
					if (option.getName().equalsIgnoreCase("sport")) {
						chosen.add(option);
					}
				}
			} else if (category.equals(new Engine())) {
				for (VehicleOption option: options) {
					if (option.getName().equalsIgnoreCase("standard 2l v4")) {
						chosen.add(option);
					}
				}
			} else {
				chosen.add(options.get(0));
			}
		}
//		System.out.println("\nChosen options: "+chosen+"\n");
		try {
			new StandardVehicleOrder(gh, chosen, modelB);
//			System.out.println("Success!");
		} catch (UnsatisfiedRestrictionException e) {
//			System.out.println(e.getMessage());
		}
	}
	
	
	
	@Test
	/**
	 * Test for a vehicle with an ultra engine and an automatic airco
	 */
	public void testUltraEngineAircoRestriction() throws NoClearanceException {
		VehicleModel modelC = this.cFactory.createModel();
		ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();
		for (VehicleOptionCategory category: categories) {
			ArrayList<VehicleOption> options = modelC.getVehicleModelSpecification().getOptionsOfCategory(category);
			if (category.equals(new Engine())) {
				for (VehicleOption option: options) {
					if (option.getName().equalsIgnoreCase("ultra 3l v8")) {
						chosen.add(option);
					}
				}
			} else if (category.equals(new Airco())) {
				for (VehicleOption option: options) {
					if (option.getName().equalsIgnoreCase("automatic")) {
						chosen.add(option);
					}
				}
			} else {
				chosen.add(options.get(0));
			}
		}
//		System.out.println("\nChosen options: "+chosen+"\n");
		try {
			new StandardVehicleOrder(gh, chosen, modelC);
//			System.out.println("Success!");
		} catch (UnsatisfiedRestrictionException e) {
//			System.out.println(e.getMessage());
		}
	}
	
	@Test
	/**
	 * Test for a vehicle with an ultra engine and an automatic airco
	 */
	public void testDoubleRestriction() throws NoClearanceException {
		VehicleModel modelC = this.cFactory.createModel();
		VehicleModel modelA = this.aFactory.createModel();
		ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();
		for (VehicleOption option: modelC.getVehicleModelSpecification().getOptionsClone()) {
			chosen.add(option);
		}
		chosen.add(modelC.getVehicleModelSpecification().getOptionsClone().
				get(modelC.getVehicleModelSpecification().getOptionsClone().size()-1));
		chosen.add(modelC.getVehicleModelSpecification().getOptionsClone().
				get(modelC.getVehicleModelSpecification().getOptionsClone().size()-1));
//		System.out.println("\nChosen options: "+chosen+"\n");
		try {
			new StandardVehicleOrder(gh, chosen, modelC);
//			System.out.println("Success!");
		} catch (UnsatisfiedRestrictionException e) {
//			System.out.println(e.getMessage());
		}
	}
	
/*	@Test
	public void normalTest() throws IllegalArgumentException, UnsatisfiedRestrictionException {
		Restriction restriction1 = new DefaultMandatoryOptionRestriction("Test1", this.catalog);
		Restriction restriction2 = new SportBodyRestriction("Test2", this.catalog);
		Restriction restriction3 = new UltraEngineAircoRestriction("Test3", this.catalog);
		ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();
		VehicleModel modelA = this.aFactory.createModel();
		for (VehicleOptionCategory category: categories) {
			ArrayList<VehicleOption> options = modelA.getVehicleModelSpecification().getOptionsOfCategory(category);
			if (options.size() > 0) {
				chosen.add(options.get(0));
			}
		}
		System.out.println("Normal test: "+chosen);
		assertTrue(restriction1.check(chosen));
		assertTrue(restriction2.check(chosen));
		assertTrue(restriction3.check(chosen));
	} */

}
