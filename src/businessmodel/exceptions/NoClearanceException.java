package businessmodel.exceptions;

import businessmodel.user.User;

/**
 * NoClearanceException
 *
 * @author SWOP team 10
 */
public class NoClearanceException extends Exception {

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 2038156514090663821L;
    private User user;

    public NoClearanceException() {
    }

    /**
     * Constructor for NoClearanceException with given Message.
     *
     * @param message
     */
    public NoClearanceException(String message) {
        super(message);
    }

    /**
     * Constructor for NoClearanceException with given User.
     *
     * @param user
     */
    public NoClearanceException(User user) {
        this.setUser(user);
    }

    /**
     * Constructor for NoClearanceException with given User and message.
     *
     * @param user
     * @param message
     */
    public NoClearanceException(User user, String message) {
        super(message);
        this.setUser(user);
    }

    /**
     * Get User.
     *
     * @return
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Set User.
     *
     * @param user
     */
    private void setUser(User user) {
        this.user = user;
    }


}
