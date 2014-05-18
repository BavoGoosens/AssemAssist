package businessmodel.category;

import java.util.UUID;

public class CargoProtection extends VehicleOptionCategory {

	/**
	 * The unique key of the category.
	 */
	private final static UUID key = UUID.randomUUID();
	
	public CargoProtection() {
		super();
	}

	@Override
	public UUID getKey() {
		return CargoProtection.key;
	}

}
