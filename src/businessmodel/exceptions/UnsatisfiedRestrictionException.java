package businessmodel.exceptions;

/**
 * UnsatisfiedRestriciontException
 *
 * @author SWOP team 10
 */
public class UnsatisfiedRestrictionException extends Exception {

    private static final long serialVersionUID = 60666997797613313L;

    /**
     * Constructor for UnsatisfiedRestriction.
     *
     * @param message
     */
    public UnsatisfiedRestrictionException(String message) {
        super(message);
    }

}
