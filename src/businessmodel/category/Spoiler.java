package businessmodel.category;

import java.util.UUID;

public class Spoiler extends CarOptionCategory {
	
	private final static UUID key = UUID.randomUUID();

	public Spoiler() {
		super();
	}

	@Override
	public UUID getKey() {
		return Spoiler.key;
	}


}
