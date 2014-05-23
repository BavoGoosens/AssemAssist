package control;

import businessmodel.Model;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.category.VehicleOption;
import businessmodel.user.User;

import java.util.ArrayList;

/**
 * The handler for everything related to scheduling.
 *
 * @author Team 10
 */
public class SchedulingHandler implements SchedulingController {

    private VehicleManufacturingCompany cmc;

    /**
     * Constructor for the Scheduling handler.
     *
     * @param model The model this controller needs to interact with.
     */
    public SchedulingHandler(Model model) {
        this.cmc = (VehicleManufacturingCompany) model;
    }

    @Override
    public void selectAlgorithm(User user, String algorithm, ArrayList<VehicleOption> options) {
        this.cmc.changeSystemWideAlgorithm(algorithm, options);
    }

}
