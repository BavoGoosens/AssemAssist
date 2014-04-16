package businessmodel.category;

import java.util.UUID;

public class Wheels extends CarOptionCategory {
	
	private static UUID key;

	public Wheels() {
		super();
	}

	@Override
	public UUID getKey() {
		return Wheels.key;
	}

	@Override
	public CarOptionCategory create() {
		Wheels.key = UUID.randomUUID();
		return this;
	}

}
