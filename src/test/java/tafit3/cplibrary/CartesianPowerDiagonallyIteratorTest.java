package tafit3.cplibrary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.*;
import static tafit3.cplibrary.CartesianPowerDiagonally.*;
import static tafit3.cplibrary.TestUtils.*;

public class CartesianPowerDiagonallyIteratorTest extends AbstractCartesianPowerDiagonallyTest {

    protected List<List<Integer>> solve(int setSize, int tupleLength) {
        List<List<Integer>> res = new ArrayList<>();
        cartesianPowerDiagonallyByIterator(setSize, tupleLength, tuple -> res.add(convertToList(tuple)));
        return res;
    }

    @Test
    public void testLimit3() {
        Iterator<int[]> it = new CartesianPowerDiagonally.CartesianPowerDiagonallyIterator(100,2);
        out(asStream(it).filter(pair -> pair[0] < pair[1]).limit(4).map(TestUtils::convertToList).collect(toList()));
    }

    public static <T> Stream<T> asStream(Iterator<T> sourceIterator) {
        Iterable<T> iterable = () -> sourceIterator;
        return StreamSupport.stream(iterable.spliterator(), false);
    }

}
