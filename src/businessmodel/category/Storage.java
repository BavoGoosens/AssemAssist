package businessmodel.category;

import java.util.UUID;

public class Storage extends VehicleOptionCategory {
	
	/**
	 * The unique key of the category.
	 */
	private final static UUID key = UUID.randomUUID();

	public Storage() {
		super();
	}

	@Override
	public UUID getKey() {
		return Storage.key;
	}

}
