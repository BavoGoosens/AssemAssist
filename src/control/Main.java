package control;

import java.util.ArrayList;

import org.joda.time.DateTime;

import businessmodel.*;
import businessmodel.user.GarageHolder;
import businessmodel.user.Manager;
import businessmodel.user.Mechanic;
import businessmodel.user.User;



/**
 * @author Team 10
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CarManufacturingCompany cmc = new CarManufacturingCompany();
		LoginHandler start = new LoginHandler(cmc);
	}
}
