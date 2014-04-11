package businessmodel.category;

import java.util.UUID;

public class Seats extends CarOptionCategory {
	
	private static UUID key;

	public Seats() {
		super();
		Seats.key = UUID.randomUUID();
	}

	@Override
	public UUID getKey() {
		return Seats.key;
	}

}
