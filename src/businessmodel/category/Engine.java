package businessmodel.category;

import java.util.UUID;

/**
 * Class representing a engine category.
 * 
 * @author team 10
 *
 */
public class Engine extends CarOptionCategory {
	
	/**
	 * Indicates the unique key of the category.
	 */
	private final static UUID key = UUID.randomUUID();
	
	/**
	 * Creates a new engine category.
	 */
	public Engine() {
		super();
	}

	/**
	 * Returns the unique key of the category.
	 */
	@Override
	public UUID getKey() {
		return Engine.key;
	}


}
