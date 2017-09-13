package tafit3.cplibrary;

import java.util.Iterator;
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

    public static void cartesianPowerByIterator(int setSize, int tupleLength, Consumer<int[]> consumer) {
        Iterator<int[]> it = new CartesianPowerIterator(setSize, tupleLength);
        while(it.hasNext()) {
            consumer.accept(it.next());
        }
    }

    public static class CartesianPowerIterator implements Iterator<int[]> {
        private final int setSize;
        private final int tupleLength;
        private final long p;
        private int[] c;
        private long i;

        public CartesianPowerIterator(int setSize, int tupleLength) {
            this.setSize = setSize;
            this.tupleLength = tupleLength;
            this.p = naivePow(setSize, tupleLength);
            c = new int[tupleLength];
        }

        @Override
        public boolean hasNext() {
            return i<p;
        }

        @Override
        public int[] next() {
            long u = i;
            for(int k=tupleLength-1;k>=0;k--) {
                c[k] = (int)(u%setSize);
                u /= setSize;
            }
            i++;
            return c;
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
