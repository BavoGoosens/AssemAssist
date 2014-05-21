package control;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import businessmodel.VehicleManufacturingCompany;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.category.VehicleOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.Order;
import businessmodel.order.SingleTaskOrder;
import businessmodel.user.CustomShopManager;
import businessmodel.util.IteratorConverter;

public class ScenarioTestUC8 {
	
	private VehicleManufacturingCompany vmc;
	private SingleTaskOrderController stoc;
	private CustomShopManager csm;
	private ArrayList<AssemblyTask> tasks;

	@Before
	public void setUp() throws Exception {
		this.vmc = new VehicleManufacturingCompany();
		this.stoc = new SingleTaskOrderHandler(this.vmc);
		this.csm = new CustomShopManager("Michiel", "Vandendriessche", "MichielVDD");
		tasks = 
				(ArrayList<AssemblyTask>) new IteratorConverter<AssemblyTask>().
				convert(this.vmc.getAvailableTasks(this.csm));
		Collections.sort(tasks, new Comparator<AssemblyTask>() {
			@Override
			public int compare(AssemblyTask t1, AssemblyTask t2) {
				return t1.toString().compareTo(t2.toString());
			}
		});
	}

	@Test
	public void testAvailableTasks() {
		assertEquals(2, tasks.size());
		assertEquals("Install Seats", tasks.get(0).toString());
		assertEquals("Paint Vehicle", tasks.get(1).toString());
	}
	
	@Test
	public void testAvailableOptions() {
		AssemblyTask task = this.tasks.get(0);
		ArrayList<VehicleOption> options = task.getInstallableOptions();
		assertEquals("SEATS: leather white", options.get(0).toString());
		assertEquals("SEATS: leather black", options.get(1).toString());
		assertEquals("SEATS: vinyl grey", options.get(2).toString());
		assertEquals("SEATS: vinyl black", options.get(3).toString());
		task = this.tasks.get(1);
		options = task.getInstallableOptions();
		assertEquals("COLOR: red", options.get(0).toString());
		assertEquals("COLOR: blue", options.get(1).toString()); 
		assertEquals("COLOR: black", options.get(2).toString());
		assertEquals("COLOR: white", options.get(3).toString());
		assertEquals("COLOR: green", options.get(4).toString());
		assertEquals("COLOR: yellow", options.get(5).toString());
	}
	
	@Test
	public void testOrderTask() throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
		AssemblyTask task = this.tasks.get(0);
		ArrayList<VehicleOption> options = task.getInstallableOptions();
		VehicleOption option = options.get(0);
		ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();
		chosen.add(option);
		DateTime deadline = new DateTime(2014, 6, 2, 8, 0);
		SingleTaskOrder order = new SingleTaskOrder(this.csm, chosen, deadline);
		this.stoc.placeSingleTaskOrder(this.csm, order);
		ArrayList<Order> pending = (ArrayList<Order>) new IteratorConverter<Order>().convert(vmc.getPendingOrders(this.csm));
		assertTrue(pending.contains(order));
	}

}
