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


public abstract class WorkPostFactory {

	public WorkPost createWorkPost(){
		
		ArrayList<AssemblyTask> tempTasks = new ArrayList<AssemblyTask>();
		
		tempTasks.add(this.createBodyTask());
		tempTasks.add(this.createColorTask());
		tempTasks.add(this.createEngineTask());
		tempTasks.add(this.createGearboxTask());
		tempTasks.add(this.createSeatsTask());
		tempTasks.add(this.createAircoTask());
		tempTasks.add(this.createWheelsTask());
		tempTasks.add(this.createSpoilerTask());
		
		ArrayList<AssemblyTask> tasks = new ArrayList<AssemblyTask>();
		for (AssemblyTask assemblyTask: tempTasks)
			if (assemblyTask != null)
				tasks.add(assemblyTask);
		
		return new WorkPost(this.getName(), tasks);
		
	}

	protected AssemblyTask createBodyTask(){
		return new AssemblyTask("Install Car Body", new Body());
	}
	
	protected AssemblyTask createColorTask(){
		return new AssemblyTask("Paint Car", new Color());
	}
	
	protected AssemblyTask createEngineTask(){
		return new AssemblyTask("Insert Engine", new Engine());
	}
	
	protected AssemblyTask createGearboxTask(){
		return new AssemblyTask("Insert Gearbox", new Gearbox());
	}
	
	protected AssemblyTask createSeatsTask(){
		return new AssemblyTask("Install Seats", new Seats());
	}
	
	protected AssemblyTask createAircoTask(){
		return new AssemblyTask("Install Airco", new Airco());
	}
	
	protected AssemblyTask createWheelsTask(){
		return new AssemblyTask("Mount Wheels", new Wheels());
	}
	
	protected AssemblyTask createSpoilerTask(){
		return new AssemblyTask("Install Spoiler", new Spoiler());
	}
	
	protected abstract String getName();
	
}
