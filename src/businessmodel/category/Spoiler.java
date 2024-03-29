package businessmodel.category;

import java.util.UUID;

/**
 * Class representing a spoiler category.
 *
 * @author SWOP team 10 2013-2014
 */
public class Spoiler extends VehicleOptionCategory {

    /**
     * The unique key of the category.
     */
    private final static UUID key = UUID.randomUUID();

    /**
     * Creates a new spoiler category.
     */
    public Spoiler() {
        super();
    }

    /**
     * Returns the unique key of the category.
     */
    @Override
    public UUID getKey() {
        return Spoiler.key;
    }


}
