package businessmodel.category;

import java.util.UUID;

public class Spoiler extends CarOptionCategory {
	
	private static UUID key;

	public Spoiler() {
		super();
		Spoiler.key = UUID.randomUUID();
	}

	@Override
	public UUID getKey() {
		return Spoiler.key;
	}

}
