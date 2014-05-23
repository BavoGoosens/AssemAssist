package businessmodel.category;

import java.util.UUID;

public class Certification extends VehicleOptionCategory {

    /**
     * The unique key of the category.
     */
    private final static UUID key = UUID.randomUUID();

    /**
     * Creates a new certification category.
     */
    public Certification() {
        super();
    }

    /**
     * Returns the unique key of the category.
     */
    @Override
    public UUID getKey() {
        return Certification.key;
    }

}
