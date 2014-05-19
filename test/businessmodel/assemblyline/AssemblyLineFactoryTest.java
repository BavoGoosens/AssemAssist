package businessmodel.assemblyline;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import businessmodel.MainScheduler;
import businessmodel.OrderManager;
import businessmodel.category.VehicleModel;
import businessmodel.util.IteratorConverter;

public class AssemblyLineFactoryTest {
	
	AssemblyLine aLine;
	AssemblyLine bLine;
	AssemblyLine cLine;

	@Before
	public void setUp() throws Exception {
        MainScheduler scheduler = new MainScheduler(new OrderManager());
        AssemblyLineAFactory facta = new AssemblyLineAFactory();
		aLine = facta.createAssemblyLine(scheduler);
		bLine = new AssemblyLineBFactory().createAssemblyLine(scheduler);
		cLine = new AssemblyLineCFactory().createAssemblyLine(scheduler);
	}

	@Test
	public void testALine() {
		assertEquals(aLine.getNumberOfWorkPosts(), 3);
		assertEquals(aLine.getWorkPostsIterator().get(0).getName(), "Body Work Post");
		assertEquals(aLine.getWorkPostsIterator().get(1).getName(), "Drivetrain Work Post");
		assertEquals(aLine.getWorkPostsIterator().get(2).getName(), "Accessories Work Post");
		IteratorConverter<VehicleModel> conv = new IteratorConverter<VehicleModel>();
		List<VehicleModel> responsibleModels = conv.convert(aLine.getResponsibleModelsIterator());
		assertEquals(responsibleModels.get(0).getName(), "Car Model A");
		assertEquals(responsibleModels.get(1).getName(), "Car Model B");
	}
	
	@Test
	public void testBLine() {
		assertEquals(bLine.getNumberOfWorkPosts(), 3);
		assertEquals(bLine.getWorkPostsIterator().get(0).getName(), "Body Work Post");
		assertEquals(bLine.getWorkPostsIterator().get(1).getName(), "Drivetrain Work Post");
		assertEquals(bLine.getWorkPostsIterator().get(2).getName(), "Accessories Work Post");
		IteratorConverter<VehicleModel> conv = new IteratorConverter<VehicleModel>();
		List<VehicleModel> responsibleModels = conv.convert(bLine.getResponsibleModelsIterator());
		assertEquals(responsibleModels.get(0).getName(), "Car Model A");
		assertEquals(responsibleModels.get(1).getName(), "Car Model B");
		assertEquals(responsibleModels.get(2).getName(), "Car Model C");
	}
	
	@Test
	public void testCLine() {
		assertEquals(cLine.getNumberOfWorkPosts(), 5);
		assertEquals(cLine.getWorkPostsIterator().get(0).getName(), "Body Work Post");
		assertEquals(cLine.getWorkPostsIterator().get(1).getName(), "Cargo Work Post");
		assertEquals(cLine.getWorkPostsIterator().get(2).getName(), "Drivetrain Work Post");
		assertEquals(cLine.getWorkPostsIterator().get(3).getName(), "Accessories Work Post");
		assertEquals(cLine.getWorkPostsIterator().get(4).getName(), "Certification Work Post");
		IteratorConverter<VehicleModel> conv = new IteratorConverter<VehicleModel>();
		List<VehicleModel> responsibleModels = conv.convert(cLine.getResponsibleModelsIterator());
		assertEquals(responsibleModels.get(0).getName(), "Car Model A");
		assertEquals(responsibleModels.get(1).getName(), "Car Model B");
		assertEquals(responsibleModels.get(2).getName(), "Car Model C");
		assertEquals(responsibleModels.get(3).getName(), "Truck Model X");
		assertEquals(responsibleModels.get(4).getName(), "Truck Model Y");
	}

}
