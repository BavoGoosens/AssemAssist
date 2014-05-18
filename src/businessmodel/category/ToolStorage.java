package businessmodel.category;

import java.util.UUID;

public class ToolStorage extends VehicleOptionCategory {
	
	/**
	 * The unique key of the category.
	 */
	private final static UUID key = UUID.randomUUID();

	public ToolStorage() {
		super();
	}

	@Override
	public UUID getKey() {
		return ToolStorage.key;
	}

}
