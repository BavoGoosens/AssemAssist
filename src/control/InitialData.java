package control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import businessmodel.category.*;
import businessmodel.util.IteratorConverter;
import org.joda.time.DateTime;

import businessmodel.VehicleManufacturingCompany;
import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.assemblyline.WorkPost;
import businessmodel.category.Protection;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.SingleTaskOrder;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.User;

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

	public void initialize(VehicleManufacturingCompany vmc) throws NoClearanceException{

		this.vmc = vmc;
		this.garageholder = vmc.login("wow", "");
		this.mechanic = vmc.login("woww", "");
		this.customsManager = vmc.login("wowwww", "");

		this.controllerStandard = new StandardOrderHandler(vmc);

		this.controllerSingleTask = new SingleTaskOrderHandler(vmc);
		this.iter = vmc.getVehicleModels(this.garageholder);
		this.available_vehiclemodels = new ArrayList<VehicleModel>();
		this.chosen = new ArrayList<VehicleOption>();

		while (iter.hasNext())
			this.available_vehiclemodels.add(this.iter.next());

		Boolean orders = false;

        ArrayList<Integer> numbers = this.generateOrders();
		for(int i=0; i < 50; i++){
			orders = this.randomOrderGenerator("standard",-1);
			if (!orders)
				this.randomOrderGenerator("standard", 0);
		}


		this.processOrders();
 

//		orders = false;
//
//		for(int i=0; i < 3; i++){
//			orders = this.randomOrderGenerator("singleTask",-1);
//			if (!orders)
//				this.randomOrderGenerator("singleTask", 0);
//	}

//		orders = false;
//
//		for(int i=0; i < 3; i++){
//			orders = this.randomOrderGenerator("standard",-1);
//			if (!orders)
//				this.randomOrderGenerator("standard", 0);
//		}
//
//		orders = false;
//
//		for(int i=0; i < 3; i++){
//			orders = this.randomOrderGenerator("standard",-1);
//			if (!orders)
//				this.randomOrderGenerator("standard", 0);
//		}


	}

    private ArrayList<Integer> generateOrders() {
        ArrayList<Integer> number = new ArrayList<Integer>();
        number.add(1);
        number.add(2);
        number.add(3);
        number.add(4);
        number.add(0);
        number.add(1);
        number.add(1);
        number.add(2);
        number.add(2);
        number.add(3);
        number.add(3);
        number.add(4);
        number.add(4);
        number.add(0);
        number.add(0);
        number.add(4);
        number.add(3);
        number.add(2);
        number.add(1);
        number.add(4);
        number.add(1);
        number.add(1);
        number.add(4);
        number.add(2);
        number.add(1);
        number.add(4);
        number.add(1);
        number.add(1);
        number.add(3);
        return number;
    }

    private boolean looping = true;
	// TODO
	private void processOrders() throws NoClearanceException {
        IteratorConverter<WorkPost> converter = new IteratorConverter<>();
		Iterator<AssemblyLine> iter1 = vmc.getAssemblyLines(this.mechanic);
		while(iter1.hasNext()){
            looping = true;
			AssemblyLine assem = iter1.next();
			while (looping == true ){
				CompleteWorkPost(assem, converter.convert(assem.getWorkPostsIterator()).size());
			}
			/*for(int i = 0; i < 40 ; i ++){
				for(WorkPost wp: assem.getWorkPostsIterator()){
					Iterator<AssemblyTask> iter3 = vmc.getPendingTasks(this.mechanic, wp);
					while (iter3.hasNext()){
						AssemblyTask task = iter3.next();
						vmc.finishTask(task, 20);
					}looping
				}
			}*/
		}

	}

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

		if (this.airco.size() != 0) this.chosen.add(this.airco.get(rnd.nextInt(this.airco.size())));
		if (this.body.size() != 0)this.chosen.add(this.body.get(rnd.nextInt(this.body.size())));
		if (this.color.size() != 0)this.chosen.add(this.color.get(rnd.nextInt(this.color.size())));
		if (this.engine.size() != 0)this.chosen.add(this.engine.get(rnd.nextInt(this.engine.size())));
		if (this.gearbox.size() != 0)this.chosen.add(this.gearbox.get(rnd.nextInt(this.gearbox.size())));
		if (this.seats.size() != 0)this.chosen.add(this.seats.get(rnd.nextInt(this.seats.size())));
		if (this.spoiler.size() != 0)this.chosen.add(this.spoiler.get(rnd.nextInt(this.spoiler.size())));
		if (this.wheels.size() != 0)this.chosen.add(this.wheels.get(rnd.nextInt(this.wheels.size())));
		if (this.certification.size() != 0)this.chosen.add(this.certification.get(rnd.nextInt(this.certification.size())));
		if (this.protection.size() != 0)this.chosen.add(this.protection.get(rnd.nextInt(this.protection.size())));
		if (this.storage.size() != 0)this.chosen.add(this.storage.get(rnd.nextInt(this.storage.size())));


		if (orders.equals("standard")){
			try {
				StandardVehicleOrder order = new StandardVehicleOrder(this.garageholder, this.chosen, vehicleModel);
				this.controllerStandard.placeOrder(this.garageholder,order);
				return true;
			} catch (IllegalArgumentException | NoClearanceException | UnsatisfiedRestrictionException e) {
				if (model == 0)
					System.out.println(e.toString());
			}
		}else if (orders.equals("singleTask")){
			try {
				DateTime time = new DateTime(new DateTime().getYear(), new DateTime().getMonthOfYear(),new DateTime().getDayOfMonth(), 8, 0);
				SingleTaskOrder order = new SingleTaskOrder(this.customsManager, this.chosen, time.plusDays(1));
				this.controllerSingleTask.placeSingleTaskOrder(this.customsManager,order);
				return true;
			} catch (IllegalArgumentException | NoClearanceException | UnsatisfiedRestrictionException e) {
				if (model == 0)
					System.out.println(e.toString());
			}
		}else{}

		return false;
	}

}
