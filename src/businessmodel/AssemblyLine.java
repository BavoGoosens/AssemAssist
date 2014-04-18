package businessmodel;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.category.Airco;
import businessmodel.category.Body;
import businessmodel.category.CarModelFactory;
import businessmodel.category.Color;
import businessmodel.category.Engine;
import businessmodel.category.Gearbox;
import businessmodel.category.ModelAFactory;
import businessmodel.category.ModelBFactory;
import businessmodel.category.ModelCFactory;
import businessmodel.category.Seats;
import businessmodel.category.Spoiler;
import businessmodel.category.Wheels;
import businessmodel.order.Order;

/**
 * A class that represents an assembly line. It currently holds 3 work post.
 * 
 * @author 	SWOP team 10 2014
 *
 */
public class AssemblyLine {

	/**
	 * List of work posts at the assembly line.
	 */
	private ArrayList<WorkPost> workposts = new ArrayList<WorkPost>();
	private ArrayList<WorkPostFactory> factories = new ArrayList<WorkPostFactory>();

	/**
	 * A constructor for the class AssemblyLine.
	 */
	public AssemblyLine() throws IllegalArgumentException {
		this.generateWorkPosts();
	}
	
	/**
	 * Method to generate all the factories for de WorkPosts
	 */
	private void generateWorkPosts(){
		
		this.getFactories().add(new CarBodyWorkPostFactory());
		this.getFactories().add(new DrivetrainWorkPostFactory());
		this.getFactories().add(new AccesoiresWorkPostFactory());
		createAllWorkPosts();
		
	}
	
	/**
	 * Method to create all WorkPosts
	 */
	private void createAllWorkPosts() {
		for (WorkPostFactory workPostFactory: this.getFactories()) {
			this.getWorkPosts().add(workPostFactory.createWorkPost());
		}
	}
	
	

	/**
	 * This method checks whether the assembly line can move forward.
	 * 
	 * @return boolean
	 * 		   true is the assembly line can move forward. false otherwise.
	 */
	public boolean canAdvance() {
		for(WorkPost wp : this.getWorkPosts()){
			boolean ready = wp.isCompleted();
			if (ready == false)
				return false;
		}
		return true;
	}		

	/**
	 * 
	 * @param neworder
	 * @return
	 * @throws IllegalStateException
	 */
	public Order advance(Order neworder) throws IllegalStateException {
		if (!this.canAdvance())
			throw new IllegalStateException("Cannot advance assembly line!");
		Order temp = neworder;
		for(WorkPost wp: this.getWorkPosts()){
			temp = wp.switchOrders(temp);
		}
		return temp;
	}
	
	/**
	 * A method that returns all the orders that are on the assembly line.
	 * 
	 * @return  LinkedList<Order>
	 * 		    A list with all the orders that are on the assembly line.
	 */
	protected LinkedList<Order> getWorkPostOrders(){
		LinkedList<Order> orders = new LinkedList<Order>();
		for(WorkPost wp: this.getWorkPosts())
			if (wp.getOrder() != null)
				orders.add(wp.getOrder());
		return orders;
	}
	
	/**
	 * 
	 * @return
	 */
	private ArrayList<WorkPostFactory> getFactories() {
		return this.factories ;
	}

	/**
	 * This method returns the list of work posts at the assembly line.
	 * 
	 * @return	ArrayList<WorkPost>
	 * 			this.workposts
	 */
	public ArrayList<WorkPost> getWorkPosts() {
		return this.workposts;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumberOfWorkPosts(){
		return this.getWorkPosts().size();
	}
}