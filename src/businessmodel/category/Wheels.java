package businessmodel.category;

import java.util.UUID;

/**
 * Class representing a wheels category.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class Wheels extends CarOptionCategory {
	
	/**
	 * The unique key of the category.
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
