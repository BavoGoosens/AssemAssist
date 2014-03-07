package businessmodel;

import java.util.ArrayList;

public class AssemblyLine {
	
	
	private final WorkPost carBodyPost;
	
	private final WorkPost driveTrainPost;
	
	private final WorkPost accessoiresPost;
	
	/**
	 * This method constructs a new assembly line with fixed work posts.
	 */
	public AssemblyLine() {
		this.carBodyPost = new WorkPost("car body post");
		this.driveTrainPost = new WorkPost("drive train post");
		this.accessoiresPost = new WorkPost("accessoires post");
	}
	
	/**
	 * This method returns the car body work post.
	 * @return The car body work post of the assembly line.
	 */
	public WorkPost getCarBodyPost() {
		return this.carBodyPost;
	}
	
	/**
	 * This method returns the drive train work post.
	 * @return The car body work post of the assembly line.
	 */
	public WorkPost getDriveTrainPost() {
		return this.driveTrainPost;
	}
	
	/**
	 * This method returns the accessoires work post.
	 * @return The car body work post of the assembly line.
	 */
	public WorkPost getAccessoiresPost() {
		return this.accessoiresPost;
	}
	
	/**
	 * This method returns all the current tasks at the assembly line.
	 * @return	For every work post at the assembly line, return the current task.
	 */
	public ArrayList<AssemblyTask> getAllCurrentTasks() {
		ArrayList<AssemblyTask> currentTasks = new ArrayList<AssemblyTask>();
		currentTasks.add(this.getCarBodyPost().getCurrentTask());
		currentTasks.add(this.getDriveTrainPost().getCurrentTask());
		currentTasks.add(this.getAccessoiresPost().getCurrentTask());
		return currentTasks;
	}
	
	/**
	 * This method checks whether all the current tasks at the assembly line are completed.
	 * @return	True if all the current tasks at the assembly line are completed.
	 */
	public boolean allCurrentTasksAreCompleted() {
		for (AssemblyTask task: this.getAllCurrentTasks()) {
			if (!task.isCompleted()) return false;
		}
		return true;
	}
	
	/**
	 * This method checks whether the assembly line can move forward.
	 * @return	True if the assembly line can move forward.
	 */
	public boolean canMoveForward() {
		return this.allCurrentTasksAreCompleted();
	}

}
