package tafit3.cplibrary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import static java.util.stream.Collectors.*;
import static org.fest.assertions.Assertions.*;
import static tafit3.cplibrary.Combinations.*;
import static tafit3.cplibrary.TestUtils.*;

public class CombinationsTest {

    @Test
    public void tuplesAreInOrder() {
        allSetSizesAndTupleLengths((setSize, tupleLength, result) ->
                assertThat(result).isEqualTo(result.stream().sorted(INTEGER_TUPLE_COMPARATOR).collect(toList())));
    }

    @Test
    public void combinationsHaveSize_newton_setSize_over_tupleLength() {
        allSetSizesAndTupleLengths((setSize, tupleLength, result) ->
                assertThat(result).hasSize((int) newton(setSize, tupleLength)));
    }

    @Test
    public void allTuplesHaveProperLength() {
        allSetSizesAndTupleLengths((setSize, tupleLength, result) ->
                result.forEach(tuple -> assertThat(tuple).hasSize(tupleLength)));
    }

    @Test
    public void allTuplesContainElementsBetween_0_and_setSize_minus_1() {
        allSetSizesAndTupleLengths((setSize, tupleLength, result) ->
                result.forEach(tuple -> {
                    if(tupleLength != 0 || !tuple.isEmpty()) {
                        NavigableSet<Integer> elements = new TreeSet<>(tuple);
                        assertThat(elements.first()).isGreaterThanOrEqualTo(0);
                        assertThat(elements.last()).isLessThan(setSize);
                    }
                }));
    }

    @Test
    public void allTuplesAreDistinct() {
        allSetSizesAndTupleLengths((setSize, tupleLength, result) ->
                assertThat(result).hasSize(new HashSet<>(result).size()));
    }

    private void allSetSizesAndTupleLengths(CombinationsResultConsumer consumer) {
        final int maxSetSize = 7;

        for(int setSize=1;setSize<=maxSetSize;setSize++) {
            for (int tupleLength = 0; tupleLength <= setSize; tupleLength++) {
                out("setSize="+setSize+", tupleLength="+tupleLength);
                consumer.consume(setSize, tupleLength, solve(setSize, tupleLength));
            }
        }
    }

    interface CombinationsResultConsumer {
        void consume(int setSize, int tupleLength, List<List<Integer>> result);
    }

    private List<List<Integer>> solve(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combinations(k, n, v -> res.add(convertToList(v)));
        return res;
    }

    private long newton(long n, long k) {
        return fac(n) / (fac(k)*fac(n-k));
    }

    private long fac(long x) {
        long res = 1;
        for(long a=1;a<=x;a++) {
            res *= a;
        }
        return res;
    }

}
