package businessmodel.category;

import java.util.UUID;

/**
 * Class representing a wheels category.
 * 
 * @author team 10
 *
 */
public class Wheels extends CarOptionCategory {
	
	/**
	 * Indicates the unique key of the category.
	 */
	private final static UUID key = UUID.randomUUID();
	
	/**
	 * Creates a new wheels category.
	 */
	public Wheels() {
		super();
	}

	/**
	 * Returns the unique key of the category.
	 */
	@Override
	public UUID getKey() {
		return Wheels.key;
	}


}
