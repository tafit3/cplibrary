package tafit3.cplibrary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;
import static org.fest.assertions.Assertions.*;
import static tafit3.cplibrary.CartesianPower.*;
import static tafit3.cplibrary.TestUtils.*;

public class CartesianPowerTest extends AbstractCartesianPowerTest {

    protected List<List<Integer>> solve(int setSize, int tupleLength) {
        List<List<Integer>> res = new ArrayList<>();
        cartesianPower(setSize, tupleLength, tuple -> res.add(convertToList(tuple)));
        return res;
    }

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

    private long brutePow(long a, long b) {
        return (long)pow(a,b);
    }

}
