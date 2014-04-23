package businessmodel.category;

import java.util.UUID;

/**
 * Class representing an airco category.
 * 
 * @author team 10
 *
 */
public class Airco extends CarOptionCategory {
	
	/**
	 * Indicates the unique key of the category.
	 */
	private final static UUID key = UUID.randomUUID();
	
	/**
	 * Creates a new airco category.
	 */
	public Airco() {
		super();
	}
	
	/**
	 * Returns the unique key of the category.
	 */
	@Override
	public UUID getKey() {
		return Airco.key;
	}

}
