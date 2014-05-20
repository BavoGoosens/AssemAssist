package control;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.assemblyline.WorkPost;
import businessmodel.category.Body;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;
import businessmodel.user.Mechanic;
import businessmodel.util.IteratorConverter;

public class ScenarioTestUC3 {
	
	private VehicleManufacturingCompany vmc;
	private AssemblyLineController ac;
	private Mechanic mechanic;
	private ArrayList<AssemblyLine> lines;

	@Before
	public void setUp() throws Exception {
		this.vmc = new VehicleManufacturingCompany();
		this.ac = new AssemblyLineHandler(this.vmc);
		this.mechanic = new Mechanic("Michiel", "Vandendriessche", "MichielVDD");
		this.lines = (ArrayList<AssemblyLine>) new IteratorConverter<AssemblyLine>().convert(this.vmc.getAssemblyLines(this.mechanic));
		this.placeOrders();
	}

	@Test
	public void testAvailableAssemblyLines() throws NoClearanceException {
		assertEquals("A 3", lines.get(0).toString());
		assertEquals("B 3", lines.get(1).toString());
		assertEquals("C 5", lines.get(2).toString());
	}
	
	@Test
	public void testAvailableWorkPostALine() {
		ArrayList<WorkPost> posts =
				(ArrayList<WorkPost>) new IteratorConverter<WorkPost>().convert(this.vmc.getWorkPosts(this.mechanic, this.lines.get(0)));
		assertEquals("Body Work Post", posts.get(0).toString());
		assertEquals("Drivetrain Work Post", posts.get(1).toString());
		assertEquals("Accessories Work Post", posts.get(2).toString());
	}
	
	@Test
	public void testAvailableWorkPostBLine() {
		ArrayList<WorkPost> posts =
				(ArrayList<WorkPost>) new IteratorConverter<WorkPost>().convert(this.vmc.getWorkPosts(this.mechanic, this.lines.get(1)));
		assertEquals("Body Work Post", posts.get(0).toString());
		assertEquals("Drivetrain Work Post", posts.get(1).toString());
		assertEquals("Accessories Work Post", posts.get(2).toString());
	}
	
	@Test
	public void testAvailableWorkPostCLine() {
		ArrayList<WorkPost> posts =
				(ArrayList<WorkPost>) new IteratorConverter<WorkPost>().convert(this.vmc.getWorkPosts(this.mechanic, this.lines.get(2)));
		assertEquals("Body Work Post", posts.get(0).toString());
		assertEquals("Cargo Work Post", posts.get(1).toString());
		assertEquals("Drivetrain Work Post", posts.get(2).toString());
		assertEquals("Accessories Work Post", posts.get(3).toString());
		assertEquals("Certification Work Post", posts.get(4).toString());
	}
	
	@Test
	public void testAvailableTasksALine() throws NoClearanceException {
		IteratorConverter<AssemblyTask> converter = new IteratorConverter<AssemblyTask>();
		ArrayList<WorkPost> posts =
				(ArrayList<WorkPost>) new IteratorConverter<WorkPost>().convert(this.vmc.getWorkPosts(this.mechanic, this.lines.get(0)));
		
		ArrayList<AssemblyTask> tasks = 
				(ArrayList<AssemblyTask>) converter.convert(posts.get(0).getPendingTasks());
		assertEquals("Assembly Vehicle Body", tasks.get(0).toString());
		assertEquals("Paint Vehicle", tasks.get(1).toString());
		for (AssemblyTask task: tasks) {
			this.ac.finishTask(this.mechanic, task, 20);
		}
		
		assertEquals(((ArrayList<AssemblyTask>) converter.convert(posts.get(0).getPendingTasks())).size(), 0);
		
		tasks = (ArrayList<AssemblyTask>) converter.convert(posts.get(1).getPendingTasks());
		assertEquals("Insert Engine", tasks.get(0).toString());
		assertEquals("Insert Gearbox", tasks.get(1).toString());
		for (AssemblyTask task: tasks) {
			this.ac.finishTask(this.mechanic, task, 20);
		}
		
		assertEquals(((ArrayList<AssemblyTask>) converter.convert(posts.get(1).getPendingTasks())).size(), 0);
		
		tasks = (ArrayList<AssemblyTask>) converter.convert(posts.get(2).getPendingTasks());
		assertEquals("Install Seats", tasks.get(0).toString());
		assertEquals("Install Airco", tasks.get(1).toString());
		assertEquals("Mount Wheels", tasks.get(2).toString());
		assertEquals("Install Spoiler", tasks.get(3).toString());
		for (AssemblyTask task: tasks) {
			this.ac.finishTask(this.mechanic, task, 20);
		}
		
		assertEquals(((ArrayList<AssemblyTask>) converter.convert(posts.get(2).getPendingTasks())).size(), 0);
		
	}
	
	@Test
	public void testAvailableTasksBLine() {
		IteratorConverter<AssemblyTask> converter = new IteratorConverter<AssemblyTask>();
		ArrayList<WorkPost> posts =
				(ArrayList<WorkPost>) new IteratorConverter<WorkPost>().
				convert(this.vmc.getWorkPosts(this.mechanic, this.lines.get(1)));
		ArrayList<AssemblyTask> tasks = 
				(ArrayList<AssemblyTask>) converter.convert(posts.get(0).getPendingTasks());
		assertEquals(0, tasks.size());
		tasks = (ArrayList<AssemblyTask>) converter.convert(posts.get(1).getPendingTasks());
		assertEquals(0, tasks.size());
		tasks = (ArrayList<AssemblyTask>) converter.convert(posts.get(2).getPendingTasks());
		assertEquals(0, tasks.size());
	}
	
	@Test
	public void testAvailableTasksCLine() throws NoClearanceException {
		IteratorConverter<AssemblyTask> converter = new IteratorConverter<AssemblyTask>();
		ArrayList<WorkPost> posts =
				(ArrayList<WorkPost>) new IteratorConverter<WorkPost>().
				convert(this.vmc.getWorkPosts(this.mechanic, this.lines.get(2)));
		
		ArrayList<AssemblyTask> tasks = 
				(ArrayList<AssemblyTask>) converter.convert(posts.get(0).getPendingTasks());
		assertEquals("Assembly Vehicle Body", tasks.get(0).toString());
		assertEquals("Paint Vehicle", tasks.get(1).toString());
		for (AssemblyTask task: tasks) {
			this.ac.finishTask(this.mechanic, task, 20);
		}
		
		assertEquals(((ArrayList<AssemblyTask>) converter.convert(posts.get(0).getPendingTasks())).size(), 0);
		
		tasks = (ArrayList<AssemblyTask>) converter.convert(posts.get(1).getPendingTasks());
		assertEquals("Install Tool Storage", tasks.get(0).toString());
		assertEquals("Add Cargo Protection", tasks.get(1).toString());
		for (AssemblyTask task: tasks) {
			this.ac.finishTask(this.mechanic, task, 20);
		}
		
		assertEquals(((ArrayList<AssemblyTask>) converter.convert(posts.get(1).getPendingTasks())).size(), 0);
		
		tasks = (ArrayList<AssemblyTask>) converter.convert(posts.get(2).getPendingTasks());
		assertEquals("Insert Engine", tasks.get(0).toString());
		assertEquals("Insert Gearbox", tasks.get(1).toString());
		for (AssemblyTask task: tasks) {
			this.ac.finishTask(this.mechanic, task, 20);
		}
		
		assertEquals(((ArrayList<AssemblyTask>) converter.convert(posts.get(2).getPendingTasks())).size(), 0);
		
		tasks = (ArrayList<AssemblyTask>) converter.convert(posts.get(3).getPendingTasks());
		assertEquals("Install Seats", tasks.get(0).toString());
		assertEquals("Install Airco", tasks.get(1).toString());
		assertEquals("Mount Wheels", tasks.get(2).toString());
		for (AssemblyTask task: tasks) {
			this.ac.finishTask(this.mechanic, task, 20);
		}
		
		assertEquals(((ArrayList<AssemblyTask>) converter.convert(posts.get(3).getPendingTasks())).size(), 0);
		
		tasks = (ArrayList<AssemblyTask>) converter.convert(posts.get(4).getPendingTasks());
		assertEquals("Certify Maximum Cargo Load", tasks.get(0).toString());
		for (AssemblyTask task: tasks) {
			this.ac.finishTask(this.mechanic, task, 20);
		}
		
		assertEquals(((ArrayList<AssemblyTask>) converter.convert(posts.get(4).getPendingTasks())).size(), 0);
	}
	
	private void placeOrders() throws NoClearanceException, UnsatisfiedRestrictionException {
		Catalog catalog = new Catalog();
		ArrayList<VehicleOptionCategory> categories = catalog.getAllCategories();
		GarageHolder garageHolder = new GarageHolder("Bouwe", "Ceunen", "BouweC");
		ArrayList<VehicleModel> models = catalog.getAvailaleModelsClone();
		
		ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();
		for (VehicleOptionCategory category: categories) {
			if (models.get(1).getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				chosen.add(models.get(1).getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
			}
		}
		
		StandardVehicleOrder order = new StandardVehicleOrder(garageHolder, chosen, models.get(1));
		this.vmc.placeOrder(order);
		System.out.println(order);
		chosen.clear();
		
		for (VehicleOptionCategory category: categories) {
			if (models.get(3).getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				if (category.equals(new Body())) {
					chosen.add(models.get(3).getVehicleModelSpecification().getOptionsOfCategory(category).get(1));
				} else {
					chosen.add(models.get(3).getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
				}
			}
		}
		
		order = new StandardVehicleOrder(garageHolder, chosen, models.get(3));
		this.vmc.placeOrder(order);
		System.out.println(order);
	}

}
