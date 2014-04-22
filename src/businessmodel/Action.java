package businessmodel;

/**
 * A class representing an action.
 * 
 * @author team 10
 *
 */
public class Action {

	/**
	 * The description of this action.
	 */
	private String description;

	/**
	 * This method constructs a new action with a given description.
	 * 
	 * @param 	description
	 * 			The description of the new action.
	 */
	public Action(String descr) throws IllegalArgumentException {
		this.setDescription(descr);
	}

	/**
	 * This method returns the description of the action.
	 * 
	 * @return	the description of this action.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * This method sets the description of the action.
	 * 
	 * @param	description
	 * 			The description of the action
	 */
	private void setDescription(String description) throws IllegalArgumentException {
		if (description == null) throw new IllegalArgumentException("Bad description!");
		this.description = description;
	}
}
