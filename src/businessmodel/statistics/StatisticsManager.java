package businessmodel.statistics;

import businessmodel.OrderManager;

public class StatisticsManager {

	private CarStatistics carStatistics;
	
	private OrderStatistics orderStatistics;
	
	public StatisticsManager(OrderManager ordermanager){
		this.carStatistics = new CarStatistics(ordermanager.getScheduler());
		this.orderStatistics = new OrderStatistics(ordermanager);
	}
	
	public CarStatistics getCarStatistics() {
		return carStatistics;
	}

	public OrderStatistics getOrderStatistics() {
		return orderStatistics;
	}

}
