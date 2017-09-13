package tafit3.cplibrary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.*;
import static org.fest.assertions.Assertions.*;
import static tafit3.cplibrary.CartesianPower.*;
import static tafit3.cplibrary.Combinations.*;
import static tafit3.cplibrary.TestUtils.*;

public class CombinationsTest {

    @Test
    public void testCombinations() {
        final int maxSize = 7;

        for(int n=1;n<=maxSize;n++) {
            for(int k=0;k<=n;k++) {
                out("n="+n+", k="+k);
                Set<List<Integer>> s = solve(n,k);
                Set<List<Integer>> b = brute(n,k);
                assertThat(s).isEqualTo(b);
            }
        }
    }

    private Set<List<Integer>> brute(int n, int k) {
        Set<List<Integer>> res = new HashSet<>();
        if(k == 0) {
            res.add(emptyList());
        } else {
            cartesianPower(n, k, v -> {
                Set<Integer> z = new HashSet<>();
                for (int x : v) {
                    z.add(x);
                }
                if (z.size() == k) {
                    res.add(new ArrayList<>(z).stream().sorted().collect(toList()));
                }
            });
        }
        return res;
    }

    private Set<List<Integer>> solve(int n, int k) {
        Set<List<Integer>> res = new HashSet<>();
        combinations(k, n, v -> res.add(convertToList(v)));
        return res;
    }

}
