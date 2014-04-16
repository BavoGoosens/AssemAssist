package businessmodel.category;

import java.util.UUID;

public class Seats extends CarOptionCategory {
	
	private static UUID key;

	public Seats() {
		super();
		
	}

	@Override
	public UUID getKey() {
		return Seats.key;
	}

	@Override
	public CarOptionCategory create() {
		Seats.key = UUID.randomUUID();
		return this;
	}

}
