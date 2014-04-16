package businessmodel.category;

import java.util.UUID;

public class Engine extends CarOptionCategory {
	
	private static UUID key;

	public Engine() {
		super();
	
	}

	@Override
	public UUID getKey() {
		return Engine.key;
	}

	@Override
	public CarOptionCategory create() {
		Engine.key = UUID.randomUUID();
		return this;
	}

}
