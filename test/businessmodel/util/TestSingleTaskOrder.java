package businessmodel.util;

import java.util.ArrayList;

import org.joda.time.DateTime;

import businessmodel.category.Seats;
import businessmodel.category.VehicleOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.SingleTaskOrder;
import businessmodel.user.CustomShopManager;

public class TestSingleTaskOrder {

	private SingleTaskOrder order;
	private CustomShopManager holder;
	
	public TestSingleTaskOrder(CustomShopManager holder, String string, DateTime userenddate) {
		this.setHolder(holder);
		makeOrder(holder,string, userenddate);
	}

	private void setHolder(CustomShopManager holder) {
		this.holder = holder;
		
	}

	private void makeOrder(CustomShopManager holder, String name,  DateTime userenddate){
		ArrayList<VehicleOption> options = new ArrayList<VehicleOption>();
		VehicleOption option1 = new VehicleOption("Seats",new Seats());
		options.add(option1);
		try {
			this.order = new SingleTaskOrder(holder,options,userenddate);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoClearanceException e) {
			e.printStackTrace();
		} catch (UnsatisfiedRestrictionException e) {
			e.printStackTrace();
		}
	}

	public SingleTaskOrder getOrder(){
		return this.order;
	}
}