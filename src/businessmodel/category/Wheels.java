package businessmodel.category;

import java.util.UUID;

public class Wheels extends CarOptionCategory {
	
	private static UUID key;

	public Wheels() {
		super();
		Wheels.key = UUID.randomUUID();
	}

	@Override
	public UUID getKey() {
		return Wheels.key;
	}

}
