package BusinessModel;

import java.util.ArrayList;

public class WorkPost {
	
	private String name;
	private ArrayList<AssemblyTask> tasks;
	
	public WorkPost(String name) {
		this.setName(name);
		this.tasks = new ArrayList<AssemblyTask>();
	}
	
	public WorkPost(String name, ArrayList<AssemblyTask> tasks) {
		this(name);
		this.setTasks(tasks);
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<AssemblyTask> getTasks() {
		return this.tasks;
	}
	
	private void setName(String name) throws IllegalArgumentException {
		if (name == null) throw new IllegalArgumentException();
		this.name = name;
	}
	
	private void setTasks(ArrayList<AssemblyTask> tasks) throws IllegalArgumentException {
		if (tasks == null) throw new IllegalArgumentException();
		this.tasks = tasks;
	}
	
	public void addTask(AssemblyTask task) {
		this.getTasks().add(task);
	}
	
	public void removeTask(AssemblyTask task) {
		this.getTasks().remove(task);
	}

}
