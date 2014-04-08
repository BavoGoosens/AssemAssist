package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.CarModelSpecification;
import businessmodel.CarOption;
import businessmodel.CarOptionCategory;
import businessmodel.Inventory;

public class CarModelSpecificationTest {
	
	private CarModelSpecification modelASpec;

	@Before
	public void setUp() {
		Inventory inventory = new Inventory();
		ArrayList<CarOption> options = new ArrayList<CarOption>();
		
		CarOptionCategory body = inventory.getBody();
		CarOptionCategory color = inventory.getColor();
		CarOptionCategory engine = inventory.getEngine();
		CarOptionCategory gearbox = inventory.getGearbox();
		CarOptionCategory seats = inventory.getSeats();
		CarOptionCategory airco = inventory.getAirco();
		CarOptionCategory wheels = inventory.getWheels();
		
		options.add(body.getOptionWithName("sedan"));
		options.add(body.getOptionWithName("break"));
		
		options.add(color.getOptionWithName("red"));
		options.add(color.getOptionWithName("blue"));
		options.add(color.getOptionWithName("black"));
		options.add(color.getOptionWithName("white"));
		
		options.add(engine.getOptionWithName("standard 2l v4"));
		options.add(engine.getOptionWithName("performance 2.5l v6"));
		
		options.add(gearbox.getOptionWithName("6 speed manual"));
		options.add(gearbox.getOptionWithName("5 speed manual"));
		options.add(gearbox.getOptionWithName("5 speed automatic"));
		
		options.add(seats.getOptionWithName("leather white"));
		options.add(seats.getOptionWithName("leather black"));
		options.add(seats.getOptionWithName("vinyl grey"));
		
		options.add(airco.getOptionWithName("manual"));
		options.add(airco.getOptionWithName("automatic"));
		
		options.add(wheels.getOptionWithName("winter"));
		options.add(wheels.getOptionWithName("comfort"));
		options.add(wheels.getOptionWithName("sports"));
		
		modelASpec = new CarModelSpecification(options);
		
	}
	
	@Test
	public void testModel() {
		assertNotNull(modelASpec);
	}

}
