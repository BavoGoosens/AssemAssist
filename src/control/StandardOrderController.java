package control;

import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.User;

/**
 * This controller is used for everything related to StandardOrder.
 *
 * @author Team 10
 */
public interface StandardOrderController {

    /**
     * This method places the StandardOrder.
     *
     * @param user  The user who wants to place the order.
     * @param order The order the caller wants to place.
     */
    public void placeOrder(User user, StandardVehicleOrder order);

}
