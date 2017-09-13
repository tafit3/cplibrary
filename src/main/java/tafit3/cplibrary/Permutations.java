package tafit3.cplibrary;

import java.util.Iterator;

public class Permutations {

    public static class PermutationsIterator implements Iterator<int[]> {
        private final boolean[] d;
        private final int n;
        private int[][] p;
        private int act;
        private boolean nextAvail;

        public PermutationsIterator(int n) {
            d = new boolean[n];
            this.n = n;
            p = new int[2][n];
            for(int i=0;i<n;i++) {
                p[0][i] = i;
            }
            nextAvail = true;
        }

        @Override
        public boolean hasNext() {
            return nextAvail;
        }

        private void computeNext() {
            int y = n-1;
            for(int i=0;i<n;i++) d[i]=false;
            d[p[act][y]] = true;
            while(y > 0 && p[act][y] < p[act][y-1]) {
                y--;
                d[p[act][y]] = true;
            }
            if(y == 0) {
                nextAvail = false;
            } else {
                d[p[act][y-1]] = true;
                if(y > 1) {
                    System.arraycopy(p[act],0,p[1-act],0,y-1);
                }
                for(int j=p[act][y-1]+1;j<n;j++) {
                    if(d[j]) {
                        p[1-act][y-1] = j;
                        d[j]=false;
                        break;
                    }
                }

                for(int j=0;j<n;j++) {
                    if(d[j]) {
                        p[1-act][y] = j;
                        y++;
                        if(y == n) {
                            break;
                        }
                    }
                }
            }
        }

        @Override
        public int[] next() {
            computeNext();
            act = 1-act;
            return p[1-act];
        }

        @Override
        public void remove() {}
    }

}
