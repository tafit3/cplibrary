package tafit3.cplibrary;

import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class TestUtils {

    public static List<Integer> convertToList(int[] a) {
        return stream(a).boxed().collect(toList());
    }

    public static void out(Object x) {
        System.out.println(x);
    }
}
