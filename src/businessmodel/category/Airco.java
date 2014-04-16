package businessmodel.category;

import java.util.UUID;

public class Airco extends CarOptionCategory {
	
	private static UUID key;

	public Airco() {
		super();
	}

	@Override
	public UUID getKey() {
		return Airco.key;
	}

	public CarOptionCategory create() {
		Airco.key = UUID.randomUUID();
		return this;
	}

}
