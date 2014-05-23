package businessmodel.user;


/**
 * A class representing a garage holder.
 *
 * @author SWOP team 10
 */
public class GarageHolder extends User {

    /**
     * Creates a new garage holder with a given first name, last name and user name.
     *
     * @param firstname The first name of the new garage holder.
     * @param lastname  The last name of the new garage holder.
     * @param username  The user name for the new garage holder.
     * @throws IllegalArgumentException
     */
    public GarageHolder(String firstname, String lastname, String username) throws IllegalArgumentException {
        super(firstname, lastname, username);
    }

    @Override
    public boolean canPlaceOrder() {
        return true;
    }
}
