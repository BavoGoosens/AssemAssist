package businessmodel.category;

import java.util.UUID;

public class Spoiler extends CarOptionCategory {
	
	private static UUID key;

	public Spoiler() {
		super();
		
	}

	@Override
	public UUID getKey() {
		return Spoiler.key;
	}

	@Override
	public CarOptionCategory create() {
		Spoiler.key = UUID.randomUUID();
		return this;
	}

}
