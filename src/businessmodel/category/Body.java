package businessmodel.category;

import java.util.UUID;

/**
 * Class representing a body category.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class Body extends CarOptionCategory {
	
	/**
	 * The unique key of the category.
	 */
	private final static UUID key = UUID.randomUUID();
	
	/**
	 * Creates a new body category.
	 */
	public Body() {
		super();
	}
	
	/**
	 * Returns the unique key of the category.
	 */
	@Override
	public UUID getKey() {
		return Body.key;
	}


}
