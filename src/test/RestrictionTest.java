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
		System.out.println("DefaultMandatoryOptions test: "+chosen);
		assertFalse(restriction.check(chosen));
	}
	
	@Test
	/**
	 * Test for a car with a sport body without a spoiler
	 */
	public void testSportBodyRestriction() {
		Restriction restriction = new SportBodyRestriction("Test2", this.catalog);
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
		System.out.println("SportBody test1: "+chosen);
		assertFalse(restriction.check(chosen));
	} 
	
	@Test
	/**
	 * Test for a car with a sport body with a standard engine
	 */
	public void testSportBodyRestriction2() {
		Restriction restriction = new SportBodyRestriction("Test3", this.catalog);
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
		System.out.println("SportBody test2: "+chosen);
		assertFalse(restriction.check(chosen));
	}
	
	
	
	@Test
	/**
	 * Test for a car with an ultra engine and an automatic airco
	 */
	public void testUltraEngineAircoRestriction() {
		Restriction restriction = new UltraEngineAircoRestriction("Test3", this.catalog);
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
		System.out.println("UltraEngineAirco test: "+chosen);
		assertFalse(restriction.check(chosen));
	}
	
	@Test
	public void normalTest() {
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
	}

}
