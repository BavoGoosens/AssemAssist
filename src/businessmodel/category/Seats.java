package businessmodel.category;

import java.util.UUID;

public class Seats extends CarOptionCategory {
	
	private final static UUID key = UUID.randomUUID();

	public Seats() {
		super();
	}

	@Override
	public UUID getKey() {
		return Seats.key;
	}

}
