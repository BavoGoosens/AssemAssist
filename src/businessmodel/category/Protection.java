package businessmodel.category;

import java.util.UUID;

public class Protection extends VehicleOptionCategory {

	/**
	 * The unique key of the category.
	 */
	private final static UUID key = UUID.randomUUID();
	
	public Protection() {
		super();
	}

	@Override
	public UUID getKey() {
		return Protection.key;
	}

}
