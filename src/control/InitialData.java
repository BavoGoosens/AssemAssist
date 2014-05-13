package control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import businessmodel.VehicleManufacturingCompany;
import businessmodel.VehicleModel;
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
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.User;

public class InitialData {

	private int nbOrders;

	public InitialData(int nbOrders){
		this.nbOrders = nbOrders;
	}

	public void initialize(VehicleManufacturingCompany vmc){

		User user = vmc.login("wow", "");
		StandardOrderController controller = new StandardOrderHandler(vmc);
		Iterator<VehicleModel> iter = vmc.getVehicleModels(user);
		ArrayList<VehicleModel> available_vehiclemodels = new ArrayList<VehicleModel>();
		Random rnd = new Random();
		ArrayList <VehicleOption> chosen = new ArrayList<VehicleOption>();
		while (iter.hasNext())
			available_vehiclemodels.add(iter.next());
		
		for(int i=0; i < this.nbOrders; i++){
			
			int randomNumber = rnd.nextInt(available_vehiclemodels.size());
			VehicleModel vehicleModel = available_vehiclemodels.get(randomNumber);
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
			
			for(int j=0; j < airco.size()-1; j++) chosen.add(airco.get(rnd.nextInt(airco.size())));
			for(int j=0; j < body.size()-1; j++) chosen.add(body.get(rnd.nextInt(body.size())));
			for(int j=0; j < color.size()-1; j++) chosen.add(color.get(rnd.nextInt(color.size())));
			for(int j=0; j < engine.size()-1; j++) chosen.add(engine.get(rnd.nextInt(engine.size())));
			for(int j=0; j < gearbox.size()-1; j++) chosen.add(gearbox.get(rnd.nextInt(gearbox.size())));
			for(int j=0; j < seats.size()-1; j++) chosen.add(seats.get(rnd.nextInt(seats.size())));
			for(int j=0; j < spoiler.size()-1; j++) chosen.add(spoiler.get(rnd.nextInt(spoiler.size())));
			for(int j=0; j < wheels.size()-1; j++) chosen.add(wheels.get(rnd.nextInt(wheels.size())));
		 
			
			StandardVehicleOrder order;
			try {
				order = new StandardVehicleOrder(user, chosen, vehicleModel);
				controller.placeOrder(order);
			} catch (IllegalArgumentException | NoClearanceException | UnsatisfiedRestrictionException e) {
				e.printStackTrace();
			}
			
		}


	}

}
