public class Edge {
	
	Vertex src;
	Vertex des;
	double dist;
	
	public Edge(Vertex a,Vertex b){
		src=a;
		des=b;
	}
	public Edge(Vertex a,Vertex b,double i){
		src=a;
		des=b;
		dist=i;
	}	
	/*public static void main(String[] args){
		int n=30;
		int i=1727;
		while (i<n){
			System.out.println(i);
			int j=1;
			int cnt=0;
			while (j*j*j<i){
				int k=0;
				while (k*k*k<=i-j*j*j){
					if (k*k*k==i-j*j*j){
						cnt++;
					}
					k++;
				}
				j++;
			}
			if (cnt==2){
				System.out.println(i);
			}
			i++;
		}
	}*/

}


