package businessmodel.category;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import businessmodel.exceptions.IllegalVehicleOptionCategoryException;

public class CategoryTest {
	
	private Body body;
	private Airco airco;
	private Airco airco2;
	
	@Before
	public void setUp() throws Exception {
		this.body = new Body();
		this.airco = new Airco();
		this.airco2 = new Airco();
	}

	@Test
	public void test() throws IllegalVehicleOptionCategoryException {
		VehicleOption option1 = new VehicleOption("sedan", body);
		VehicleOption option2 = new VehicleOption("break", body);
		body.addOption(option1);
		body.addOption(option2);
		VehicleOption[] options = new VehicleOption[2];
		options[0] = option1;
		options[1] = option2;
		assertArrayEquals(options, body.getOptionsClone().toArray());
	}
	
	@Test(expected=IllegalVehicleOptionCategoryException.class)
	public void test2() throws IllegalVehicleOptionCategoryException {
		VehicleOption option = new VehicleOption("manual", airco);
		body.addOption(option);
	}
	
	@Test
	public void test3() {
		assertNotEquals(body.getKey(), airco.getKey());
		assertNotEquals(body, airco);
		assertEquals(airco.getKey(), airco2.getKey());
		assertEquals(airco, airco2);
	}

}
