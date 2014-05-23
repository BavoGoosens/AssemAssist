package control;


import businessmodel.order.SingleTaskOrder;
import businessmodel.user.User;

/**
 * The controller for a SingleTaskOrder.
 *
 * @author Team 10
 */
public interface SingleTaskOrderController {

    /**
     * This method is used to place a SingleTaskOrder.
     *
     * @param user  The user who wants to place the order
     * @param order The order the caller wants to place.
     */
    public void placeSingleTaskOrder(User user, SingleTaskOrder order);

}
