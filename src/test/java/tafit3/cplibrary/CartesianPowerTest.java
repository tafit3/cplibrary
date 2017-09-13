package tafit3.cplibrary;

import org.fest.assertions.Condition;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.Math.*;
import static java.util.stream.Collectors.toSet;
import static org.fest.assertions.Assertions.*;
import static tafit3.cplibrary.CartesianPower.*;
import static tafit3.cplibrary.TestUtils.*;

public class CartesianPowerTest {

    @Test
    public void testNaivePow() {
        final long maxValue = 10;

        for(long a=0;a<=maxValue;a++) {
            for(long b=0;b<=maxValue;b++) {
                long s = naivePow(a, b);
                long br = brutePow(a, b);
                assertThat(s).isEqualTo(br);
            }
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCartesianPower() {
        final int maxSetSize = 7;
        final int maxTupleLength = 7;

        for(int n=1;n<=maxSetSize;n++) {
            int nn = n;
            for(int k=0;k<=maxTupleLength;k++) {
                int kk = k;
                Set<List<Integer>> s = solve(n, k);
                out("n="+n+", k="+k);
                assertThat(s).hasSize((int)naivePow(n, k));
                for(List<Integer> tuple: s) {
                    assertThat(tuple).hasSize(k);
                    assertThat(tuple).satisfies(new Condition<List<?>>() {
                        @Override
                        public boolean matches(List<?> list) {
                            if(list.isEmpty() && kk == 0) {
                                return true;
                            }
                            NavigableSet<Integer> elements = new TreeSet<>((List<Integer>) list);
                            return elements.first() >= 0 && elements.last() < nn;
                        }
                    });
                }
            }
        }
    }

    private Set<List<Integer>> solve(int n, int k) {
        Set<List<Integer>> res = new HashSet<>();
        cartesianPower(n, k, v -> res.add(convertToList(v)));
        return res;
    }

    private long brutePow(long a, long b) {
        return (long)pow(a,b);
    }
}
