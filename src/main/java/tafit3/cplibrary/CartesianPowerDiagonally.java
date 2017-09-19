package tafit3.cplibrary;

import java.util.Iterator;
import java.util.function.Consumer;

import static java.lang.Math.*;
import static java.lang.System.*;
import static java.util.Arrays.*;

public class CartesianPowerDiagonally {

    public static void cartesianPowerDiagonally(int setSize, int tupleLength, Consumer<int[]> consumer) {
        int[] a = new int[tupleLength];
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

    public static void cartesianPowerDiagonallyByIterator(int setSize, int tupleLength, Consumer<int[]> consumer) {
        Iterator<int[]> it = new CartesianPowerDiagonallyIterator(setSize, tupleLength);
        while(it.hasNext()) {
            consumer.accept(it.next());
        }
    }

    public static class CartesianPowerDiagonallyIterator implements Iterator<int[]> {
        private final int setSize;
        private final int tupleLength;
        private final int[][] tuples;
        private final long s;
        private boolean hasNext;
        private int cur;
        private int i;

        public CartesianPowerDiagonallyIterator(int setSize, int tupleLength) {
            this.setSize = setSize;
            this.tupleLength = tupleLength;
            tuples = new int[2][tupleLength];
            s = (setSize-1) * tupleLength;
            hasNext = true;
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public int[] next() {
            int[] res = tuples[cur];
            advance();
            return res;
        }

        private void advance() {
            if(tupleLength == 0) {
                hasNext = false;
                return;
            }
            int old = cur;
            cur = 1-cur;
            arraycopy(tuples[old],0,tuples[cur],0,tupleLength);

            int[] a = tuples[cur];
            int j = tupleLength - 1;
            int sum = 0;
            while (j > 0) {
                if (a[j - 1] < setSize - 1 && a[j] > 0) {
                    a[j]--;
                    a[j - 1]++;
                    sum += a[j];
                    int k = tupleLength - 1;
                    while (k >= j) {
                        int q = min(sum, setSize - 1);
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
                i++;
                if(i > s) {
                    hasNext = false;
                } else {
                    fill(a, 0);
                    int k = tupleLength - 1;
                    int rest = i;
                    while (k >= 0 && rest > 0) {
                        int q = min(rest, setSize - 1);
                        a[k] = q;
                        rest -= q;
                        k--;
                    }
                }
            }
        }
    }

}
