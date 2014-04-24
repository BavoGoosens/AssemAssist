package businessmodel;

import java.util.ArrayList;

import businessmodel.category.Airco;
import businessmodel.category.Body;
import businessmodel.category.Color;
import businessmodel.category.Engine;
import businessmodel.category.Gearbox;
import businessmodel.category.Seats;
import businessmodel.category.Spoiler;
import businessmodel.category.Wheels;

public class TaskManager {

	private ArrayList<AssemblyTask> singleTaskOrders;

	public TaskManager(ArrayList<WorkPost> workposts){	
		this.generateRepsonsibleAssemblyTasks(workposts);
		this.generatePossibleSingleTasks();
	}

	@SuppressWarnings("unchecked")
	protected ArrayList<AssemblyTask> getSingleTaskOrders() {
		return (ArrayList<AssemblyTask>) singleTaskOrders.clone();
	}
	
	private void generateRepsonsibleAssemblyTasks(ArrayList<WorkPost> workposts){
		ArrayList<AssemblyTask> tasks_workPost_1 = new ArrayList<AssemblyTask>();
		ArrayList<AssemblyTask> tasks_workPost_2 = new ArrayList<AssemblyTask>();
		ArrayList<AssemblyTask> tasks_workPost_3 = new ArrayList<AssemblyTask>();
		WorkPost workpost1 = workposts.get(0);
		WorkPost workpost2 = workposts.get(1);
		WorkPost workpost3 = workposts.get(2);
		tasks_workPost_1.add(new AssemblyTask("Assembly Car Body", "Place the body on the car", new Body(),workpost1));
		tasks_workPost_1.add(new AssemblyTask("Paint Car","Paint the car", new Color(),workpost1));
		tasks_workPost_2.add(new AssemblyTask("Insert Engine", "insert engine into the car", new Engine(),workpost2));
		tasks_workPost_2.add(new AssemblyTask("Insert Gearbox","insert gearbox into the car", new Gearbox(),workpost2));
		tasks_workPost_3.add(new AssemblyTask("Install Seats", "insert seats into the car", new Seats(),workpost3));
		tasks_workPost_3.add(new AssemblyTask("Install Airco", "insert airco into the car", new Airco(),workpost3));
		tasks_workPost_3.add(new AssemblyTask("Mount Wheels", "mount wheels on the car", new Wheels(),workpost3));
		tasks_workPost_3.add(new AssemblyTask("Install Spoiler","install spoiler on the car", new Spoiler(),workpost3));
		workpost1.setResponsibletasks(tasks_workPost_1);
		workpost2.setResponsibletasks(tasks_workPost_2);
		workpost3.setResponsibletasks(tasks_workPost_3);
	}

	private void generatePossibleSingleTasks() {
		this.singleTaskOrders = new ArrayList<AssemblyTask>();
		singleTaskOrders.add(new AssemblyTask("Paint Car","paint blabla", new Color()));
		singleTaskOrders.add(new AssemblyTask("Install Seats", "insert seats blabla", new Seats()));
	}
}