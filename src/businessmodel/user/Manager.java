package businessmodel.user;

/**
 * A class representing a manager.
 *
 * @author SWOP team 10
 */
public class Manager extends User {

    /**
     * Creates a new manager with a given first name, last name and user name.
     *
     * @param firstname The first name of the new manager.
     * @param lastname  The last name of the new manager.
     * @param username  The user name of the new manager.
     * @throws IllegalArgumentException
     */
    public Manager(String firstname, String lastname, String username) throws IllegalArgumentException {
        super(firstname, lastname, username);
    }

    @Override
    public boolean canViewStatistics() {
        return true;
    }

    @Override
    public boolean canViewAssemblyLines() {
        return true;
    }

    @Override
    public boolean canChangeAlgorithm() {
        return true;
    }

    @Override
    public boolean canChangeOperationalStatus() {
        return true;
    }
}
