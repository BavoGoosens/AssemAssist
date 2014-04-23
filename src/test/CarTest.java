package test;


import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Car;
import businessmodel.category.Body;
import businessmodel.category.CarOption;
import businessmodel.category.Engine;
import businessmodel.exceptions.UnsatisfiedRestrictionException;

public class CarTest {

	private ArrayList<CarOption> options;
	@Before
	public void setUp() throws Exception {
		CarOption option = new CarOption("small engine", new Engine() );
		CarOption option2 = new CarOption("big body", new Body() );
		options =  new ArrayList<CarOption>();
//		options.add(option);
//		options.add(option2);
		
		
	}

	@Test
	public void test() {
		try {
			new Car(options);
		} catch (IllegalArgumentException e) {
			
		} catch (UnsatisfiedRestrictionException e) {
			
			
		}
	}

}
