public class MySet<T> {

	MyLinkedList<T> l =new MyLinkedList<T>();
	
	public int size(){
		return l.size();
	}
	
	public boolean isEmpty(){
		return (this.l.size()==0);
	}
	
	public boolean isMember(T o){
		return (l.search(o));
	}
	
	public void insert(T o){
		this.l.addf(o);
	}
	public void delete(T o){
		l.delete(o);
	}
	
	public MySet<T> union(MySet<T> a){
		this.l.union(a.l);
		return this;		
	}
	
	public MySet<T> intersection(MySet<T> a){
		MySet<T> n=new MySet<T>();
		n.l=this.l.intersection(a.l);
		return n;
	}
	
	/*public static void main(String[] args){
		MySet<Integer> a=new MySet<Integer>();
		MySet<Integer> b=new MySet<Integer>();
		b.insert(1);
		b.insert(2);
		b.insert(3);
		b.insert(4);
		a.delete(3);
		a.insert(1);
		a.insert(1);
		a.insert(5);
		System.out.println(b.size());
		System.out.println(a.size());
		System.out.println(a.intersection(b).size());
		System.out.println(a.intersection(a).size());
		System.out.println(b.intersection(a).size());
	}*/	
}
