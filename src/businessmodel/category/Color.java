package businessmodel.category;

import java.util.UUID;

public class Color extends CarOptionCategory {
	
	private final static UUID key = UUID.randomUUID();

	public Color() {
		super();
<<<<<<< HEAD
	
=======
>>>>>>> 10f1a3a1e1567f9a33494156d9341b89044c7497
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
