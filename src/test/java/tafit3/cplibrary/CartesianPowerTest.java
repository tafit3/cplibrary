package tafit3.cplibrary;

import java.util.ArrayList;
import java.util.List;

import static tafit3.cplibrary.CartesianPower.*;
import static tafit3.cplibrary.TestUtils.*;

public class CartesianPowerTest extends AbstractCartesianPowerTest {

    protected List<List<Integer>> solve(int setSize, int tupleLength) {
        List<List<Integer>> res = new ArrayList<>();
        cartesianPower(setSize, tupleLength, tuple -> res.add(convertToList(tuple)));
        return res;
    }

}
