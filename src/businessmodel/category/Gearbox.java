package businessmodel.category;

import java.util.UUID;

public class Gearbox extends CarOptionCategory {
	
	private final static UUID key = UUID.randomUUID();

	public Gearbox() {
		super();
	}

	@Override
	public UUID getKey() {
		return Gearbox.key;
	}


}
