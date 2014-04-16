package businessmodel.category;

import java.util.UUID;

public class Color extends CarOptionCategory {
	
	private static UUID key;

	public Color() {
		super();
	
	}

	@Override
	public UUID getKey() {
		return Color.key;
	}

	@Override
	public CarOptionCategory create() {
		Color.key = UUID.randomUUID();
		return this;
	}

}
