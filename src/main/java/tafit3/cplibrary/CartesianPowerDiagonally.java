package tafit3.cplibrary;

import java.util.function.Consumer;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public class CartesianPowerDiagonally {

    public static void cartesianPowerDiagonally(int setSize, int tupleLength, Consumer<int[]> consumer) {
        int[] a = new int[tupleLength];
        if(tupleLength == 0 || setSize == 1) {
            consumer.accept(a);
        } else {
            long s = (setSize-1) * tupleLength;
            for (int i = 0; i <= s; i++) {
                fill(a, 0);
                int j = tupleLength - 1;
                int rest = i;
                while (j >= 0 && rest > 0) {
                    int q = min(rest, setSize-1);
                    a[j] = q;
                    rest -= q;
                    j--;
                }
                consumer.accept(a);
                while (true) {
                    j = tupleLength - 1;
                    if (j > 0) {
                        int sum = 0;
                        while (j > 0) {
                            if (a[j - 1] < setSize-1 && a[j] > 0) {
                                a[j]--;
                                a[j - 1]++;
                                sum += a[j];
                                int k = tupleLength - 1;
                                while (k >= j) {
                                    int q = min(sum, setSize-1);
                                    a[k] = q;
                                    sum -= q;
                                    k--;
                                }
                                break;
                            }
                            sum += a[j];
                            j--;
                        }
                        if (j == 0) {
                            break;
                        }
                        consumer.accept(a);
                    } else {
                        break;
                    }
                }
            }
        }
    }

}
