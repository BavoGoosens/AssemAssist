package control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.joda.time.DateTime;

import businessmodel.Catalog;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.assemblyline.WorkPost;
import businessmodel.category.Airco;
import businessmodel.category.Body;
import businessmodel.category.Certification;
import businessmodel.category.Color;
import businessmodel.category.Engine;
import businessmodel.category.Gearbox;
import businessmodel.category.ModelAFactory;
import businessmodel.category.ModelBFactory;
import businessmodel.category.ModelCFactory;
import businessmodel.category.ModelXFactory;
import businessmodel.category.ModelYFactory;
import businessmodel.category.Protection;
import businessmodel.category.Seats;
import businessmodel.category.Spoiler;
import businessmodel.category.Storage;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.category.Wheels;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.Order;
import businessmodel.order.SingleTaskOrder;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.User;
import businessmodel.util.IteratorConverter;

/**
 * The initial data.
 *
 * @author Team 10
 *
 */
public class InitialData {

	private Random rnd = new Random();
	private StandardOrderHandler controllerStandard;
	private SingleTaskOrderHandler controllerSingleTask;
	private Iterator<VehicleModel> iter;
	private ArrayList<VehicleModel> available_vehiclemodels;
	private ArrayList<VehicleOption> chosen;
	private ArrayList<VehicleOption> airco, body, color, engine, gearbox, seats, spoiler, wheels, certification, protection, storage;
	private VehicleManufacturingCompany vmc;
	private User mechanic;
	private User garageholder;
	private User customsManager;
	private ArrayList<Integer> batchList;
	private VehicleModel model;
	private boolean looping = true;
	private int orderCount = 0;
	private final long DAY_IN_MILIS = 86400000;
	private ArrayList<VehicleOption> chosenA, chosenB, chosenC, chosenX, chosenY;
	private VehicleModel modelA, modelB, modelC, modelX, modelY;
	/**
	 * Constructor for InitialData. Initialize lists.
	 */
	public InitialData(){

		this.airco = new ArrayList<VehicleOption>();
		this.body = new ArrayList<VehicleOption>();
		this.color= new ArrayList<VehicleOption>();
		this.engine = new ArrayList<VehicleOption>();
		this.gearbox = new ArrayList<VehicleOption>();
		this.seats = new ArrayList<VehicleOption>();
		this.spoiler = new ArrayList<VehicleOption>();
		this.wheels = new ArrayList<VehicleOption>();
		this.certification = new ArrayList<VehicleOption>();
		this.protection = new ArrayList<VehicleOption>();
		this.storage = new ArrayList<VehicleOption>();


	}

	/**
	 * Initialize with the given VehicleManufacturingCompany.
	 * @param vmc
	 * @throws NoClearanceException
	 */
	protected void initialize(VehicleManufacturingCompany vmc) throws NoClearanceException{

		this.vmc = vmc;
		this.garageholder = vmc.login("wow", "");
		this.mechanic = vmc.login("woww", "");
		this.customsManager = vmc.login("wowwww", "");

		this.controllerStandard = new StandardOrderHandler(vmc);

		this.controllerSingleTask = new SingleTaskOrderHandler(vmc);
		this.iter = vmc.getVehicleModels(this.garageholder);
		this.available_vehiclemodels = new ArrayList<VehicleModel>();
		this.chosen = new ArrayList<VehicleOption>();

		this.batchList = new ArrayList<Integer>();

		while (iter.hasNext())
			this.available_vehiclemodels.add(this.iter.next());

		Boolean orders = false;

		ArrayList<Integer> numbers = this.generateOrders();

		this.initialize();

		System.out.println(numbers.size());
		for(int i=0; i < numbers.size(); i++){
			orders = this.randomOrderGenerator("standard",numbers.get(i));
			if (!orders)
				this.makeStandardOrder(numbers.get(i));
		}

		System.out.println(this.orderCount);

		//This integer can be changed to the numbers of days you wish to be
		//completed by the system.
		        int days = 1;
		        for (int i = 0; i < days; i ++)
				    this.processOrders();


		//		orders = false;
		//		for(int i=0; i < 3; i++){
		//			orders = this.randomOrderGenerator("singleTask",-1);
		//			if (!orders)
		//				this.randomOrderGenerator("singleTask", 0);
		//		}
		//
		//		this.makeOrdersNotInSameBatch();
		//
		//		orders = false;
		//		for(int i=0; i < 3; i++){
		//			orders = this.randomOrderGenerator("standard",-1);
		//			if (!orders)
		//				this.randomOrderGenerator("standard", 0);
		//		}



	}

	private void makeStandardOrder(int model) {
		try{
			if (model == 0){
				StandardVehicleOrder modelAOrder = new StandardVehicleOrder(this.garageholder, this.chosenA, this.modelA);
				this.controllerStandard.placeOrder(this.garageholder,modelAOrder);
				this.orderCount ++;
			}else if (model == 1){
				StandardVehicleOrder modelBOrder = new StandardVehicleOrder(this.garageholder, this.chosenB, this.modelB);
				this.controllerStandard.placeOrder(this.garageholder,modelBOrder);
				this.orderCount ++;
			}else if (model == 2){
				StandardVehicleOrder modelCOrder = new StandardVehicleOrder(this.garageholder, this.chosenC, this.modelC);
				this.controllerStandard.placeOrder(this.garageholder,modelCOrder);
				this.orderCount ++;
			}else if (model == 3){
				StandardVehicleOrder modelXOrder = new StandardVehicleOrder(this.garageholder, this.chosenX, this.modelX);
				this.controllerStandard.placeOrder(this.garageholder,modelXOrder);
				this.orderCount ++;
			}else if (model == 4){
				StandardVehicleOrder modelYOrder = new StandardVehicleOrder(this.garageholder, this.chosenY, this.modelY);
				this.controllerStandard.placeOrder(this.garageholder,modelYOrder);
				this.orderCount ++;
			}

		} catch (Exception ex){
			System.out.println(ex.toString());
		}

	}
	/**
	 * Generate 3 orders that are not in the same batch.
	 */
	private void initialize() {

		ArrayList<Order> orders = new ArrayList<Order>();
		ArrayList<VehicleOptionCategory> categories = new Catalog().getAllCategories();

		this.modelA = new ModelAFactory().createModel();
		this.modelB = new ModelBFactory().createModel();
		this.modelC = new ModelCFactory().createModel();
		this.modelX = new ModelXFactory().createModel();
		this.modelY = new ModelYFactory().createModel();

		this.chosenA = new ArrayList<VehicleOption>();
		this.chosenB = new ArrayList<VehicleOption>();
		this.chosenC = new ArrayList<VehicleOption>();
		this.chosenX = new ArrayList<VehicleOption>();
		this.chosenY = new ArrayList<VehicleOption>();

		for (VehicleOptionCategory category: categories) {
			if (modelA.getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				chosenA.add(modelA.getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
			}
			if (modelB.getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				if (category.equals(new Spoiler()))
					chosenB.add(modelB.getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
				else
					chosenB.add(modelB.getVehicleModelSpecification().getOptionsOfCategory(category).get(1));
			}
			if (modelC.getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				if (category.equals(new Wheels()))
					chosenC.add(modelC.getVehicleModelSpecification().getOptionsOfCategory(category).get(1));
				else
					chosenC.add(modelC.getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
			}
			if (modelX.getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				if (category.equals(new Wheels()))
					chosenX.add(modelX.getVehicleModelSpecification().getOptionsOfCategory(category).get(1));
				else
					chosenX.add(modelX.getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
			}
			if (modelY.getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				if (category.equals(new Wheels()))
					chosenY.add(modelY.getVehicleModelSpecification().getOptionsOfCategory(category).get(1));
				else
					chosenY.add(modelY.getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
			}

		}

	}

	/**
	 * Generate random orders.
	 * @return
	 */
	private ArrayList<Integer> generateOrders() {
		ArrayList<Integer> number = new ArrayList<Integer>();
		number.add(1); number.add(2); number.add(3); number.add(4); number.add(0);	number.add(1);	number.add(1);	number.add(2);	number.add(2); number.add(3); number.add(3);
		number.add(4); number.add(4); number.add(0); number.add(0);	number.add(4);	number.add(3);	number.add(2);	number.add(1);	number.add(4); number.add(1); number.add(1);
		number.add(4); number.add(2); number.add(1); number.add(4); number.add(1);  number.add(1);	number.add(3); 	number.add(4); number.add(2); number.add(1); number.add(4);
		number.add(1); number.add(2); number.add(3); number.add(4); number.add(0);	number.add(1);	number.add(1);	number.add(2);	number.add(2); number.add(3); number.add(3);
		number.add(4); number.add(2); number.add(1); number.add(4); number.add(1);  number.add(1);	number.add(3); 	number.add(4); number.add(2); number.add(1); number.add(4);
		number.add(4); number.add(4); number.add(0); number.add(0);	number.add(4);	number.add(3);	number.add(2);	number.add(1);	number.add(4); number.add(1); number.add(1);
		number.add(4); number.add(2); number.add(1); number.add(4); number.add(1);  number.add(1);	number.add(3); 	number.add(4); number.add(2); number.add(1); number.add(4);
		number.add(4); number.add(2); number.add(1); number.add(4); number.add(1);  number.add(1);	number.add(3); 	number.add(4); number.add(2); number.add(1); number.add(4);
		number.add(1); number.add(2); number.add(3); number.add(4); number.add(0);	number.add(1);	number.add(1);	number.add(2);	number.add(2); number.add(3); number.add(3);
		number.add(4); number.add(2); number.add(1); number.add(4); number.add(1);  number.add(1);	number.add(3); 	number.add(4); number.add(2); number.add(1); number.add(4);
		number.add(4); number.add(4); number.add(0); number.add(0);	number.add(4);	number.add(3);	number.add(2);	number.add(1);	number.add(4); number.add(1); number.add(1);
		number.add(4); number.add(2); number.add(1); number.add(4); number.add(1);  number.add(1);	number.add(3); 	number.add(4); number.add(2); number.add(1); number.add(4);
		return number;
	}

	/**
	 * Process the orders.
	 * @throws NoClearanceException
	 */
	private void processOrders() throws NoClearanceException {
		IteratorConverter<WorkPost> converter = new IteratorConverter<>();
		Iterator<AssemblyLine> iter1 = vmc.getAssemblyLines(this.mechanic);
		DateTime beginDateTime = this.vmc.getSystemTime();
		while(iter1.hasNext()){
			looping = true;
			AssemblyLine assem = iter1.next();
			DateTime assemblyLineDateTime = assem.getAssemblyLineScheduler().getCurrentTime();
			DateTime result = assemblyLineDateTime.minus(beginDateTime.getMillis());

			while (looping == true && result.getMillis() < DAY_IN_MILIS){
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
			Iterator<AssemblyTask> iter2 = vmc.getPendingTasks(this.mechanic, wp1);
			while (iter2.hasNext()){
				AssemblyTask task = iter2.next();
				vmc.finishTask(task, 20);
				looping = true;
			}
		}
	}

	// orders (standard or singleTask
	// model -1 if random, otherwise (0 to 4). 0 for model A, ...
	// batch -1 if random, otherwise number of the options that must be the same for the number of orders
	/**
	 * Random generator for orders.
	 * @param orders
	 * @param model
	 * @return
	 */
	private boolean randomOrderGenerator(String orders, int model){

		VehicleModel vehicleModel;

		int randomNumber = this.rnd.nextInt(this.available_vehiclemodels.size());
		if (model == -1)
			vehicleModel = this.available_vehiclemodels.get(randomNumber);
		else
			vehicleModel = this.available_vehiclemodels.get(model);


		ArrayList <VehicleOption> available = vehicleModel.getPossibilities();
		airco.clear(); body.clear(); color.clear();engine.clear();gearbox.clear(); seats.clear();
		spoiler.clear();wheels.clear(); this.chosen.clear(); protection.clear(); certification.clear(); storage.clear();

		for(VehicleOption option: available){
			if (option.getCategory().equals(new Airco())){
				airco.add(option);
			}else if (option.getCategory().equals(new Body())){
				body.add(option);
			}else if (option.getCategory().equals(new Color())){
				color.add(option);
			}else if (option.getCategory().equals(new Engine())){
				engine.add(option);
			}else if (option.getCategory().equals(new Gearbox())){
				gearbox.add(option);
			}else if (option.getCategory().equals(new Seats())){
				seats.add(option);
			}else if (option.getCategory().equals(new Spoiler())){
				spoiler.add(option);
			}else if (option.getCategory().equals(new Wheels())){
				wheels.add(option);
			}else if (option.getCategory().equals(new Protection())){
				certification.add(option);
			}else if (option.getCategory().equals(new Storage())){
				protection.add(option);
			}else if (option.getCategory().equals(new Certification())){
				storage.add(option);
			}else{}
		}

		int count = 0;
		int number = 0;

		if (batchList.contains(count++)) number = 0; else if (this.body.size() != 0) number = rnd.nextInt(this.body.size());
		if (this.body.size() != 0) this.chosen.add(this.body.get(number));
		if (batchList.contains(count++)) number = 0; else if (this.color.size() != 0) number = rnd.nextInt(this.color.size());
		if (this.color.size() != 0) this.chosen.add(this.color.get(number));
		if (batchList.contains(count++)) number = 0; else if (this.engine.size() != 0) number = rnd.nextInt(this.engine.size());
		if (this.engine.size() != 0) this.chosen.add(this.engine.get(number));
		if (batchList.contains(count++)) number = 0; else if (this.gearbox.size() != 0) number = rnd.nextInt(this.gearbox.size());
		if (this.gearbox.size() != 0) this.chosen.add(this.gearbox.get(number));
		if (batchList.contains(count++)) number = 0; else if (this.seats.size() != 0) number = rnd.nextInt(this.seats.size());
		if (this.seats.size() != 0) this.chosen.add(this.seats.get(number));
		if (batchList.contains(count++)) number = 0; else if (this.airco.size() != 0) number = rnd.nextInt(this.airco.size());
		if (this.airco.size() != 0) this.chosen.add(this.airco.get(number));
		if (batchList.contains(count++)) number = 0; else if (this.spoiler.size() != 0) number = rnd.nextInt(this.spoiler.size());
		if (this.spoiler.size() != 0) this.chosen.add(this.spoiler.get(number));
		if (batchList.contains(count++)) number = 0; else if (this.wheels.size() != 0) number = rnd.nextInt(this.wheels.size());
		if (this.wheels.size() != 0) this.chosen.add(this.wheels.get(number));

		if (this.certification.size() != 0)this.chosen.add(this.certification.get(rnd.nextInt(this.certification.size())));
		if (this.protection.size() != 0)this.chosen.add(this.protection.get(rnd.nextInt(this.protection.size())));
		if (this.storage.size() != 0)this.chosen.add(this.storage.get(rnd.nextInt(this.storage.size())));


		if (orders.equals("standard")){
			try {
				StandardVehicleOrder order = new StandardVehicleOrder(this.garageholder, this.chosen, vehicleModel);
				this.controllerStandard.placeOrder(this.garageholder,order);
				this.orderCount ++;
				return true;
			} catch (IllegalArgumentException | NoClearanceException | UnsatisfiedRestrictionException e) {
				return false;
			}
		}else if (orders.equals("singleTask")){
			try {
				DateTime time = new DateTime(new DateTime().getYear(), new DateTime().getMonthOfYear(),new DateTime().getDayOfMonth(), 8, 0);
				SingleTaskOrder order = new SingleTaskOrder(this.customsManager, this.chosen, time.plusDays(1));
				this.controllerSingleTask.placeSingleTaskOrder(this.customsManager,order);
				this.orderCount ++;
				return true;
			} catch (IllegalArgumentException | NoClearanceException | UnsatisfiedRestrictionException e) {
				return false;
			}
		}else{
			return false;
		}
	}


}
