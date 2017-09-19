package tafit3.cplibrary;

import java.util.ArrayList;
import java.util.List;

import static tafit3.cplibrary.CartesianPowerDiagonally.*;
import static tafit3.cplibrary.TestUtils.*;

public class CartesianPowerDiagonallyTest extends AbstractCartesianPowerDiagonallyTest {

    protected List<List<Integer>> solve(int setSize, int tupleLength) {
        List<List<Integer>> res = new ArrayList<>();
        cartesianPowerDiagonally(setSize, tupleLength, tuple -> res.add(convertToList(tuple)));
        return res;
    }

}
