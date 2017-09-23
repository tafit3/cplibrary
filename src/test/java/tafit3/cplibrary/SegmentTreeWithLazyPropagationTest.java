package tafit3.cplibrary;

import org.junit.Test;

import static org.fest.assertions.Assertions.*;
import static tafit3.cplibrary.CartesianPower.*;

public class SegmentTreeWithLazyPropagationTest {

    @Test
    public void testSimpleSegmentTree() {
        SegmentTreeWithLazyPropagation sg = new SegmentTreeWithLazyPropagation(new long[] { 0, 0 });
        sg.add(0,0,1);
        assertThat(sg.sum(0,1)).isEqualTo(1);
    }

    @Test
    public void testSimpleSegmentTree2() {
        SegmentTreeWithLazyPropagation sg = new SegmentTreeWithLazyPropagation(new long[] { 0, 0 });
        sg.add(0,1,1);
        assertThat(sg.sum(0,0)).isEqualTo(1);
    }

    @Test
    public void bruteForceSegmentTrees() {
        // config
        int maxArrayLength = 4;
        int initialElementValueCount = 3;
        int maxIncreaseValue = 3;
        int increaseQueryCount = 3;

        // test
        for (int n = 1; n <= maxArrayLength; n++) {
            int nn = n;
            cartesianPower(initialElementValueCount, n, initialArray ->
                cartesianPower(nn, increaseQueryCount, increaseQueryFrom ->
                    cartesianPower(nn, increaseQueryCount, increaseQueryTo -> {
                        if(allLessThanOrEqual(increaseQueryFrom, increaseQueryTo)) {
                            cartesianPower(maxIncreaseValue, increaseQueryCount, increaseQueryValues -> {
                                long[] v = int2longArray(initialArray);
                                long[] w = int2longArray(initialArray);
                                SegmentTreeWithLazyPropagation sg = new SegmentTreeWithLazyPropagation(v);
                                for (int i = 0; i <= increaseQueryCount; i++) {
                                    for(int from=0;from<nn;from++) for(int to=from;to<nn;to++) {
                                        long s = sg.sum(from, to);
                                        long b = bruteSum(w, from, to);
                                        assertThat(s).isEqualTo(b);
                                    }
                                    if(i < increaseQueryCount) {
                                        sg.add(increaseQueryFrom[i], increaseQueryTo[i], increaseQueryValues[i]);
                                        bruteAdd(w, increaseQueryFrom[i], increaseQueryTo[i], increaseQueryValues[i]);
                                    }
                                }
                            });
                        }
                    })
                )
            );
        }
    }

    private void bruteAdd(long[] w, int from, int to, int x) {
        for(int i=from;i<=to;i++) {
            w[i] += x;
        }
    }

    private long bruteSum(long[] w, int from, int to) {
        long res = 0;
        for(int i=from;i<=to;i++) {
            res += w[i];
        }
        return res;
    }

    private boolean allLessThanOrEqual(int[] a, int[] b) {
        for(int i=0;i<a.length;i++) {
            if(a[i] > b[i]) {
                return false;
            }
        }
        return true;
    }

    private static long[] int2longArray(int[] a) {
        long[] res = new long[a.length];
        for(int i=0;i<a.length;i++) {
            res[i] = a[i];
        }
        return res;
    }
}
