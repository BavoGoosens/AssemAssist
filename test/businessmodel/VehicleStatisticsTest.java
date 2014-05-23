package businessmodel;

import java.util.ArrayList;
import java.util.Iterator;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.assemblyline.WorkPost;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.statistics.StatisticsManager;
import businessmodel.statistics.VehicleStatistics;
import businessmodel.user.GarageHolder;
import businessmodel.util.IteratorConverter;
import businessmodel.util.Tuple;

public class VehicleStatisticsTest {
	
	VehicleStatistics vehicleStatistics;
	OrderManager om;
	boolean looping;

    @Before
    public void setUp() throws Exception {
    	GarageHolder gh = new GarageHolder("Michiel", "Vandendriessche", "michielvdd");
        this.om = new OrderManager();
        StatisticsManager statisticsManager = new StatisticsManager(om);
        vehicleStatistics = statisticsManager.getVehicleStatistics();
        
        this.placeOrders();
        
        int days = 15;
		for (int i = 0; i < days; i ++)
			this.processOrders();
    }
    
    private void processOrders() throws NoClearanceException {
		IteratorConverter<WorkPost> converter = new IteratorConverter<>();
		Iterator<AssemblyLine> iter1 = om.getMainScheduler().getAssemblyLines().iterator();
		DateTime beginDateTime = this.om.getMainScheduler().getTime();
		while(iter1.hasNext()){
			looping = true;
			AssemblyLine assem = iter1.next();
			DateTime assemblyLineDateTime = assem.getAssemblyLineScheduler().getCurrentTime();
			DateTime result = assemblyLineDateTime.minus(beginDateTime.getMillis());

			while (looping == true && result.getMillis() < 86400000){
				assemblyLineDateTime = assem.getAssemblyLineScheduler().getCurrentTime();
				result = assemblyLineDateTime.minus(beginDateTime.getMillis());
				CompleteWorkPost(assem, converter.convert(assem.getWorkPostsIterator()).size());
			}
		}
	}

	/**
	 * Complete the WorkPosts from the given AssemblyLine.
	 * @param assem
	 * @param i
	 * @throws NoClearanceException
	 */
	private void CompleteWorkPost(AssemblyLine assem, int i) throws NoClearanceException{
		looping = false;
		for(int j = 0 ; j < i ; j++){
			IteratorConverter<WorkPost> converter = new IteratorConverter<>();
			WorkPost wp1 = converter.convert(assem.getWorkPostsIterator()).get(j);
			Iterator<AssemblyTask> iter2 = wp1.getPendingTasks();
			while (iter2.hasNext()){
				AssemblyTask task = iter2.next();
				task.completeAssemblytask(20);
				looping = true;
			}
		}
	}
	
	private void placeOrders() throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
		Catalog catalog = new Catalog();
		ArrayList<VehicleOptionCategory> categories = catalog.getAllCategories();
		GarageHolder garageHolder = new GarageHolder("Bouwe", "Ceunen", "BouweC");
		ArrayList<VehicleModel> models = catalog.getAvailaleModelsClone();
		
		ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();
		for (VehicleOptionCategory category: categories) {
			if (models.get(0).getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				chosen.add(models.get(0).getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
			}
		}

		for (int i = 0; i < 100; i++) {
			this.om.placeOrder(new StandardVehicleOrder(garageHolder, chosen, models.get(0)));
		}
		
	}
	

    @Test
	public void test() {
		for (Tuple<LocalDate, Integer> tuple: this.vehicleStatistics.getLastDays(1)) {
			System.out.println(tuple.getX());
			System.out.println(tuple.getY());
		}
	}
}