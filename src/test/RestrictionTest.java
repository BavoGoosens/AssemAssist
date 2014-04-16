package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.restrictions.*;
import businessmodel.category.*;
import businessmodel.CarModel;
import businessmodel.Catalog;

public class RestrictionTest {

	private Catalog catalog;
	private ArrayList<CarOptionCategory> categories;
	private ModelAFactory aFactory;
	private ModelBFactory bFactory;
	private ModelCFactory cFactory;
	
	@Before
	public void setUp() throws Exception {
		this.catalog = new Catalog();
		this.categories = this.catalog.getAllCategories();
		this.aFactory = new ModelAFactory();
		this.bFactory = new ModelBFactory();
		this.cFactory = new ModelCFactory();
	}

	@Test
	/**
	 * Test for car without an engine
	 */
	public void testDefaultMandatoryOptions() {
		Restriction restriction = new DefaultMandatoryOptionRestriction("Test1", this.catalog);
		CarModel modelA = this.aFactory.createModel();
		ArrayList<CarOption> chosen = new ArrayList<CarOption>();
		for (CarOptionCategory category: this.categories) {
			if (!category.equals(new Engine())) {
				ArrayList<CarOption> options = modelA.getCarModelSpecification().getOptionsOfCategory(category);
				if (options.size() > 0) {
					chosen.add(options.get(0));
				}
			}
		}
		System.out.println(chosen);
		assertFalse(restriction.check(chosen));
	}
	
	@Test
	/**
	 * Test for a car with a sport body without a spoiler
	 */
	public void testSportBodyRestriction() {
		SportBodyRestriction restriction = new SportBodyRestriction("Test2", this.catalog);
		CarModel modelA = this.aFactory.createModel();
		ArrayList<CarOption> chosen = new ArrayList<CarOption>();
		for (CarOptionCategory category: this.categories) {
			ArrayList<CarOption> options = category.getOptionsClone();
			if (category.equals()) {
				for (CarOption option: options) {
					if (option.getName().equalsIgnoreCase("sport")) {
						chosen.add(option);
					}
				}
			} else if (category != this.inventory.getSpoiler()) {
				chosen.add(category.getPossibleOptionsClone().get(0));
			}
		}
		assertFalse(restriction.check(chosen));
	} 
	
	@Test
	/**
	 * Test for a car with a sport body with a standard engine
	 */
/*	public void testSportBodyRestriction2() {
		ArrayList<CarOption> chosen = new ArrayList<CarOption>();
		SportBodyRestriction restriction = new SportBodyRestriction("Test3", this.inventory);
		for (CarOptionCategory category: categories) {
			ArrayList<CarOption> options = category.getPossibleOptionsClone();
			if (category == this.inventory.getBody()) {
				for (CarOption option: options) {
					if (option.getName().equalsIgnoreCase("sport")) {
						chosen.add(option);
					}
				}
			} else if (category == this.inventory.getEngine()) {
				for (CarOption option: options) {
					if (option.getName().equalsIgnoreCase("standard 2l v4")) {
						chosen.add(option);
					}
				}
			} else {
				chosen.add(category.getPossibleOptionsClone().get(0));
			}
		}
		assertFalse(restriction.check(chosen));
	}
	
	@Test
	/**
	 * Test for a car with an ultra engine and an automatic airco
	 */
/*	public void testUltraEngineAircoRestriction() {
		ArrayList<CarOption> chosen = new ArrayList<CarOption>();
		UltraEngineAircoRestriction restriction = new UltraEngineAircoRestriction("Test2", this.inventory);
		for (CarOptionCategory category: categories) {
			ArrayList<CarOption> options = category.getPossibleOptionsClone();
			if (category == this.inventory.getEngine()) {
				for (CarOption option: options) {
					if (option.getName().equalsIgnoreCase("ultra 3l v8")) {
						chosen.add(option);
					}
				}
			} else if (category == this.inventory.getAirco()) {
				for (CarOption option: options) {
					if (option.getName().equalsIgnoreCase("automatic")) {
						chosen.add(option);
					}
				}
			} else {
				chosen.add(options.get(0));
			}
		}
		assertFalse(restriction.check(chosen));
	}
	
	@Test
	public void normalTest() {
		ArrayList<CarOption> chosen = new ArrayList<CarOption>();
		DefaultMandatoryOptionRestriction restriction1 = new DefaultMandatoryOptionRestriction("Test1", this.inventory);
		SportBodyRestriction restriction2 = new SportBodyRestriction("Test2", this.inventory);
		UltraEngineAircoRestriction restriction3 = new UltraEngineAircoRestriction("Test3", this.inventory);
		for (CarOptionCategory category: categories) {
			chosen.add(category.getPossibleOptionsClone().get(0));
		}
		assertTrue(restriction1.check(chosen));
		assertTrue(restriction2.check(chosen));
		assertTrue(restriction3.check(chosen));
	} */

}
