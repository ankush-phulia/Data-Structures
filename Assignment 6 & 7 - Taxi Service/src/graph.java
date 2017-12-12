import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;

public class graph {

	HashMap<Vertex,ArrayList<Vertex>> h =new HashMap<Vertex,ArrayList<Vertex>>();
	ArrayList<Edge> edges=new ArrayList<Edge>();
		
	public Vertex getVertex(String a){
		Iterator<Vertex> it=h.keySet().iterator();
		while (it.hasNext()){
			Vertex v=it.next();
			if (v.name.equals(a)){
				return v;
			}
		}
		return null;
	}
	
	public Edge getEdge(Vertex a,Vertex b){
		if (a.equals(b)){
			return new Edge(a,a,0.0);
		}
		else{
			for (int i=0;i<edges.size();i++){
				if (edges.get(i).src.equals(a) && edges.get(i).des.equals(b)){
					return edges.get(i);
				}
			}
			return null;
		}		
	}
	
	public void addEdge(String a,String b,double i){
		if (getVertex(a)!=null){
			if (getVertex(b)!=null){
				Vertex a1=this.getVertex(a);
				Vertex b1=this.getVertex(b);
				Edge e=new Edge(a1,b1,i);
				Edge e1=new Edge(b1,a1,i);
				edges.add(e);
				edges.add(e1);
				h.get(a1).add(b1);
				h.get(b1).add(a1);
			}
			else{
				Vertex a1=this.getVertex(a);
				Vertex b1=new Vertex(b,h.keySet().size());
				Edge e=new Edge(a1,b1,i);
				Edge e1=new Edge(b1,a1,i);
				edges.add(e);
				edges.add(e1);
				h.get(a1).add(b1);
				ArrayList<Vertex> ed=new ArrayList<Vertex>();
				ed.add(a1);
				h.put(b1,ed);
			}
		}
		else{
			if (getVertex(b)!=null){
				Vertex b1=this.getVertex(b);
				Vertex a1=new Vertex(a,h.keySet().size());
				Edge e=new Edge(a1,b1,i);
				Edge e1=new Edge(b1,a1,i);
				edges.add(e);
				edges.add(e1);
				h.get(b1).add(a1);
				ArrayList<Vertex> ed=new ArrayList<Vertex>();
				ed.add(b1);
				h.put(a1,ed);
			}
			else{
				Vertex a1=new Vertex(a,h.keySet().size());
				Vertex b1=new Vertex(b,h.keySet().size()+1);
				Edge e=new Edge(a1,b1,i);
				Edge e1=new Edge(b1,a1,i);
				edges.add(e);
				edges.add(e1);
				ArrayList<Vertex> ed=new ArrayList<Vertex>();
				ed.add(b1);
				ArrayList<Vertex> ed1=new ArrayList<Vertex>();
				ed1.add(a1);
				h.put(a1,ed);
				h.put(b1,ed1);
			}
		}
	}
	
	public void cVertex(int cnt,Vertex u,boolean[] visited,int[] disc,int[] low, Vertex[] parent,ArrayList<Vertex> cvs){
		int children=0;
		visited[u.id]=true;
		disc[u.id]=low[u.id]=cnt;
		ArrayList<Vertex> adj=h.get(u);
		for (int i=0;i<adj.size();i++){
			Vertex v=adj.get(i);
			if (!visited[v.id]){
				children++;
				parent[v.id]=u;
				cVertex(cnt+1,v,visited,disc,low,parent,cvs);
				low[u.id]=Integer.min(low[u.id],low[v.id]);
				if (parent[u.id]==null && children>1){
					cvs.add(u);
				}
				if (parent[u.id] != null && low[v.id] >= disc[u.id])
					cvs.add(u);
			}
			else if (v.id!=parent[u.id].id){
				low[u.id] = Integer.min(low[u.id], disc[v.id]);
			}
		}
	}
	
	public ArrayList<Vertex> cutVertex(){
		int x=h.keySet().size();
		boolean[] visited = new boolean[x];
		int[] disc=new int[x];
		int[] low=new int[x];
		Vertex[] parent=new Vertex[x];
		ArrayList<Vertex> cv=new ArrayList<Vertex>();
		
		for (int i=0;i<x;i++){
			parent[i]=null;
			visited[i]=false;
			disc[i]=-1;
			low[i]=-1;
		}
		int cnt=0;
		Iterator<Vertex> it=h.keySet().iterator();
		while (it.hasNext()){
			Vertex vv=it.next();
			if (!visited[vv.id]){
				cVertex(cnt,vv,visited,disc,low,parent,cv);
			}
		}		
		return cv;
		
	}
	
	public void cyc(int cnt,Vertex u, int disc[], int low[], Stack<Integer> st,boolean[] member){
		disc[u.id] = low[u.id] = cnt;
	    st.push(u.id);
	    member[u.id] = true;
	    ArrayList<Vertex> adj=h.get(u);
		for (int i=0;i<adj.size();i++){
			Vertex v=adj.get(i);
			if (disc[v.id] == -1){
	            cyc(cnt+1,v, disc, low, st, member);
	            low[u.id]  = Integer.min(low[u.id], low[v.id]);
	        }
			else if (member[v.id] == true){
	            low[u.id]  = Integer.min(low[u.id], disc[v.id]);
			}
		}
	    
	    
	}
	
	public Path shortest(String a,String b){
		
		int a1=this.getVertex(a).id;
		int b1=this.getVertex(b).id;
		int x=h.keySet().size();
		
	    ArrayList<Vertex> tmp=new ArrayList<Vertex>();
	    Iterator<Vertex> it=h.keySet().iterator();
	    while(it.hasNext()){
	    	tmp.add(it.next());
	    }
	    Collections.sort(tmp);
	    
	    PriorityQueue<Node> pq=new PriorityQueue<Node>();
	    
		double[] dist=new double[x];
		Vertex[] prev=new Vertex[x];
		
		for(int i = 0; i<x; i++){
			if (i!=a1){
		        dist[i] = Double.POSITIVE_INFINITY;
			}
			else{
				dist[i]=0.0;
			}
			pq.add(new Node(tmp.get(i),dist[i]));
	    }
		
	    while (!pq.isEmpty()){
	    	Vertex v=pq.remove().pt;
	    	ArrayList<Vertex> vr=h.get(v);
	    	for (int i=0;i<vr.size();i++){
	    		Vertex v1=vr.get(i);
	    		double d=dist[v.id]+getEdge(v,v1).dist;
	    		if (d<dist[v1.id]){
	    			Node n=new Node(v1,dist[v1.id]);
	    			dist[v1.id]=d;
	    			prev[v1.id]=v;
	    			pq.remove(n);
	    			Node m=new Node(v1,dist[v1.id]);
	    			pq.add(m);
	    		}
	    	}
	    }
	    Path p=new Path(x);
	    p.dist=dist;
	    p.prev=prev;
	    p.ss=b1;
	    return p;	    
	}
	
	public static void main(String[] args) {
		graph g=new graph();
		g.addEdge("a", "b",1);
		g.addEdge("a", "c",2);	
		g.addEdge("b", "d", 1);
		g.addEdge("d", "g",2);
		//g.addEdge("c", "f",2);
		//g.addEdge("c", "d",2);
		//g.addEdge("a", "d",2.5);
		//g.addEdge("d", "e", 1);

		//System.out.println(g.h.get(v).get(1).name);
		System.out.println(g.shortest("a","d"));
		ArrayList<Vertex> vv=(g.cutVertex());
		for (int i=0;i<vv.size();i++){
			System.out.println(vv.get(i).name);
		}
	}
}
