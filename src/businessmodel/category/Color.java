package businessmodel.category;

import java.util.UUID;

public class Color extends CarOptionCategory {
	
	private final static UUID key = UUID.randomUUID();

	public Color() {
		super();
	}

	@Override
	public UUID getKey() {
		return Color.key;
	}

}
