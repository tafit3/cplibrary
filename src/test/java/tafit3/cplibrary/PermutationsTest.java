package tafit3.cplibrary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import static java.util.stream.Collectors.*;
import static org.fest.assertions.Assertions.*;
import static tafit3.cplibrary.TestUtils.*;

public class PermutationsTest {

    @Test
    public void tuplesAreInOrder() {
        allTupleLengths((tupleLength, result) ->
                assertThat(result).isEqualTo(result.stream().sorted(INTEGER_TUPLE_COMPARATOR).collect(toList())));
    }

    @Test
    public void permutationsSizeIsFactorialOfTupleLength() {
        allTupleLengths((tupleLength, result) ->
                assertThat(result).hasSize((int) fac(tupleLength)));
    }

    @Test
    public void allTuplesHaveProperLength() {
        allTupleLengths((tupleLength, result) ->
                result.forEach(tuple -> assertThat(tuple).hasSize(tupleLength)));
    }

    @Test
    public void tupleContainsDistinctElements() {
        allTupleLengths((tupleLength, result) ->
                result.forEach(tuple -> assertThat(new HashSet<>(tuple)).hasSize(tupleLength)));
    }

    @Test
    public void allTuplesContainElementsBetween_0_and_tupleLength_minus_1() {
        allTupleLengths((tupleLength, result) ->
                result.forEach(tuple -> {
                    if(tupleLength != 0 || !tuple.isEmpty()) {
                        NavigableSet<Integer> elements = new TreeSet<>(tuple);
                        assertThat(elements.first()).isGreaterThanOrEqualTo(0);
                        assertThat(elements.last()).isLessThan(tupleLength);
                    }
                }));
    }

    @Test
    public void allTuplesAreDistinct() {
        allTupleLengths((tupleLength, result) ->
                assertThat(result).hasSize(new HashSet<>(result).size()));
    }

    private void allTupleLengths(PermutationsResultConsumer consumer) {
        final int maxTupleLength = 8;

        for (int tupleLength = 1; tupleLength <= maxTupleLength; tupleLength++) {
            out("tupleLength="+tupleLength);
            consumer.accept(tupleLength, solve(tupleLength));
        }
    }

    interface PermutationsResultConsumer {
        void accept(int tupleLength, List<List<Integer>> result);
    }

    private List<List<Integer>> solve(int tupleLength) {
        List<List<Integer>> res = new ArrayList<>();
        Iterator<int[]> it = new Permutations.PermutationsIterator(tupleLength);
        while(it.hasNext()) {
            res.add(convertToList(it.next()));
        }
        return res;
    }

}
