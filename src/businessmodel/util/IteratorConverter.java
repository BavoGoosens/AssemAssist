package businessmodel.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

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


