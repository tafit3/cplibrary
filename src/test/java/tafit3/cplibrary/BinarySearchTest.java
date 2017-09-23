package tafit3.cplibrary;

import org.junit.Test;

import java.util.function.IntPredicate;

import static java.lang.Integer.*;
import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;
import static org.fest.assertions.Assertions.*;
import static tafit3.cplibrary.BinarySearch.binarySearch;
import static tafit3.cplibrary.BinarySearch.*;
import static tafit3.cplibrary.CartesianPower.*;

public class BinarySearchTest {

    @Test
    public void bruteBinarySearch() {
        for(int n=1;n<=5;n++) {
            int nn = n;
            cartesianPower(n+2, n, v -> {
                int[] w = runningTotal(v);
                if(allDistinct(w)) {
                    for (int x = -2; x <= ((nn + 2) * nn) + 2; x++) {
                        int xx = x;
                        int pos = indexOf(w, xx);
                        if (pos != MIN_VALUE) {
                            IntPredicate predicate = e -> xx < w[e];
                            int res = binarySearch(0, nn - 1, cached(predicate));
                            assertThat(res).isEqualTo(pos);
                        }
                    }
                }
            });
        }
    }

    private boolean allDistinct(int[] w) {
        return stream(w).boxed().collect(toSet()).size() == w.length;
    }

    private int[] runningTotal(int[] v) {
        int[] res = new int[v.length];
        res[0] = v[0];
        for(int i=1;i<v.length;i++) {
            res[i] = res[i-1] + v[i];
        }
        return res;
    }

    private static int indexOf(int[] a, int x) {
        int pos = 0;
        while(pos < a.length && a[pos] < x) pos++;
        if(pos < a.length && a[pos] == x) {
            return pos;
        }
        return MIN_VALUE;
    }

}
