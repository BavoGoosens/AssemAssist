package businessmodel.category;

import java.util.UUID;

/**
 * Class representing a gearbox category.
 * 
 * @author team 10
 *
 */
public class Gearbox extends CarOptionCategory {
	
	/**
	 * Indicates the unique key of the category.
	 */
	private final static UUID key = UUID.randomUUID();

	/**
	 * Creates a new gearbox category.
	 */
	public Gearbox() {
		super();
	}

	/**
	 * Returns the unique key of the category.
	 */
	@Override
	public UUID getKey() {
		return Gearbox.key;
	}


}
