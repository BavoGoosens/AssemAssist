package businessmodel.assemblyline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.MainScheduler;
import businessmodel.OrderManager;
import businessmodel.category.Color;
import businessmodel.category.Seats;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;
import businessmodel.util.IteratorConverter;


public class WorkPostTest {

	private WorkPost workpost;
	private StandardVehicleOrder order;

	@Before
	public void setUp() throws Exception {
		Catalog catalog = new Catalog();
		VehicleModel model = catalog.getAvailaleModelsClone().get(0);
		ArrayList<VehicleOptionCategory> categories = catalog.getAllCategories();
		ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();
		for (VehicleOptionCategory category: categories) {
			if (model.getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				chosen.add(model.getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
			}
		}
		this.order = new StandardVehicleOrder(new GarageHolder("Michiel", "Vandendriessche", "MichielVDD"), chosen, model);
		AssemblyLineAFactory factory = new AssemblyLineAFactory();
		this.workpost = new WorkPost("CarBodyPost", factory.createAssemblyLine(new MainScheduler(new OrderManager())));
		assertEquals("CarBodyPost", this.workpost.getName());
	}

	@Test
	public void testSetResponsibleTasks() {

		ArrayList<AssemblyTask> assemblytasks = new ArrayList<AssemblyTask>();
		AssemblyTask assem1 = new AssemblyTask("Test","Test", new Seats(), false);
		assemblytasks.add(assem1);
		assemblytasks.add(assem1);
		this.workpost.setResponsibleTasks(assemblytasks);
		assertEquals(this.workpost.getResponsibleTasksClone().get(0).getName(),"Test");
	}

	@Test
	public void testOrder(){
		ArrayList<AssemblyTask> responsibleTasks = new ArrayList<AssemblyTask>();
		responsibleTasks.add(new AssemblyTask("Test", "Tester", new Color(), this.workpost, false));
		this.workpost.setResponsibleTasks(responsibleTasks);
		this.workpost.setNewOrder(this.order);
		assertEquals(this.order,this.workpost.getOrder());
		ArrayList<AssemblyTask> pending = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(workpost.getPendingTasks());
		assertEquals(responsibleTasks.get(0).getName(), pending.get(0).getName());
		pending.get(0).completeAssemblytask(20);		
		ArrayList<AssemblyTask> finished = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(workpost.getFinishedTasks());
		assertEquals(responsibleTasks.get(0).getName(), finished.get(0).getName());
		assertTrue(this.workpost.isCompleted());
		this.workpost.switchOrders(null);
		assertEquals(this.workpost.getOrder(),null);
		this.workpost.setNewOrder(this.order);
		assertFalse(this.workpost.isCompleted());
		
		assertEquals(this.workpost.toString(),"CarBodyPost");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetName(){
		this.workpost = new WorkPost(null,new AssemblyLine());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPossiblyAssemblyTasks(){
		this.workpost.setResponsibleTasks(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPossiblyAssemblyTasks(){
		this.workpost.possibleAssemblyTasks(null);
	}
}
