package businessmodel;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import businessmodel.VehicleModel;
import businessmodel.VehicleModelSpecification;
import businessmodel.Catalog;

public class VehicleModelTest {
	
	private VehicleModel model;
	private VehicleModelSpecification cms;

	@Before
	public void setUp() throws Exception {
	
		Catalog catalog = new Catalog();
		cms = catalog.getAvailaleModelsClone().get(0).getVehicleModelSpecification();
		model = catalog.getAvailaleModelsClone().get(0);
	}

	@Test
	public void test() {
		assertEquals("Model A",this.model.getName());
		assertEquals(this.cms,model.getVehicleModelSpecification());
		assertEquals(model.toString(),"Vehicle model: Model A");
	}

}
