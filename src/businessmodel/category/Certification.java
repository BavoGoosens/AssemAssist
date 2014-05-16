package businessmodel.category;

import java.util.UUID;

public class Certification extends VehicleOptionCategory {
	
	/**
	 * The unique key of the category.
	 */
	private final static UUID key = UUID.randomUUID();

	public Certification() {
		super();
	}

	@Override
	public UUID getKey() {
		return Certification.key;
	}

}
