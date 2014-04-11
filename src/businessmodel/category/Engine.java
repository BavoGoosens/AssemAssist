package businessmodel.category;

import java.util.UUID;

public class Engine extends CarOptionCategory {
	
	private static UUID key;

	public Engine() {
		super();
		Engine.key = UUID.randomUUID();
	}

	@Override
	public UUID getKey() {
		return Engine.key;
	}

}
