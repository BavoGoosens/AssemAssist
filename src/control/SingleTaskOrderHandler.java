package control;

import businessmodel.Model;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.order.SingleTaskOrder;
import businessmodel.user.User;

/**
 * The handler for everything related to SingleTaskOrders.
 *
 * @author Team 10
 */
public class SingleTaskOrderHandler implements SingleTaskOrderController {


    private VehicleManufacturingCompany cmc;

    /**
     * Constructor for this handler.
     *
     * @param model The model this controller needs to interact with.
     */
    public SingleTaskOrderHandler(Model model) {
        this.cmc = (VehicleManufacturingCompany) model;
    }

    @Override
    public void placeSingleTaskOrder(User user, SingleTaskOrder order) {
        this.cmc.placeOrder(order);
    }

}
