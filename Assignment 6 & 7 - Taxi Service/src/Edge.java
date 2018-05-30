public class Edge {

    Vertex src;
    Vertex des;
    double dist;

    public Edge(Vertex a, Vertex b) {
        src = a;
        des = b;
    }

    public Edge(Vertex a, Vertex b, double i) {
        src = a;
        des = b;
        dist = i;
    }

}
