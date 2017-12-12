import java.util.ArrayList;

public class AVLTree {

	ANode root;

	public AVLTree(){
		root=null;
	}
	
	public AVLTree(ANode a){
		root=a;
	}
	
	public boolean isEmpty(){
		return (root==null);
	}
	
	public int ht(ANode r){
		if (r==null){
			return 0;
		}
		else return r.ht;
	}
	
	public ANode insert(Position p,ANode r){
		if (r==null){
			r=new ANode(p);
		}
		else{
			if (p.wordIndex<(r.p.wordIndex)){
				r.lt_child=insert(p,r.lt_child);
				if (ht(r.lt_child)-ht(r.rt_child)==2){
					if (p.wordIndex<r.lt_child.p.wordIndex){
						r=lt_rot(r);
					}
					else{
						r.lt_child=rt_rot(r.lt_child);
						r=lt_rot(r);
					}
				}
			}
			else if (p.wordIndex>(r.p.wordIndex)){
				r.rt_child=insert(p,r.rt_child);
				if (ht(r.rt_child)-ht(r.lt_child)==2){
					if (p.wordIndex>r.rt_child.p.wordIndex){
						r=rt_rot(r);
					}
					else{
						r.rt_child=lt_rot(r.rt_child);
						r=rt_rot(r);
					}
				}
			}			
		}
		r.ht=Integer.max(ht(r.rt_child), ht(r.lt_child))+1;
		return r;
	}
	
	public ANode lt_rot(ANode r){
		ANode lch=r.lt_child;
		r.lt_child=lch.rt_child;
		lch.rt_child=r;
		r.ht=Integer.max(ht(r.lt_child),ht(r.rt_child))+1;
		lch.ht=Integer.max(ht(lch.lt_child),ht(lch.rt_child))+1;
		return lch;
	}
	
	public ANode rt_rot(ANode r){
		ANode rch=r.rt_child;
		r.rt_child=rch.lt_child;
		rch.lt_child=r;
		r.ht=Integer.max(ht(r.lt_child),ht(r.rt_child))+1;
		rch.ht=Integer.max(ht(rch.lt_child),ht(rch.rt_child))+1;
		return rch;
	}
	
	public void insert(Position p){
		if (search(p)==null){
			this.root=this.insert(p,this.root);
		}
	}
    
	public ANode search(Position p){
		if (isEmpty()){
			return null;
		}
		else{
			if (root.p.wordIndex==p.wordIndex){
				return root;
			}
			else if (p.wordIndex<root.p.wordIndex){
				AVLTree x=new AVLTree(root.lt_child);
				return x.search(p);
			}
			else{
				AVLTree x=new AVLTree(root.rt_child);
				return x.search(p);
			}
		}
	}
	
	public ArrayList<Position> traverse(){
		ArrayList<Position> travel=new ArrayList<Position>();
		if (this.isEmpty()){
			return travel;
		 }
		if (root.lt_child==null && root.rt_child==null){
			travel.add(root.p);
		}
		else{
			AVLTree lt_tree=new AVLTree(root.lt_child);
			AVLTree rt_tree=new AVLTree(root.rt_child);
			travel.addAll(lt_tree.traverse());
			travel.add(root.p);
			travel.addAll(rt_tree.traverse());
		}
		return travel;
	}
	
	public ANode max(){
		if (isEmpty()){
			return root;
		}
		else if (root.rt_child==null){
			return root;
		}
		else {
			AVLTree ne=new AVLTree(root.rt_child);
			return ne.max();
		}
	}
	
	public ANode min(){
		if (isEmpty()){
			return root;
		}
		else if (root.lt_child==null){
			return root;
		}
		else {
			AVLTree ne=new AVLTree(root.lt_child);
			return ne.min();
		}
	}
	
	public Position succ(int p){
		ArrayList<Position> a=this.traverse();
		for (int i=0;i<a.size();i++){
			if (a.get(i).wordIndex==(p)){
				return a.get(i+1);
			}
		}
		return null;
	}
	
	/*public static void main(String[] args) throws FileNotFoundException {
		PageEntry p1=new PageEntry("stack_cprogramming");
		PageEntry p2=new PageEntry("stack_oracle");
		PageEntry p3=new PageEntry("stack_datastructure_wiki");
		PageEntry p4=new PageEntry("stacklighting");
		PageEntry p5=new PageEntry("stackmagazine");
		PageEntry p6=new PageEntry("stackoverflow");
		Position po1=new Position(p1,1);
		Position po2=new Position(p1,2);
		Position po3=new Position(p1,3);
		Position po4=new Position(p1,4);
		Position po5=new Position(p1,5);
		Position po6=new Position(p1,6);
		Position po7=new Position(p1,7);
		Position po8=new Position(p1,8);
		Position po9=new Position(p1,9);
		Position po10=new Position(p1,10);
		Position po11=new Position(p1,11);
		Position po12=new Position(p1,12);
		Position po13=new Position(p1,13);
		Position po14=new Position(p1,14);
		Position po15=new Position(p1,15);
		Position po16=new Position(p1,16);
		AVLTree at=new AVLTree();
		at.insert(po3);
		at.insert(po1);
		at.insert(po2);
		at.insert(po5);
		at.insert(po4);
		at.insert(po6);
		at.insert(po7);
		at.insert(po8);
		at.insert(po9);
		at.insert(po10);
		at.insert(po11);
		at.insert(po12);
		at.insert(po13);
		at.insert(po14);
		at.insert(po15);
		System.out.println(at.root.ht);
		System.out.println(at.search(po16));
	}*/

}
