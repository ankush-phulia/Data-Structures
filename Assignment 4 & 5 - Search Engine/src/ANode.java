public class ANode {
	
	ANode lt_child,rt_child;
	Position p;
	int ht;
	
	public ANode(){
		lt_child=null;
		rt_child=null;
		p=null;
		ht=0;
	}
	
	public ANode(Position pos){
		lt_child=null;
		rt_child=null;
		p=pos;
		ht=1;
	}
	
	public Position getPosition(){
		return p;
	}
	public int ht(){
		return ht;
	}
	
}
