package businessmodel.category;

import java.util.UUID;

public class Engine extends CarOptionCategory {
	
	private final static UUID key = UUID.randomUUID();

	public Engine() {
		super();
<<<<<<< HEAD
	
=======
>>>>>>> 10f1a3a1e1567f9a33494156d9341b89044c7497
	}

	@Override
	public UUID getKey() {
		return Engine.key;
	}

	@Override
	public CarOptionCategory create() {
		Engine.key = UUID.randomUUID();
		return this;
	}

}
