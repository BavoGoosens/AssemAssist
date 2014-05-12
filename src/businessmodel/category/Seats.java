package businessmodel.category;

import java.util.UUID;

/**
 * Class representing a seats category.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class Seats extends VehicleOptionCategory {
	
	/**
	 * The unique key of the category.
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
