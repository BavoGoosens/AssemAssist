package businessmodel.category;

import java.util.UUID;

public class Color extends CarOptionCategory {
	
	private static UUID key;

	public Color() {
		super();
		Color.key = UUID.randomUUID();
	}

	@Override
	public UUID getKey() {
		return Color.key;
	}

}
