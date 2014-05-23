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
    	ArrayList<StandardVehicleOrder> orders = new ArrayList<StandardVehicleOrder>();
    	GarageHolder gh = new GarageHolder("Michiel", "Vandendriessche", "michielvdd");
        this.om = new OrderManager();
        StatisticsManager statisticsManager = new StatisticsManager(om);
        vehicleStatistics = statisticsManager.getVehicleStatistics();
        ArrayList<VehicleOptionCategory> categories = new Catalog().getAllCategories();
        
        /**
         * Plaats orders van model A
         */
        VehicleModel modelA = new ModelAFactory().createModel();
        ArrayList<VehicleOption> chosenA = new ArrayList<VehicleOption>();
        for (VehicleOptionCategory category: categories) {
        	if (modelA.getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
        		chosenA.add(modelA.getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
        	}
        }
        orders.add(new StandardVehicleOrder(gh, chosenA, modelA));
        orders.add(new StandardVehicleOrder(gh, chosenA, modelA));
        orders.add(new StandardVehicleOrder(gh, chosenA, modelA));
        orders.add(new StandardVehicleOrder(gh, chosenA, modelA));
        orders.add(new StandardVehicleOrder(gh, chosenA, modelA));
        orders.add(new StandardVehicleOrder(gh, chosenA, modelA));
        orders.add(new StandardVehicleOrder(gh, chosenA, modelA));
        orders.add(new StandardVehicleOrder(gh, chosenA, modelA));
        
        /**
         * Plaats orders van model B
         */
        VehicleModel modelB = new ModelBFactory().createModel();
        ArrayList<VehicleOption> chosenB = new ArrayList<VehicleOption>();
        for (VehicleOptionCategory category: categories) {
        	if (modelB.getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
        		chosenB.add(modelB.getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
        	}
        }
        orders.add(new StandardVehicleOrder(gh, chosenB, modelB));
        orders.add(new StandardVehicleOrder(gh, chosenB, modelB));
        orders.add(new StandardVehicleOrder(gh, chosenB, modelB));
        orders.add(new StandardVehicleOrder(gh, chosenB, modelB));
        orders.add(new StandardVehicleOrder(gh, chosenB, modelB));
        orders.add(new StandardVehicleOrder(gh, chosenB, modelB));
        orders.add(new StandardVehicleOrder(gh, chosenB, modelB));
        orders.add(new StandardVehicleOrder(gh, chosenB, modelB));
        
        /**
         * Plaats orders van model C
         */
        VehicleModel modelC = new ModelCFactory().createModel();
        ArrayList<VehicleOption> chosenC = new ArrayList<VehicleOption>();
        for (VehicleOptionCategory category: categories) {
        	if (modelC.getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
        		chosenC.add(modelC.getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
        	}
        }
        orders.add(new StandardVehicleOrder(gh, chosenC, modelC));
        orders.add(new StandardVehicleOrder(gh, chosenC, modelC));
        orders.add(new StandardVehicleOrder(gh, chosenC, modelC));
        orders.add(new StandardVehicleOrder(gh, chosenC, modelC));
        orders.add(new StandardVehicleOrder(gh, chosenC, modelC));
        orders.add(new StandardVehicleOrder(gh, chosenC, modelC));
        orders.add(new StandardVehicleOrder(gh, chosenC, modelC));
        orders.add(new StandardVehicleOrder(gh, chosenC, modelC)); 
        
        /**
         * Plaats orders van model X
         */
        VehicleModel modelX = new ModelXFactory().createModel();
        ArrayList<VehicleOption> chosenX = new ArrayList<VehicleOption>();
        for (VehicleOptionCategory category: categories) {
        	if (category.equals(new Body())) {
        		chosenX.add(modelX.getVehicleModelSpecification().getOptionsOfCategory(category).get(1));
        	}
        	else if (modelX.getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
        		chosenX.add(modelX.getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
        	}
        }
        orders.add(new StandardVehicleOrder(gh, chosenX, modelX));
        orders.add(new StandardVehicleOrder(gh, chosenX, modelX));
        orders.add(new StandardVehicleOrder(gh, chosenX, modelX));
        orders.add(new StandardVehicleOrder(gh, chosenX, modelX));
        orders.add(new StandardVehicleOrder(gh, chosenX, modelX));
        orders.add(new StandardVehicleOrder(gh, chosenX, modelX));
        orders.add(new StandardVehicleOrder(gh, chosenX, modelX));
        orders.add(new StandardVehicleOrder(gh, chosenX, modelX));
        
        /**
         * Plaats orders van model Y
         */
        VehicleModel modelY = new ModelYFactory().createModel();
        ArrayList<VehicleOption> chosenY = new ArrayList<VehicleOption>();
        for (VehicleOptionCategory category: categories) {
        	if (category.equals(new Wheels())) {
        		chosenY.add(modelY.getVehicleModelSpecification().getOptionsOfCategory(category).get(1));
        	}
        	else if (modelY.getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
        		chosenY.add(modelY.getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
        	}
        }
        orders.add(new StandardVehicleOrder(gh, chosenY, modelY));
        orders.add(new StandardVehicleOrder(gh, chosenY, modelY));
        orders.add(new StandardVehicleOrder(gh, chosenY, modelY));
        orders.add(new StandardVehicleOrder(gh, chosenY, modelY));
        orders.add(new StandardVehicleOrder(gh, chosenY, modelY));
        orders.add(new StandardVehicleOrder(gh, chosenY, modelY));
        orders.add(new StandardVehicleOrder(gh, chosenY, modelY));
        orders.add(new StandardVehicleOrder(gh, chosenY, modelY));
        
        /**
         * Plaats alle orders
         */
        for (StandardVehicleOrder order: orders) {
        	om.placeOrder(order);
        }
        
        int days = 10;
		for (int i = 0; i < days; i ++)
			this.processOrders(om);
    }
    
    private void processOrders(OrderManager om) throws NoClearanceException {
		IteratorConverter<WorkPost> converter = new IteratorConverter<>();
		Iterator<AssemblyLine> iter1 = om.getMainScheduler().getAssemblyLines().iterator();
		DateTime beginDateTime = om.getMainScheduler().getTime();
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

    @Test
	public void test() {
		for (Tuple<LocalDate, Integer> tuple: this.vehicleStatistics.getLastDays(2)) {
			System.out.println(tuple.getX());
			System.out.println(tuple.getY());
		}
		System.out.println(om.getCompletedOrders().size());
	}
}