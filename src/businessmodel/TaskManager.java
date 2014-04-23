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

	public TaskManager(ArrayList<WorkPost> workposts){	
		this.generateRepsonsibleAssemblyTasks(workposts);
		this.generatePossibleSingleTasks();
	}

	private void generateRepsonsibleAssemblyTasks(ArrayList<WorkPost> workposts){
		ArrayList<AssemblyTask> tasks_workPost_1 = new ArrayList<AssemblyTask>();
		ArrayList<AssemblyTask> tasks_workPost_2 = new ArrayList<AssemblyTask>();
		ArrayList<AssemblyTask> tasks_workPost_3 = new ArrayList<AssemblyTask>();
		
		WorkPost workpost1 = workposts.get(0);
		WorkPost workpost2 = workposts.get(1);
		WorkPost workpost3 = workposts.get(2);
		
		tasks_workPost_1.add(new AssemblyTask("Assembly Car Body", "assemble blabla", new Body(),workpost1));
		tasks_workPost_1.add(new AssemblyTask("Paint Car","paint blabla", new Color(),workpost1));
		tasks_workPost_2.add(new AssemblyTask("Insert Engine", "insert engine blabla", new Engine(),workpost2));
		tasks_workPost_2.add(new AssemblyTask("Insert Gearbox","insert gearbox blabla", new Gearbox(),workpost2));
		tasks_workPost_3.add(new AssemblyTask("Install Seats", "insert seats blabla", new Seats(),workpost3));
		tasks_workPost_3.add(new AssemblyTask("Install Airco", "insert airco blabla", new Airco(),workpost3));
		tasks_workPost_3.add(new AssemblyTask("Mount Wheels", "insert mount wheels", new Wheels(),workpost3));
		tasks_workPost_3.add(new AssemblyTask("Install Spoiler","install spoiler", new Spoiler(),workpost3));
	}
	
	private void generatePossibleSingleTasks() {
		
	}

	
}
