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
		
		ArrayList<AssemblyTask> tasks = new ArrayList<AssemblyTask>();
		
		tasks.add(this.createBodyTask());
		tasks.add(this.createColorTask());
		tasks.add(this.createEngineTask());
		tasks.add(this.createGearboxTask());
		tasks.add(this.createSeatsTask());
		tasks.add(this.createAircoTask());
		tasks.add(this.createWheelsTask());
		tasks.add(this.createSpoilerTask());
		
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
