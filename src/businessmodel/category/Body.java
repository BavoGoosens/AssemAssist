package businessmodel.category;

import java.util.UUID;

public class Body extends CarOptionCategory {
	
	private static UUID key;

	public Body() {
		super();
	
	}

	@Override
	public UUID getKey() {
		return Body.key;
	}

	@Override
	public CarOptionCategory create() {
		Body.key = UUID.randomUUID();
		return this;
	}

}
