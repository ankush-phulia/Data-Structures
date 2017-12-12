class ListException extends Exception{
	private static final long serialVersionUID = 1L;

	ListException(String s){
		super(s);
	}
}

public class MyLinkedList<T> {
	
	DNode<T> head;
	DNode<T> tail;
	int size;
	
	public MyLinkedList(){
		head=new DNode<T>();
		tail=new DNode<T>();
		head.setNext(tail);
		tail.setPrev(head);
		size=0;
	}
	
	public boolean isEmpty(){
		return (size==0);
	}
	
	public int size(){
		return size;
	}
	
	public DNode<T> get(int i){
		DNode<T> a=this.head.getNext();
		if (i<size){
			for (int j=0;j<i;j++){
				a=a.getNext();
			}
			return a;
		}
		else return tail;
	}
	
	public DNode<T> get_data(T o){
		DNode<T> a=this.head.getNext();
		while (a!=tail){
			if (a.element.equals(o)){
				return a;
			}
			else{
				a=a.getNext();
			}
		}
		return a;
	}
	
	public void addf(T o){
		if (!this.search(o)){
			DNode<T> d=new DNode<T>(o,head,head.getNext());
			size++;
		}
	}
	public void addl(T o){
		if (!this.search(o)){
			DNode<T> d=new DNode<T>(o,tail.getPrev(),tail);
			size++;
		}
	}

	public void delete(T o){
		if (isEmpty()){
			System.out.println("Can't Delete from any Empty List/Set");
		}
		else{
			DNode<T> a=this.head;
			boolean chk=false;
			while(!a.next.equals(tail)){
				DNode<T> b=a.getNext();
				if (b.element!=o){
					a=b;
				}
				else{
					DNode<T> c=b.getNext();
					a.setNext(c);
					size--;
					chk=true;
					break;
				}
			}
			if (!chk){
				System.out.println("Element not Found");
			}
		}
	}
	
	public boolean search(T o){
		if (isEmpty()){
			return false;
		}
		else{
			DNode<T> a=this.head;
			while(!a.next.equals(tail)){
				DNode<T> b=a.getNext();
				if (b.element==o){
					return true;
				}
				else{
					a=b;
				}
			}
			return false;
		}
	}
	
	public MyLinkedList<T> union(MyLinkedList<T> l){
		if (this.isEmpty()){
			return l;
		}
		else{
			DNode<T> a=l.head;
			while(!a.next.equals(l.tail)){
				DNode<T> b=a.getNext();
				if (!search(b.element)){
					this.addl(b.element);
					a=b;
				}
				else{
					a=b;
				}
			}
			return this;
		}
	}
	
	
	
	public MyLinkedList<T> intersection(MyLinkedList<T> l){
		MyLinkedList<T> n=new MyLinkedList<T>();
		if (this.isEmpty()){
			return n;
		}
		else{
			DNode<T> a=l.head;
			while(!a.next.equals(l.tail)){
				DNode<T> b=a.getNext();
				if (search(b.element)){
					n.addl(b.element);
					a=b;
				}
				else{
					a=b;
				}
			}
			return n;
		}
	}
	
	public DNode<WordEntry> wget_data(String w){
		DNode<WordEntry> a=(DNode<WordEntry>) this.head.getNext();
		while (a!=this.tail){
			if (a.element.word.equals(w)){
				return a;
			}
			else{
				a=a.getNext();
			}
		}
		return a;
	}
	
	public boolean wsearch(String w){
		if (this.isEmpty()){
			return false;
		}
		else{
			DNode<WordEntry> a=(DNode<WordEntry>) this.head;
			while(!a.next.equals(this.tail)){
				DNode<WordEntry> b=a.getNext();
				if (b.element.word.equals(w)){
					return true;
				}
				else{
					a=b;
				}
			}
			return false;
		}
	}
/*	
	public static void main(String[] args) {
		MyLinkedList<Integer> l=new MyLinkedList<Integer>();
		l.addf(2);
		l.addl(3);
		l.addf(1);
		MyLinkedList<Integer> m=new MyLinkedList<Integer>();
		m.addf(1);
		System.out.println(l.search(1));
		System.out.println(m.intersection(l).size());
		System.out.println(m.get_data(1).element);
	}*/

}
