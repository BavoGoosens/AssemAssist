package businessmodel.category;

import java.util.UUID;

/**
 * Class representing a gear box category.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class Gearbox extends VehicleOptionCategory {
	
	/**
	 * The unique key of the category.
	 */
	private final static UUID key = UUID.randomUUID();

	/**
	 * Creates a new gear box category.
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
