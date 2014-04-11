package businessmodel.category;

import java.util.UUID;

public class Airco extends CarOptionCategory {
	
	private static UUID key;

	public Airco() {
		super();
		Airco.key = UUID.randomUUID();
	}

	@Override
	public UUID getKey() {
		return Airco.key;
	}

}
