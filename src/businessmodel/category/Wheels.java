package businessmodel.category;

import java.util.UUID;

public class Wheels extends CarOptionCategory {
	
	private final static UUID key = UUID.randomUUID();

	public Wheels() {
		super();
	}

	@Override
	public UUID getKey() {
		return Wheels.key;
	}


}
