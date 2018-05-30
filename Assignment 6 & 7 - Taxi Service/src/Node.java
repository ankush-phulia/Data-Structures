public class Node implements Comparable<Node> {

    public Vertex pt;
    public double priority;

    public Node(Vertex v, double i) {
        pt = v;
        priority = i;
    }

    @Override
    public int compareTo(Node v) {
        if (priority > v.priority) {
            return 1;
        } 
        else if (priority < v.priority) {
            return -1;
        } 
        else return 0;
    }
}
