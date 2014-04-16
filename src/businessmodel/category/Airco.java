package businessmodel.category;

import java.util.UUID;

public class Airco extends CarOptionCategory {
	
	private final static UUID key = UUID.randomUUID();

	public Airco() {
		super();
	}

	@Override
	public UUID getKey() {
		return Airco.key;
	}

}
