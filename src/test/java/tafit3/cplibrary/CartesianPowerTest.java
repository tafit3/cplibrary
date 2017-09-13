package tafit3.cplibrary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import static java.lang.Math.*;
import static java.util.stream.Collectors.*;
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
    public void tuplesAreInOrder() {
        allSetSizesAndTupleLengths((setSize, tupleLength, result) ->
                assertThat(result).isEqualTo(result.stream().sorted(INTEGER_TUPLE_COMPARATOR).collect(toList())));
    }

    @Test
    public void cartesianPowerHasSize_setSize_to_the_power_of_tupleLength() {
        allSetSizesAndTupleLengths((setSize, tupleLength, result) ->
                assertThat(result).hasSize((int) naivePow(setSize, tupleLength)));
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

    private void allSetSizesAndTupleLengths(CartesianPowerResultConsumer consumer) {
        final int maxSetSize = 7;
        final int maxTupleLength = 7;

        for(int setSize=1;setSize<=maxSetSize;setSize++) {
            for (int tupleLength = 0; tupleLength <= maxTupleLength; tupleLength++) {
                out("setSize="+setSize+", tupleLength="+tupleLength);
                consumer.consume(setSize, tupleLength, solve(setSize, tupleLength));
            }
        }
    }

    interface CartesianPowerResultConsumer {
        void consume(int setSize, int tupleLength, List<List<Integer>> result);
    }

    private List<List<Integer>> solve(int setSize, int tupleLength) {
        List<List<Integer>> res = new ArrayList<>();
        cartesianPower(setSize, tupleLength, tuple -> res.add(convertToList(tuple)));
        return res;
    }

    private long brutePow(long a, long b) {
        return (long)pow(a,b);
    }
}
