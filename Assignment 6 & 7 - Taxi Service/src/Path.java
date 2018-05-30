public class Path {

    int l;
    Vertex[] prev;
    double[] dist;
    int ss;

    public Path(int x) {
        l = x;
        prev = new Vertex[x];
        dist = new double[x];
    }
}
