package businessmodel.category;

import java.util.UUID;

public class Body extends CarOptionCategory {
	
	private static UUID key;

	public Body() {
		super();
		Body.key = UUID.randomUUID();
	}

	@Override
	public UUID getKey() {
		return Body.key;
	}

}
