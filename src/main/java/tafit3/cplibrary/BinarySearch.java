package tafit3.cplibrary;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntPredicate;

public class BinarySearch {

    public static int binarySearch(int from, int to, IntPredicate isLower) {
        int lo = from;
        int hi = to;
        while(hi - lo > 1) {
            int m = (lo + hi) / 2;
            if(isLower.test(m)) {
                hi = m-1;
            } else {
                lo = m;
            }
        }
        while(lo <= to && !isLower.test(lo)) lo++;
        return lo - 1;
    }

    public static IntPredicate cached(IntPredicate predicate) {
        return cached((Function<Integer, Boolean>)predicate::test)::apply;
    }

    private static <A,B> Function<A,B> cached(Function<A,B> function) {
        Map<A,B> cache = new HashMap<>();
        return a -> {
            B res = cache.get(a);
            if(res == null) {
                res = function.apply(a);
                cache.put(a, res);
            }
            return res;
        };
    }
}
