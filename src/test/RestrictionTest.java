package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.order.Order;
import businessmodel.order.StandardCarOrder;
import businessmodel.restrictions.*;
import businessmodel.user.GarageHolder;
import businessmodel.category.*;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.CarModel;
import businessmodel.Catalog;

public class RestrictionTest {

	private Catalog catalog;
	private ArrayList<CarOptionCategory> categories;
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
	 * Test for car without an engine
	 */
	public void testDefaultMandatoryOptions() throws NoClearanceException {
		CarModel modelA = this.aFactory.createModel();
		ArrayList<CarOption> chosen = new ArrayList<CarOption>();
		for (CarOptionCategory category: this.categories) {
			if (!category.equals(new Engine()) && !category.equals(new Color())) {
				ArrayList<CarOption> options = modelA.getCarModelSpecification().getOptionsOfCategory(category);
				if (options.size() > 0) {
					chosen.add(options.get(0));
				}
			}
		}
		System.out.println("\nChosen options: "+chosen+"\n");
		try {
			new StandardCarOrder(gh, chosen);
		} catch (UnsatisfiedRestrictionException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	/**
	 * Test for a car with a sport body without a spoiler
	 */
	public void testSportBodyRestriction() throws NoClearanceException {
		CarModel modelB = this.bFactory.createModel();
		ArrayList<CarOption> chosen = new ArrayList<CarOption>();
		for (CarOptionCategory category: this.categories) {
			ArrayList<CarOption> options = modelB.getCarModelSpecification().getOptionsOfCategory(category);
			if (category.equals(new Body())) {
				for (CarOption option: options) {
					if (option.getName().equalsIgnoreCase("sport")) {
						chosen.add(option);
					}
				}
			}
			else if (!category.equals(new Spoiler())) {
				chosen.add(options.get(0));
			}
		}
		System.out.println("\nChosen options: "+chosen+"\n");
		try {
			new StandardCarOrder(gh, chosen);
		} catch (UnsatisfiedRestrictionException e) {
			System.out.println(e.getMessage());
		}
	} 
	
	@Test
	/**
	 * Test for a car with a sport body with a standard engine
	 */
	public void testSportBodyRestriction2() throws NoClearanceException {
		CarModel modelB = this.bFactory.createModel();
		ArrayList<CarOption> chosen = new ArrayList<CarOption>();
		for (CarOptionCategory category: categories) {
			ArrayList<CarOption> options = modelB.getCarModelSpecification().getOptionsOfCategory(category);
			if (category.equals(new Body())) {
				for (CarOption option: options) {
					if (option.getName().equalsIgnoreCase("sport")) {
						chosen.add(option);
					}
				}
			} else if (category.equals(new Engine())) {
				for (CarOption option: options) {
					if (option.getName().equalsIgnoreCase("standard 2l v4")) {
						chosen.add(option);
					}
				}
			} else {
				chosen.add(options.get(0));
			}
		}
		System.out.println("\nChosen options: "+chosen+"\n");
		try {
			new StandardCarOrder(gh, chosen);
		} catch (UnsatisfiedRestrictionException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	@Test
	/**
	 * Test for a car with an ultra engine and an automatic airco
	 */
	public void testUltraEngineAircoRestriction() throws NoClearanceException {
		CarModel modelC = this.cFactory.createModel();
		ArrayList<CarOption> chosen = new ArrayList<CarOption>();
		for (CarOptionCategory category: categories) {
			ArrayList<CarOption> options = modelC.getCarModelSpecification().getOptionsOfCategory(category);
			if (category.equals(new Engine())) {
				for (CarOption option: options) {
					if (option.getName().equalsIgnoreCase("ultra 3l v8")) {
						chosen.add(option);
					}
				}
			} else if (category.equals(new Airco())) {
				for (CarOption option: options) {
					if (option.getName().equalsIgnoreCase("automatic")) {
						chosen.add(option);
					}
				}
			} else {
				chosen.add(options.get(0));
			}
		}
		System.out.println("\nChosen options: "+chosen+"\n");
		try {
			new StandardCarOrder(gh, chosen);
		} catch (UnsatisfiedRestrictionException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	/**
	 * Test for a car with an ultra engine and an automatic airco
	 */
	public void testDoubleRestriction() throws NoClearanceException {
		CarModel modelC = this.cFactory.createModel();
		ArrayList<CarOption> chosen = new ArrayList<CarOption>();
		for (CarOption option: modelC.getCarModelSpecification().getOptionsClone()) {
			chosen.add(option);
		}
		chosen.add(modelC.getCarModelSpecification().getOptionsClone().
				get(modelC.getCarModelSpecification().getOptionsClone().size()-1));
		chosen.add(modelC.getCarModelSpecification().getOptionsClone().
				get(modelC.getCarModelSpecification().getOptionsClone().size()-1));
		System.out.println("\nChosen options: "+chosen+"\n");
		try {
			new StandardCarOrder(gh, chosen);
		} catch (UnsatisfiedRestrictionException e) {
			System.out.println(e.getMessage());
		}
	}
	
/*	@Test
	public void normalTest() throws IllegalArgumentException, UnsatisfiedRestrictionException {
		Restriction restriction1 = new DefaultMandatoryOptionRestriction("Test1", this.catalog);
		Restriction restriction2 = new SportBodyRestriction("Test2", this.catalog);
		Restriction restriction3 = new UltraEngineAircoRestriction("Test3", this.catalog);
		ArrayList<CarOption> chosen = new ArrayList<CarOption>();
		CarModel modelA = this.aFactory.createModel();
		for (CarOptionCategory category: categories) {
			ArrayList<CarOption> options = modelA.getCarModelSpecification().getOptionsOfCategory(category);
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
