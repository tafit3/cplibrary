package tafit3.cplibrary;

import java.util.function.Consumer;

public class Combinations {

    public static void combinations(int k, int n, Consumer<int[]> consumer) {
        if(k == 0) {
            consumer.accept(new int[0]);
        } else {
            int[] d = new int[k];
            for (int i = 0; i < k; i++) {
                d[i] = i;
            }
            while (true) {
                consumer.accept(d);

                int j = k - 1;
                if (d[j] < n - 1) {
                    d[j]++;
                } else {
                    while (j > 0 && d[j - 1] + 1 == d[j]) {
                        j--;
                    }
                    if (j == 0) {
                        break;
                    } else {
                        d[j - 1]++;
                        while (j < k) {
                            d[j] = d[j - 1] + 1;
                            j++;
                        }
                    }
                }
            }
        }
    }

}
