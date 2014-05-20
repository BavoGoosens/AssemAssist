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

public class ScenarioTestUC4 {
	
	private VehicleManufacturingCompany vmc;
	private Mechanic mechanic;
	private ArrayList<AssemblyLine> lines;

	@Before
	public void setUp() throws Exception {
		this.vmc = new VehicleManufacturingCompany();
		this.mechanic = new Mechanic("Michiel", "Vandendriessche", "MichielVDD");
		this.lines = (ArrayList<AssemblyLine>) new IteratorConverter<AssemblyLine>().convert(this.vmc.getAssemblyLines(this.mechanic));
		this.placeOrders();
		this.finishSomeTasks();
	}

	@Test
	public void testAssemblyLineA() {
		AssemblyLine line = this.lines.get(0);
		ArrayList<WorkPost> posts = (ArrayList<WorkPost>) new IteratorConverter<WorkPost>().convert(line.getWorkPostsIterator());
		
		WorkPost post = posts.get(0);
		ArrayList<AssemblyTask> pendingTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getPendingTasks());
		ArrayList<AssemblyTask> finishedTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getFinishedTasks());
		assertEquals(0, pendingTasks.size());
		assertEquals("Assembly Vehicle Body", finishedTasks.get(0).toString());
		assertEquals("Paint Vehicle", finishedTasks.get(1).toString());
		
		post = posts.get(1);
		pendingTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getPendingTasks());
		finishedTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getFinishedTasks());
		assertEquals("Insert Engine", pendingTasks.get(0).toString());
		assertEquals("Insert Gearbox", pendingTasks.get(1).toString());
		assertEquals(0, finishedTasks.size());
		
		post = posts.get(2);
		pendingTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getPendingTasks());
		finishedTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getFinishedTasks());
		assertEquals(0, pendingTasks.size());
		assertEquals(0, finishedTasks.size());
	}
	
	@Test
	public void testAssemblyLineB() {
		AssemblyLine line = this.lines.get(1);
		ArrayList<WorkPost> posts = (ArrayList<WorkPost>) new IteratorConverter<WorkPost>().convert(line.getWorkPostsIterator());
		
		WorkPost post = posts.get(0);
		ArrayList<AssemblyTask> pendingTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getPendingTasks());
		ArrayList<AssemblyTask> finishedTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getFinishedTasks());
		assertEquals(0, pendingTasks.size());
		assertEquals(0, finishedTasks.size());
		
		post = posts.get(1);
		pendingTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getPendingTasks());
		finishedTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getFinishedTasks());
		assertEquals(0, pendingTasks.size());
		assertEquals(0, finishedTasks.size());
		
		post = posts.get(2);
		pendingTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getPendingTasks());
		finishedTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getFinishedTasks());
		assertEquals(0, pendingTasks.size());
		assertEquals(0, finishedTasks.size());
		
	}
	
	@Test
	public void testAssemblyLineC() {
		AssemblyLine line = this.lines.get(2);
		ArrayList<WorkPost> posts = (ArrayList<WorkPost>) new IteratorConverter<WorkPost>().convert(line.getWorkPostsIterator());
		
		WorkPost post = posts.get(0);
		ArrayList<AssemblyTask> pendingTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getPendingTasks());
		ArrayList<AssemblyTask> finishedTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getFinishedTasks());
		assertEquals("Assembly Vehicle Body", pendingTasks.get(0).toString());
		assertEquals("Paint Vehicle", pendingTasks.get(1).toString());
		assertEquals(0, finishedTasks.size());
		
		post = posts.get(1);
		pendingTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getPendingTasks());
		finishedTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getFinishedTasks());
		assertEquals(0, pendingTasks.size());
		assertEquals(0, finishedTasks.size());
		
		post = posts.get(2);
		pendingTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getPendingTasks());
		finishedTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getFinishedTasks());
		assertEquals(0, pendingTasks.size());
		assertEquals(0, finishedTasks.size());
		
		post = posts.get(3);
		pendingTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getPendingTasks());
		finishedTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getFinishedTasks());
		assertEquals(0, pendingTasks.size());
		assertEquals(0, finishedTasks.size());
		
		post = posts.get(4);
		pendingTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getPendingTasks());
		finishedTasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(post.getFinishedTasks());
		assertEquals(0, pendingTasks.size());
		assertEquals(0, finishedTasks.size());
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
	}
	
	private void finishSomeTasks() {
		AssemblyLine line = this.lines.get(0);
		ArrayList<WorkPost> posts = (ArrayList<WorkPost>) new IteratorConverter<WorkPost>().convert(line.getWorkPostsIterator());
		ArrayList<AssemblyTask> tasks = (ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().convert(posts.get(0).getPendingTasks());
		for (AssemblyTask task: tasks) {
			task.completeAssemblytask(20);
		}
	}

}
