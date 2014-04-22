package businessmodel.category;

import java.util.UUID;

/**
 * Class representing a seats category.
 * 
 * @author team 10
 *
 */
public class Seats extends CarOptionCategory {
	
	/**
	 * Indicates the unique key of the category.
	 */
	private final static UUID key = UUID.randomUUID();

	/**
	 * Creates a new seats category.
	 */
	public Seats() {
		super();
	}
	
	/**
	 * Returns the unique key of the category.
	 */
	@Override
	public UUID getKey() {
		return Seats.key;
	}


}
