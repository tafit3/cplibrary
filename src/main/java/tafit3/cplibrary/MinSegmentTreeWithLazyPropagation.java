package tafit3.cplibrary;

import static java.lang.Math.min;

public class MinSegmentTreeWithLazyPropagation {
    private int n;
    private long[] a;
    private long[] minValue;
    private long[] lazy;

    public MinSegmentTreeWithLazyPropagation(long[] a) {
        this.a = a;
        n = a.length;
        minValue = new long[4*n];
        lazy = new long[4*n];
        build(1, 0, n-1);
    }

    private void build(int id, int l, int r) {
        if(l == r) {
            minValue[id] = a[l];
            return;
        }
        int m = (l+r)/2;
        build(id*2, l, m);
        build(id*2+1, m+1, r);
        minValue[id] = min(minValue[id*2], minValue[id*2+1]);
    }

    private void upd(int id, int l, int r, long x) {
        minValue[id] += x;
        lazy[id] += x;
    }

    private void shift(int id, int l, int r) {
        int m = (l+r)/2;
        upd(id*2,l,m,lazy[id]);
        upd(id*2+1,m+1,r,lazy[id]);
        lazy[id] = 0;
    }

    public void add(int from, int to, long x) {
        add(from, to, x, 1, 0, n-1);
    }

    private void add(int from, int to, long x, int id, int l, int r) {
        if(from > r || to < l) {
            return;
        }
        if(from <= l && to >= r) {
            upd(id, l, r, x);
            return;
        }
        shift(id, l, r);
        int m = (l+r)/2;
        add(from, to, x, id*2, l, m);
        add(from, to, x, id*2 + 1, m+1, r);
        minValue[id] = min(minValue[id*2], minValue[id*2+1]);
    }

    public long getMin(int from, int to) {
        return getMin(from, to, 1, 0, n-1);
    }

    private long getMin(int from, int to, int id, int l, int r) {
        if(from > r || to < l) {
            return Long.MAX_VALUE;
        }
        if(from <= l && to >= r) {
            return minValue[id];
        }
        shift(id, l, r);
        int m = (l+r)/2;
        return min(getMin(from, to, id*2, l, m), getMin(from, to, id*2+1, m+1, r));
    }
}
