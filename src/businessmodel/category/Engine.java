package businessmodel.category;

import java.util.UUID;

public class Engine extends CarOptionCategory {
	
	private final static UUID key = UUID.randomUUID();

	public Engine() {
		super();
	}

	@Override
	public UUID getKey() {
		return Engine.key;
	}


}
