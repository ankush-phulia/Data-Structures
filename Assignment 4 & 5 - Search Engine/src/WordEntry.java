import java.util.ArrayList;

public class WordEntry {
	
	MyLinkedList<Position> pos;
	String word;
	AVLTree po;
	
/*	public boolean psearch(int w){
		if (pos.isEmpty()){
			return false;
		}
		else{
			DNode<Position> a=(DNode<Position>) pos.head;
			while(!a.next.equals(pos.tail)){
				DNode<Position> b=a.getNext();
				if (b.element.wordIndex==w){
					return true;
				}
				else{
					a=b;
				}
			}
			return false;
		}
	}*/
	
	public boolean psearch(Position w){
		if (po.search(w)==null){
			return false;
		}
		else{
			return true;
		}
	}
	
/*	public MyLinkedList<Position> punion(MyLinkedList<Position> l){
		if (pos.isEmpty()){
			return l;
		}
		else{
			DNode<Position> a=l.head;
			while(!a.next.equals(l.tail)){
				DNode<Position> b=a.getNext();
				if (!psearch(b.element.wordIndex)){
					pos.addl(b.element);
					a=b;
				}
				else{
					a=b;
				}
			}
			return pos;
		}
	}*/
	
	public AVLTree punion(MyLinkedList<Position> l){
		for (int i=0;i<l.size;i++){
			addPosition(l.get(i).element);
		}
		return po;
	}
	
	public AVLTree punion(AVLTree tr){
		ArrayList<Position> l=tr.traverse();
		for (int i=0;i<l.size();i++){
			addPosition(l.get(i));
		}
		return po;
	}
	
	public WordEntry(String w){
		word=w;
		pos=new MyLinkedList<Position>();
		po=new AVLTree();
	}
	
/*	public void addPosition(Position posi){
		if (!this.psearch(posi.wordIndex)){
			pos.addl(posi);
		}		
	}*/
	
	public void addPosition(Position posi){
		if (!psearch(posi)){
			po.insert(posi);
		}
	}
	
	public double getrel(){
		double r=0.0;
		for (int i=0;i<this.pos.size;i++){
			int k=pos.get(i).element.wordIndex+1;
			r=r+1.0/k/k;
		}
		return r;
	}
	
	/*public void addPositions(MyLinkedList<Position> posi){
		this.punion(posi);
	}*/
	
	public void addPositions(MyLinkedList<Position> posi){
		this.punion(posi);
	}
	
	public void addPositions(AVLTree tr){
		this.punion(tr);
	}
	
/*	public MyLinkedList<Position> getAllPositionsForThisWord(){
		return pos;
	}
	*/
	public AVLTree getAllPositionsForThisWord(){
		return po;
	}

}
