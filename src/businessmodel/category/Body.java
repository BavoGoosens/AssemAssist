package businessmodel.category;

import java.util.UUID;

public class Body extends CarOptionCategory {
	
	private final static UUID key = UUID.randomUUID();

	public Body() {
		super();
	}

	@Override
	public UUID getKey() {
		return Body.key;
	}


}
