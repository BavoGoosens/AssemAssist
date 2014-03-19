package businessmodel;

import java.util.ArrayList;
import java.util.LinkedList;

import component.Airco;
import component.Body;
import component.Color;
import component.Engine;
import component.Gearbox;
import component.Seats;
import component.Wheels;

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
	public AssemblyLine() {
		this.generateWorkPosts();
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

	/**
	 * This method advances the Assembly Line. 
	 * It adds the supplied order to the first work post and moves the rest forward. 
	 * If the last WorkPost was working on an Order. That order is finished and is returned.
	 * 
	 * @param 	neworder 
	 * 		  	The Order that is added at the front of the line.
	 * 
	 * @return 	Order
	 * 		   	A finished order if the last WorkPost was working on an Order. 
	 */
	public Order advance(Order neworder) {
		Order temp1 = this.getWorkPosts().get(0).getOrder();
		Order temp2 = this.getWorkPosts().get(1).getOrder();
		Order finished = this.getWorkPosts().get(2).getOrder();
		
		this.getWorkPosts().get(0).setNewOrder(neworder);
		this.getWorkPosts().get(1).setNewOrder(temp1);
		this.getWorkPosts().get(2).setNewOrder(temp2);

		return finished;
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
	 * A method to generate all the responsible assembly tasks of a work post.
	 * 
	 * @param   actions
	 *          the actions of all the work posts.
	 */
	private void generateWorkPosts() {
		Action action1 = new Action("Paint car");
		Action action2 = new Action("Assembly Car Body");
		Action action3 = new Action("Insert engine");
		Action action4 = new Action("Insert gearbox");
		Action action5 = new Action("Install seats");
		Action action6 = new Action("Install Airco");
		Action action7 = new Action("Mount Wheels");
		action1.addComponent(new Color("blue",50));
		action2.addComponent(new Body("sedan", 50));
		action3.addComponent(new Engine("standard 2l 4 cilinders", 50));
		action4.addComponent(new Gearbox("6 speed manual", 1000));
		action5.addComponent(new Seats("leather black", 1000));
		action6.addComponent(new Airco("manual climate control",1000));
		action7.addComponent(new Wheels("comfort",1000));

		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(action1);
		actions.add(action2);
		actions.add(action3);
		actions.add(action4);
		actions.add(action5);
		actions.add(action6);
		actions.add(action7);

		ArrayList<AssemblyTask> tasks1 = new ArrayList<AssemblyTask>();
		ArrayList<AssemblyTask> tasks2 = new ArrayList<AssemblyTask>();
		ArrayList<AssemblyTask> tasks3 = new ArrayList<AssemblyTask>();
		
		ArrayList<Action> actions1 = new ArrayList<Action>();
		actions1.add(actions.get(0));	
		ArrayList<Action> actions2 = new ArrayList<Action>();
		actions2.add(actions.get(1));
		ArrayList<Action> actions3 = new ArrayList<Action>();
		actions3.add(actions.get(2));
		ArrayList<Action> actions4 = new ArrayList<Action>();
		actions4.add(actions.get(3));
		ArrayList<Action> actions5 = new ArrayList<Action>();
		actions5.add(actions.get(4));
		ArrayList<Action> actions6 = new ArrayList<Action>();
		actions6.add(actions.get(5));
		ArrayList<Action> actions7 = new ArrayList<Action>();
		actions7.add(actions.get(6));
		
		AssemblyTask assem1 = new AssemblyTask("Paint car",actions1);
		AssemblyTask assem2 = new AssemblyTask("Assembly Car Body",actions2);
		AssemblyTask assem3 = new AssemblyTask("Insert engine",actions3);
		AssemblyTask assem4 = new AssemblyTask("Insert gearbox",actions4);
		AssemblyTask assem5 = new AssemblyTask("Install seats",actions5);
		AssemblyTask assem6 = new AssemblyTask("Install Airco",actions6);
		AssemblyTask assem7 = new AssemblyTask("Mount Wheels",actions7);
	
		tasks1.add(assem1);
		tasks1.add(assem2);
		tasks2.add(assem3);
		tasks2.add(assem4);
		tasks3.add(assem5);
		tasks3.add(assem6);
		tasks3.add(assem7);
		
		WorkPost wp1 = new WorkPost("car body post",tasks1);
		WorkPost wp2 = new WorkPost("drive train post",tasks2);
		WorkPost wp3 = new WorkPost("accessories post",tasks3);
	
		this.getWorkPosts().add(wp1);
		this.getWorkPosts().add(wp2);
		this.getWorkPosts().add(wp3);
	}

}
