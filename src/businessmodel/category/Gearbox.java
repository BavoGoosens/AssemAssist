package businessmodel.category;

import java.util.UUID;

public class Gearbox extends CarOptionCategory {
	
	private static UUID key;

	public Gearbox() {
		super();
		Gearbox.key = UUID.randomUUID();
	}

	@Override
	public UUID getKey() {
		return Gearbox.key;
	}


}
