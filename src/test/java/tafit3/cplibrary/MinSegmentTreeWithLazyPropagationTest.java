package tafit3.cplibrary;

import org.junit.Test;

import static java.lang.Long.*;
import static java.lang.Math.min;
import static org.fest.assertions.Assertions.*;
import static tafit3.cplibrary.CartesianPower.*;
import static tafit3.cplibrary.TestUtils.*;

public class MinSegmentTreeWithLazyPropagationTest {

    @Test
    public void testSimpleSegmentTree() {
        MinSegmentTreeWithLazyPropagation sg = new MinSegmentTreeWithLazyPropagation(new long[] { 0, 0 });
        sg.add(0,0,1);
        assertThat(sg.getMin(0,1)).isEqualTo(0);
    }

    @Test
    public void testSimpleSegmentTree2() {
        MinSegmentTreeWithLazyPropagation sg = new MinSegmentTreeWithLazyPropagation(new long[] { 0, 0 });
        sg.add(0,1,1);
        assertThat(sg.getMin(0,0)).isEqualTo(1);
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
                                MinSegmentTreeWithLazyPropagation sg = new MinSegmentTreeWithLazyPropagation(v);
                                for (int i = 0; i <= increaseQueryCount; i++) {
                                    for(int from=0;from<nn;from++) for(int to=from;to<nn;to++) {
                                        long s = sg.getMin(from, to);
                                        long b = bruteMin(w, from, to);
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

    private long bruteMin(long[] w, int from, int to) {
        long res = MAX_VALUE;
        for(int i=from;i<=to;i++) {
            res = min(res, w[i]);
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

}
