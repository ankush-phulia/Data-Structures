import java.util.ArrayList;

public class TaxiService {

    graph map = new graph();
    ArrayList<taxi> taxis = new ArrayList<taxi>();

    public void addTaxi(String a, String b) {
        if (map.getVertex(b) != null) {
            Vertex v = map.getVertex(b);
            taxi t = new taxi(a, v);
            taxis.add(t);
        } 
        else {
            System.out.println("Location doesn't exist on the map");
        }
    }

    public void av_taxi(int tim) {
        for (int i = 0; i < taxis.size(); i++) {
            taxi t = taxis.get(i);
            if (t.time <= tim) {
                System.out.println(t.taxiName + ": " + t.taxiPosition.name);
            }
        }
    }

    public void performAction(String actionMessage) {
        System.out.println("action to be performed: " + actionMessage);
        String[] str = actionMessage.split(" ");
        if (str[0].equals("edge")) {
            map.addEdge(str[1], str[2], Integer.parseInt(str[3]));
        } 
        else if (str[0].equals("taxi")) {
            this.addTaxi(str[1], str[2]);
        }
        else if (str[0].equals("printTaxiPosition")) {
            av_taxi(Integer.parseInt(str[1]));
        } 
        else if (str[0].equals("customer")) {
            double[] dist = new double[taxis.size()];
            int tt = Integer.parseInt(str[3]);
            System.out.println("Availible Taxis");
            
            for (int i = 0; i < taxis.size(); i++) {
                if (taxis.get(i).time <= tt) {
                    Path p = map.shortest(taxis.get(i).taxiPosition.name, str[1]);
                    dist[i] = p.dist[p.ss];
                    System.out.print("Path of " + taxis.get(i).taxiName + ": ");
                    
                    Vertex v = p.prev[p.ss];
                    ArrayList<String> sstr = new ArrayList<String>();
                    while (v != null) {
                        sstr.add(v.name);
                        v = p.prev[v.id];
                    }
                    ArrayList<String> ssstr = new ArrayList<String>();
                    for (int k = 0; k < sstr.size(); k++) {
                        ssstr.add(0, sstr.get(k));
                    }
                    ssstr.add(str[1]);
                    
                    System.out.print(ssstr);
                    System.out.print(" Time taken is : " + p.dist[p.ss]);
                    System.out.println();
                } 
                else {
                    dist[i] = Double.POSITIVE_INFINITY;
                }
            }
            double min = dist[0];
            int mind = 0;
            for (int j = 1; j < dist.length; j++) {
                if (dist[j] < min) {
                    min = dist[j];
                    mind = j;
                }
            }
            if (min == Double.POSITIVE_INFINITY) {
                System.out.println("No Taxi Availible");
            } 
            else {
                taxi ride = taxis.get(mind);
                System.out.println("**" + ride.taxiName + " chosen to service customer**");
                
                Vertex vv = ride.taxiPosition;
                ride.taxiPosition = map.getVertex(str[2]);
                Path pp = map.shortest(str[1], str[2]);
                Path ppp = map.shortest(vv.name, str[1]);
                taxis.get(mind).time = tt + (int) ((pp).dist[pp.ss] + (ppp).dist[ppp.ss]);
                System.out.print("Path of " + ride.taxiName + ": ");
                
                Vertex v = pp.prev[pp.ss];
                ArrayList<String> sstr = new ArrayList<String>();
                while (v != null) {
                    sstr.add(v.name);
                    v = pp.prev[v.id];
                }
                ArrayList<String> ssstr = new ArrayList<String>();
                for (int k = 0; k < sstr.size(); k++) {
                    ssstr.add(0, sstr.get(k));
                }
                ssstr.add(str[2]);
                
                System.out.print(ssstr);
                System.out.print(" Time taken is : " + pp.dist[pp.ss]);
                System.out.println();
            }
        } 
        else {
            System.out.println("Invalid Command");
        }
    }
}
