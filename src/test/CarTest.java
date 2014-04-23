package test;


import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Car;
import businessmodel.category.Body;
import businessmodel.category.CarOption;
import businessmodel.category.Engine;

public class CarTest {

	private ArrayList<CarOption> options;
	@Before
	public void setUp() throws Exception {
		CarOption option = new CarOption("small engine", new Engine() );
		CarOption option2 = new CarOption("big body", new Body() );
		options =  new ArrayList<CarOption>();
		options.add(option);
		options.add(option2);
		
		
	}

	@Test
	public void test() {
		new Car(options);
	}

}
