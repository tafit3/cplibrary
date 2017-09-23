package tafit3.cplibrary;

public class SegmentTreeWithLazyPropagation {
    private int n;
    private long[] a;
    private long[] sum;
    private long[] lazy;

    public SegmentTreeWithLazyPropagation(long[] a) {
        this.a = a;
        n = a.length;
        sum = new long[4*n];
        lazy = new long[4*n];
        build(1, 0, n-1);
    }

    private void build(int id, int l, int r) {
        if(l == r) {
            sum[id] = a[l];
            return;
        }
        int m = (l+r)/2;
        build(id*2, l, m);
        build(id*2+1, m+1, r);
        sum[id] = sum[id*2] + sum[id*2+1];
    }

    private void upd(int id, int l, int r, long x) {
        sum[id] += (r-l+1)*x;
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
        sum[id] = sum[id*2] + sum[id*2+1];
    }

    public long sum(int from, int to) {
        return sum(from, to, 1, 0, n-1);
    }

    private long sum(int from, int to, int id, int l, int r) {
        if(from > r || to < l) {
            return 0;
        }
        if(from <= l && to >= r) {
            return sum[id];
        }
        shift(id, l, r);
        int m = (l+r)/2;
        return sum(from, to, id*2, l, m) + sum(from, to, id*2+1, m+1, r);
    }
}
