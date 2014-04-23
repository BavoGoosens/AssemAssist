package businessmodel.category;

import java.util.UUID;

/**
 * Class representing an airco category.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class Airco extends CarOptionCategory {
	
	/**
	 * The unique key of the category.
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
