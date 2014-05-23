package businessmodel.category;

import java.util.UUID;

public class Storage extends VehicleOptionCategory {

    /**
     * The unique key of the category.
     */
    private final static UUID key = UUID.randomUUID();

    /**
     * Creates a new storage category.
     */
    public Storage() {
        super();
    }

    /**
     * Returns the unique key of the category.
     */
    @Override
    public UUID getKey() {
        return Storage.key;
    }

}
