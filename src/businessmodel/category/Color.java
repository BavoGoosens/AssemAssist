package businessmodel.category;

import java.util.UUID;

/**
 * Class representing a color category.
 *
 * @author SWOP team 10 2013-2014
 */
public class Color extends VehicleOptionCategory {

    /**
     * The unique key of the category.
     */
    private final static UUID key = UUID.randomUUID();

    /**
     * Creates a new color category.
     */
    public Color() {
        super();
    }

    /**
     * Returns the unique key of the category.
     */
    @Override
    public UUID getKey() {
        return Color.key;
    }

}
