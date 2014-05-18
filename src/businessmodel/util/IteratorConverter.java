package businessmodel.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Team 10
 */
public class IteratorConverter <Type> {

    public List<Type> convert(Iterator<Type> iterator){
        ArrayList<Type> lijst = new ArrayList<Type>();
        while (iterator.hasNext())
            lijst.add(iterator.next());
        return  lijst;
    }
}


