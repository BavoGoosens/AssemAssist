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
import businessmodel.category.Body;
import businessmodel.category.ModelAFactory;
import businessmodel.category.ModelBFactory;
import businessmodel.category.ModelCFactory;
import businessmodel.category.ModelXFactory;
import businessmodel.category.ModelYFactory;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.category.Wheels;
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
        
        this.placeOrders(om);
        
        int days = 10;
		for (int i = 0; i < days; i ++)
			this.processOrders();
    }
    
    private void processOrders() throws NoClearanceException {
		IteratorConverter<WorkPost> converter = new IteratorConverter<>();
		for(AssemblyLine iter1: this.om.getMainScheduler().getAssemblyLines()){
			looping = true;
			while (looping == true ){
				CompleteWorkPost(iter1, converter.convert(iter1.getWorkPostsIterator()).size());
			}
		}

	}

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
	
	private void placeOrders(OrderManager om) throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
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
		for (int i = 0; i <= 30; i++) {
			om.placeOrder(new StandardVehicleOrder(garageHolder, chosen, models.get(1)));
		}
		
	}

    @Test
	public void test() {
		for (Tuple<LocalDate, Integer> tuple: this.vehicleStatistics.getLastDays(2)) {
			System.out.println(tuple.getX());
			System.out.println(tuple.getY());
		}
		System.out.println(vehicleStatistics.getLastDays(2).size());
		System.out.println(om.getCompletedOrders().size());
	}
}