package businessmodel.util;

import java.util.Iterator;

/**
 * @author Team 10.
 */
@SuppressWarnings("rawtypes")
public class SafeIterator <Type> implements Iterator{

    private Iterator<Type> list;

    @Override
    public boolean hasNext() {
        if (list != null)
            return list.hasNext();
        else
            throw new RuntimeException("You need to use the convertIterator method first");
    }

    @Override
    public Type next() {
        if (list != null)
            return list.next();
        else
            throw new RuntimeException("You need to use the convertIterator method first");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("you can not use this :(");
    }

    public void convertIterator(Iterator<Type> iter){
        IteratorConverter<Type> converter = new IteratorConverter<Type>();
        this.list = converter.convert(iter).iterator();
    }
   
}
