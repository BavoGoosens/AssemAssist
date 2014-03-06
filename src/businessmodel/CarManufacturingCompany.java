package businessmodel;

public class CarManufacturingCompany {
	
	private UserManagement um;
	
	private Inventory inv;
	
	private OrderManagement om;
	
	private ProductionScheduler ps;
	
	public boolean login(String username, String password) {
		return this.um.authenticate(username, password);		
	}

	public User getUser(String username) {
		return this.um.getUser(username);
	}

	public ArrayList<Order> getCompletedOrders(User user) {
		return om.getCompletedOrders(user);
	}

	public ArrayList<Order> getPendingOrders(User user) {
		// TODO Auto-generated method stub
		return om.getPendingOrders(user);
	}
	
	

}
