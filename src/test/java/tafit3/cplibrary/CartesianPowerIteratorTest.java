package tafit3.cplibrary;

import java.util.ArrayList;
import java.util.List;

import static tafit3.cplibrary.CartesianPower.*;
import static tafit3.cplibrary.TestUtils.*;

public class CartesianPowerIteratorTest extends AbstractCartesianPowerTest {

    protected List<List<Integer>> solve(int setSize, int tupleLength) {
        List<List<Integer>> res = new ArrayList<>();
        cartesianPowerByIterator(setSize, tupleLength, tuple -> res.add(convertToList(tuple)));
        return res;
    }

}
