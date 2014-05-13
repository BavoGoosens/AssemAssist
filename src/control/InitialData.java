package control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.joda.time.DateTime;

import businessmodel.VehicleManufacturingCompany;
import businessmodel.category.VehicleModel;
import businessmodel.category.Airco;
import businessmodel.category.Body;
import businessmodel.category.Color;
import businessmodel.category.Engine;
import businessmodel.category.Gearbox;
import businessmodel.category.Seats;
import businessmodel.category.Spoiler;
import businessmodel.category.VehicleOption;
import businessmodel.category.Wheels;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.SingleTaskOrder;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.User;

public class InitialData {

	private Random rnd = new Random();
	private User user;
	private User user2;
	private StandardOrderHandler controllerStandard;
	private SingleTaskOrderHandler controllerSingleTask;
	private Iterator<VehicleModel> iter;
	private ArrayList<VehicleModel> available_vehiclemodels;
	private ArrayList<VehicleOption> chosen;

	public InitialData(){}

	public void initialize(VehicleManufacturingCompany vmc){

		user = vmc.login("wow", "");
		controllerStandard = new StandardOrderHandler(vmc);
		user2 = vmc.login("wow", "");
		controllerSingleTask = new SingleTaskOrderHandler(vmc);
		iter = vmc.getVehicleModels(user);
		available_vehiclemodels = new ArrayList<VehicleModel>();
		chosen = new ArrayList<VehicleOption>();

		while (iter.hasNext())
			available_vehiclemodels.add(iter.next());

		this.randomOrderGenerator(10, "standard");
		this.processOrders();
		this.randomOrderGenerator(3, "singleTask");
		this.randomOrderGenerator(3, "standard");
		this.randomOrderGenerator(3, "standard");
	
	}

	private void processOrders() {
		// TODO Auto-generated method stub
		
	}

	private void randomOrderGenerator(int nbOrders, String orders){

		for(int i=0; i < nbOrders; i++){

			int randomNumber = this.rnd.nextInt(this.available_vehiclemodels.size());
			VehicleModel vehicleModel = this.available_vehiclemodels.get(randomNumber);
			ArrayList <VehicleOption> available = vehicleModel.getPossibilities();
			ArrayList<VehicleOption> airco = new ArrayList<VehicleOption>();
			ArrayList<VehicleOption> body = new ArrayList<VehicleOption>();
			ArrayList<VehicleOption> color= new ArrayList<VehicleOption>();
			ArrayList<VehicleOption> engine = new ArrayList<VehicleOption>();
			ArrayList<VehicleOption> gearbox = new ArrayList<VehicleOption>();
			ArrayList<VehicleOption> seats = new ArrayList<VehicleOption>();
			ArrayList<VehicleOption> spoiler = new ArrayList<VehicleOption>();
			ArrayList<VehicleOption> wheels = new ArrayList<VehicleOption>();

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
				}else{}
			}

			for(int j=0; j < airco.size()-1; j++) this.chosen.add(airco.get(rnd.nextInt(airco.size())));
			for(int j=0; j < body.size()-1; j++) this.chosen.add(body.get(rnd.nextInt(body.size())));
			for(int j=0; j < color.size()-1; j++) this.chosen.add(color.get(rnd.nextInt(color.size())));
			for(int j=0; j < engine.size()-1; j++) this.chosen.add(engine.get(rnd.nextInt(engine.size())));
			for(int j=0; j < gearbox.size()-1; j++) this.chosen.add(gearbox.get(rnd.nextInt(gearbox.size())));
			for(int j=0; j < seats.size()-1; j++) this.chosen.add(seats.get(rnd.nextInt(seats.size())));
			for(int j=0; j < spoiler.size()-1; j++) this.chosen.add(spoiler.get(rnd.nextInt(spoiler.size())));
			for(int j=0; j < wheels.size()-1; j++) this.chosen.add(wheels.get(rnd.nextInt(wheels.size())));

			
			if (orders.equals("standard")){
				try {
					StandardVehicleOrder order = new StandardVehicleOrder(this.user, this.chosen, vehicleModel);
					this.controllerStandard.placeOrder(order);
				} catch (IllegalArgumentException | NoClearanceException | UnsatisfiedRestrictionException e) {
					e.printStackTrace();
				}
			}else if (orders.equals("singleTask")){
				try {
					DateTime time = new DateTime(new DateTime().getYear(), new DateTime().getMonthOfYear(),new DateTime().getDayOfMonth(), 8, 0);
					SingleTaskOrder order = new SingleTaskOrder(this.user2, this.chosen, time.plusDays(1));
					this.controllerSingleTask.placeSingleTaskOrder(order);
				} catch (IllegalArgumentException | NoClearanceException | UnsatisfiedRestrictionException e) {
					e.printStackTrace();
				}
			}else{}
	
		}
	}

}
