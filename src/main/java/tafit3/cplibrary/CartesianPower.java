package tafit3.cplibrary;

import java.util.function.Consumer;

public class CartesianPower {

    public static void cartesianPower(int setSize, int tupleLength, Consumer<int[]> consumer) {
        long p = naivePow(setSize, tupleLength);
        int[] c = new int[tupleLength];
        for(long i=0;i<p;i++) {
            long u = i;
            for(int k=tupleLength-1;k>=0;k--) {
                c[k] = (int)(u%setSize);
                u /= setSize;
            }
            consumer.accept(c);
        }
    }

    public static long naivePow(long a, long b) {
        long c = 1;
        for(long i=0;i<b;i++) {
            c *= a;
        }
        return c;
    }

}
