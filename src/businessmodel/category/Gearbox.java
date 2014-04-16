package businessmodel.category;

import java.util.UUID;

public class Gearbox extends CarOptionCategory {
	
	private static UUID key;

	public Gearbox() {
		super();
		
	}

	@Override
	public UUID getKey() {
		return Gearbox.key;
	}

	@Override
	public CarOptionCategory create() {
		Gearbox.key = UUID.randomUUID();
		return this;
	}


}
