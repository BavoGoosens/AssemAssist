package businessmodel;

import java.util.ArrayList;
import java.util.LinkedList;

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

	/**
	 * A constructor for the class AssemblyLine.
	 */
	public AssemblyLine() throws IllegalArgumentException {
		this.generateWorkPosts();
	}
	
	// TODO aanpassen voor als er meerdere WorkPosts mogelijk zijn.
	private void generateWorkPosts(){
		WorkPost post1 = new WorkPost("Car");
		WorkPost post2 = new WorkPost("");
		WorkPost post3 = new WorkPost("");
		this.getWorkPosts().add(post1);
		this.getWorkPosts().add(post2);
		this.getWorkPosts().add(post3);
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
	
	public int getNumberOfWokrkPosts(){
		return this.getWorkPosts().size();
	}
}