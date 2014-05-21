package businessmodel.category;

import java.util.UUID;

public class Protection extends VehicleOptionCategory {

	/**
	 * The unique key of the category.
	 */
	private final static UUID key = UUID.randomUUID();
	
	/**
	 * Creates a new protection category.
	 */
	public Protection() {
		super();
	}

	/**
	 * Returns the unique key of the category.
	 */
	@Override
	public UUID getKey() {
		return Protection.key;
	}

}
