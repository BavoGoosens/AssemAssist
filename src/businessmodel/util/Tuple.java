package businessmodel.util;

/**
 * A class representing a generic tuple.
 *
 * @param <X> The type of the first variable of the tuple.
 * @param <Y> The type of the second variable of the tuple.
 * @author SWOP team 10 2013-2014
 */
public class Tuple<X, Y> {

    /**
     * The first variable of the tuple.
     */
    private X x;

    /**
     * The second variable of the tuple.
     */
    private Y y;

    /**
     * Creates a new tuple object with 2 given variables.
     *
     * @param x The first variable.
     * @param y The second variable.
     */
    // no setters for final variable
    public Tuple(X x, Y y) {
        this.setX(x);
        this.increaseY(y);
    }

    /**
     * Returns the first variable of the tuple.
     *
     * @return The first variable (x) of the tuple.
     */
    public X getX() {
        return this.x;
    }

    private void setX(X x) {
        this.x = x;
    }

    /**
     * Returns the second variable of the tuple.
     *
     * @return The second variable (y) of the tuple.
     */
    public Y getY() {
        return this.y;
    }

    public void increaseY(Y y) {
        this.setY(y);
    }

    private void setY(Y y){
        this .y = y;
    }
    @Override
    public int hashCode() {
        return this.x.hashCode() ^ this.y.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (!(o instanceof Tuple))
            return false;
        @SuppressWarnings("unchecked")
        Tuple<X, Y> pairo = (Tuple<X, Y>) o;
        return this.x.equals(pairo.getX()) &&
                this.y.equals(pairo.getY());
    }
}
