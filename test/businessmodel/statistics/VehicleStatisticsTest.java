package businessmodel.statistics;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.OrderManager;
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
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;

public class VehicleStatisticsTest {
	
	VehicleStatistics vehicleStatistics;

    @Before
    public void setUp() throws Exception {
    	ArrayList<StandardVehicleOrder> orders = new ArrayList<StandardVehicleOrder>();
    	GarageHolder gh = new GarageHolder("Michiel", "Vandendriessche", "michielvdd");
        OrderManager om = new OrderManager();
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
        orders.add(new StandardVehicleOrder(gh, chosenY, modelY));
        orders.add(new StandardVehicleOrder(gh, chosenY, modelY));
        
        /**
         * Plaats alle orders
         */
        for (StandardVehicleOrder order: orders) {
        	om.placeOrder(order);
        }
        
        /**
         * Voer alle taken uit
         */
        for (AssemblyLine assemblyLine: om.getMainScheduler().getAssemblyLines()) {
        	for (WorkPost workPost: assemblyLine.getWorkPosts()) {
        		while(workPost.getPendingTasks().hasNext()) {
        			AssemblyTask task = workPost.getPendingTasks().next();
        			task.completeAssemblytask(60);
        		}
        	}
        } 
    }
    
    @Test
    public void test() {
    	System.out.println(vehicleStatistics.getNumberOfVehicles());
    }
}