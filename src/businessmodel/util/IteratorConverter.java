package businessmodel.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Convert iterator to arrayList.
 *
 * @author Team 10
 */
public class IteratorConverter<Type> {

    /**
     * Convert iterator to arrayList.
     *
     * @param iterator
     * @return
     */
    public List<Type> convert(Iterator<Type> iterator) {
        ArrayList<Type> lijst = new ArrayList<Type>();
        while (iterator.hasNext())
            lijst.add(iterator.next());
        return lijst;
    }
}


