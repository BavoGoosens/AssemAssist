package businessmodel;

import java.util.ArrayList;

public class AssemblyLine {
	
	
	private final WorkPost carBodyPost;
	
	private final WorkPost driveTrainPost;
	
	private final WorkPost accessoiresPost;
	
	/**
	 * This method constructs a new assembly line with an empty list of work posts.
	 */
	public AssemblyLine() {
		this.carBodyPost = new WorkPost("car body post");
		this.driveTrainPost = new WorkPost("drive train post");
		this.accessoiresPost = new WorkPost("accessoires post");
	}
	
	public WorkPost getCarBodyPost() {
		return this.carBodyPost;
	}
	
	public WorkPost getDriveTrainPost() {
		return this.driveTrainPost;
	}
	
	public WorkPost getAccessoiresPost() {
		return this.accessoiresPost;
	}
	
	public ArrayList<AssemblyTask> getAllCurrentTasks() {
		ArrayList<AssemblyTask> currentTasks = new ArrayList<AssemblyTask>();
		currentTasks.add(this.getCarBodyPost().getCurrentTask());
		currentTasks.add(this.getDriveTrainPost().getCurrentTask());
		currentTasks.add(this.getAccessoiresPost().getCurrentTask());
		return currentTasks;
	}
	
	public boolean allCurrentTasksAreCompleted() {
		for (AssemblyTask task: this.getAllCurrentTasks()) {
			if (!task.isCompleted()) return false;
		}
		return true;
	}
	
	public boolean canMoveForward() {
		return this.allCurrentTasksAreCompleted();
	}

}
