package businessmodel;

import java.util.Iterator;

import businessmodel.exceptions.NoClearanceException;
import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.order.Order;
import businessmodel.user.User;

public interface Model {
	
	public Iterator<Order> getPendingOrders(User user) throws IllegalArgumentException, NoClearanceException;
	
	public Iterator<Order> getCompletedOrders(User user) throws IllegalArgumentException, NoClearanceException;
	
	public Iterator<WorkPost> getWorkPosts(User user);
	
	public Iterator<CarModel> getCarModels(User user);
	
	public Iterator<AssemblyTask> getAvailableTasks(User user);
	
	public Iterator<AssemblyTask> getPendingTasks(User user);
	
	public Iterator<AssemblyTask> getCompletedTasks(User user);
	
	public Iterator<String> getSchedulingAlgorithms(User user);

	public User login(String username, String password);
	
	public void register(User user);
	
	public AssemblyLine registerAssemblyLineObserver(Observer observer);

}
