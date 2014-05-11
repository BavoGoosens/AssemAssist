package businessmodel;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import businessmodel.CarModel;
import businessmodel.CarModelSpecification;
import businessmodel.Catalog;

public class CarModelTest {
	
	private CarModel model;
	private CarModelSpecification cms;

	@Before
	public void setUp() throws Exception {
	
		Catalog catalog = new Catalog();
		cms = catalog.getAvailaleModelsClone().get(0).getCarModelSpecification();
		model = catalog.getAvailaleModelsClone().get(0);
	}

	@Test
	public void test() {
		assertEquals("Model A",this.model.getName());
		assertEquals(this.cms,model.getCarModelSpecification());
		assertEquals(model.toString(),"Car model: Model A");
	}

}
