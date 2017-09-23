package tafit3.cplibrary;

import java.util.Arrays;
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

    public static long newton(long n, long k) {
        return fac(n) / (fac(k)*fac(n-k));
    }

    public static long fac(long x) {
        long res = 1;
        for(long a=1;a<=x;a++) {
            res *= a;
        }
        return res;
    }

    public static List<Integer> convertToList(int[] a) {
        return stream(a).boxed().collect(toList());
    }

    public static void out(Object x) {
        System.out.println(x);
    }

    public static class Holder<T> {
        T object;
    }

    public static String ats(int[] x) {
        return Arrays.toString(x);
    }

    public static String ats(long[] x) {
        return Arrays.toString(x);
    }

    public static String ats(Object[] x) {
        return Arrays.deepToString(x);
    }

    public static long[] int2longArray(int[] a) {
        long[] res = new long[a.length];
        for(int i=0;i<a.length;i++) {
            res[i] = a[i];
        }
        return res;
    }
}
