public class taxi {

    Vertex taxiPosition;
    String taxiName;
    int time;
    boolean av;

    public taxi(String a, Vertex b) {
        taxiPosition = b;
        taxiName = a;
        av = true;
        time = 0;
    }
}
