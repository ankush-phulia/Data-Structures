public class DNode<T> {

	T element;
	DNode<T> next;
	DNode<T> prev;
	
	public DNode(){
		element=null;
		next=null;
		prev=null;
	}
	public DNode(T e,DNode<T> p,DNode<T> n){
		element =e;
		next=n;
		prev=p;
		n.setPrev(this);
		p.setNext(this);
	}
	
	public T getElement(){
		return element;
	}
	public DNode<T> getNext(){
		return next;
	}
	public DNode<T> getPrev(){
		return prev;
	}
	
	public void setElement(T newElement){
		element=newElement;
	}
	public void setNext(DNode<T> newNext){
		next=newNext;
		newNext.prev=this;
	}
	public void setPrev(DNode<T> newPrev){
		prev=newPrev;
		newPrev.next=this;
	}
	
}
