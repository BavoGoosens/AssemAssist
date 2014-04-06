package businessmodel.scheduler;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.Order;
import businessmodel.exceptions.IllegalSchedulingAlgorithmException;
import businessmodel.schedulingalgorithms.*;

public class Scheduler {
	
	private ArrayList<Shift> shifts; 
	
	private LinkedList<Order> orders;
	
	private SchedulingAlgorithm algo;

	public Scheduler(){
		
	}
	
	public void changeAlgorithm(String algoname){
		if (algoname == null )
			throw new NullPointerException("No scheduling algorithm supplied");
		if (algoname.equalsIgnoreCase("fifo") || algoname.equalsIgnoreCase("first in first out") )
			this.algo = new FIFO(this);
		if (algoname.equalsIgnoreCase("sb") || algoname.equalsIgnoreCase("specification batch"))
			this.algo = new SpecificationBatch(this);
		throw new IllegalSchedulingAlgorithmException("The scheduling algorithm was not recognised");
	}
}
