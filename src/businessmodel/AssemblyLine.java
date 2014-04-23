package businessmodel;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.category.Airco;
import businessmodel.category.Body;
import businessmodel.category.Color;
import businessmodel.category.Engine;
import businessmodel.category.Gearbox;
import businessmodel.category.Seats;
import businessmodel.category.Spoiler;
import businessmodel.category.Wheels;
import businessmodel.order.Order;

/**
 * A class representing an assembly line. It currently holds 3 work post.
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
	 * Creates a new assembly line.
	 */
	public AssemblyLine() {
		this.generateWorkPosts();
	}

	/**
	 * Method to generate all the factories for the WorkPosts
	 */
	private void generateWorkPosts(){

		ArrayList<AssemblyTask> tasksWorkPost1 = new ArrayList<AssemblyTask>();
		ArrayList<AssemblyTask> tasksWorkPost2 = new ArrayList<AssemblyTask>();
		ArrayList<AssemblyTask> tasksWorkPost3 = new ArrayList<AssemblyTask>();
		WorkPost post1 = new WorkPost("Car Body Post", tasksWorkPost1);
		WorkPost post2 = new WorkPost("Drivetrain Post", tasksWorkPost2);
		WorkPost post3 = new WorkPost("Accesoires Post", tasksWorkPost3);		

		tasksWorkPost1.add(new AssemblyTask("Assembly Car Body", new Body(),post1));
		tasksWorkPost1.add(new AssemblyTask("Paint Car", new Color(),post1));
		tasksWorkPost2.add(new AssemblyTask("Insert Engine", new Engine(),post2));
		tasksWorkPost2.add(new AssemblyTask("Insert Gearbox", new Gearbox(),post2));
		tasksWorkPost3.add(new AssemblyTask("Install Seats", new Seats(),post3));
		tasksWorkPost3.add(new AssemblyTask("Install Airco", new Airco(),post3));
		tasksWorkPost3.add(new AssemblyTask("Mount Wheels", new Wheels(),post3));
		tasksWorkPost3.add(new AssemblyTask("Install Spoiler", new Spoiler(),post3));

		this.getWorkPosts().add(post1);
		this.getWorkPosts().add(post2);
		this.getWorkPosts().add(post3);
	}

	/**
	 * Checks whether the assembly line can move forward.
	 * 
	 * @return True if the assembly line can move forward.
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
	 * Advances the assembly line and adds a new order to the assembly line.
	 * 
	 * @param 	neworder
	 * 			The new order that needs to be added to the assembly line, when moved forward.	
	 * @throws	IllegalStateException
	 * 			| If the assembly line cannot be advanced.
	 * 			| !this.canAdvance()
	 */
	public void advance(Order neworder) throws IllegalStateException {
		if (!this.canAdvance())
			throw new IllegalStateException("Cannot advance assembly line!");
		Order temp = neworder;
		for(WorkPost wp: this.getWorkPosts()){
			temp = wp.switchOrders(temp);
		}
		if(temp != null)
			temp.setCompleted();
	}

	/**
	 * Returns all the orders that are on the assembly line.
	 * 
	 * @return  A list with all the orders that are currently on the assembly line.
	 */
	protected LinkedList<Order> getWorkPostOrders(){
		LinkedList<Order> orders = new LinkedList<Order>();
		for(WorkPost wp: this.getWorkPosts())
			if (wp.getOrder() != null)
				orders.add(wp.getOrder());
		return orders;
	}


	/**
	 * Returns the list of work posts at the assembly line.
	 * 
	 * @return	The list of work posts at the assembly line.
	 */
	public ArrayList<WorkPost> getWorkPosts() {
		return this.workposts;
	}

	/**
	 * Returns the number of work posts at the assembly line.
	 * 
	 * @return	The number of work posts at the assembly line.
	 */
	public int getNumberOfWorkPosts(){
		return this.getWorkPosts().size();
	}
}