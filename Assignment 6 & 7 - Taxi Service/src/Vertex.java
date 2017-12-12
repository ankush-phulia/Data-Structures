public class Vertex implements Comparable<Vertex>{
	
	int id;
	String name;
	
	public Vertex(String n,int i){
		name=n;
		id=i;
	}

	@Override
	public int compareTo(Vertex v) {
		if (id>v.id){
			return 1;
		}
		else if (id<v.id){
			return -1;
		}
		else return 0;
	}

}
