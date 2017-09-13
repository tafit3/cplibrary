package tafit3.cplibrary;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

public class TestUtils {
    public static <T extends Comparable<T>> Comparator<List<T>> tupleComparator() {
        return (a, b) -> {
            Iterator<T> aIt = a.iterator();
            Iterator<T> bIt = b.iterator();
            while (aIt.hasNext() && bIt.hasNext()) {
                T x = aIt.next();
                T y = bIt.next();
                int res = x.compareTo(y);
                if (res > 0) {
                    return 1;
                } else if(res < 0) {
                    return -1;
                }
            }
            if(aIt.hasNext()) {
                return 1;
            }
            if(bIt.hasNext()) {
                return -1;
            }
            return 0;
        };
    }

    public static final Comparator<List<Integer>> INTEGER_TUPLE_COMPARATOR = tupleComparator();

    public static List<Integer> convertToList(int[] a) {
        return stream(a).boxed().collect(toList());
    }

    public static void out(Object x) {
        System.out.println(x);
    }

    public static class Holder<T> {
        T object;
    }
}
