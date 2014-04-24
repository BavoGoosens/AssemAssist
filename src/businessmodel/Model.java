package businessmodel;

import java.util.Iterator;

import org.joda.time.DateTime;

import businessmodel.category.CarOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.observer.Observer;
import businessmodel.order.Order;
import businessmodel.statistics.CarStatistics;
import businessmodel.statistics.OrderStatistics;
import businessmodel.user.User;

public interface Model {
	
	public Iterator<Order> getPendingOrders(User user) throws IllegalArgumentException, NoClearanceException;
	
	public Iterator<Order> getCompletedOrders(User user) throws IllegalArgumentException, NoClearanceException;
	
	public Iterator<WorkPost> getWorkPosts(User user);
	
	public Iterator<CarModel> getCarModels(User user);
	
	public Iterator<AssemblyTask> getAvailableTasks(User user);
	
	public Iterator<String> getSchedulingAlgorithms(User user);

	public User login(String username, String password);
	
	public void register(User user);
	
	public AssemblyLine registerAssemblyLineObserver(Observer observer);

	public CarStatistics getCarStatistics();

	public OrderStatistics getOrderStatistics();

	public Iterator<WorkPost> getWorkPosts();

	public Iterator<AssemblyTask> getPendingTasks(WorkPost wp);

	public Iterator<AssemblyTask> getFinishedTasks(WorkPost wp);
	
	public Iterator<CarOption> getUnscheduledCarOptions(int num);

	public String getCurrentAlgo();

	public DateTime getSystemTime();

}
