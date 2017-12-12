import java.util.Iterator;
import java.util.LinkedList;

class SetException extends Exception{
	private static final long serialVersionUID = 1L;

	SetException(String s){
		super(s);
	}
}

public class MySet<T> {

	LinkedList<T> l =new LinkedList<T>();
	
	public boolean isEmpty(){
		return (this.l.size()==0);
	}
	
	public boolean isMember(T o){
		Iterator<T> it=this.l.iterator();
		while (it.hasNext()){
			T obj=it.next();
			if (o.equals(obj)){
				return true;
			}
		}
		return false;
	}
	
	public void insert(T o){
		if (!isMember(o)){
			this.l.add(o);
		}
	}
	public void delete(T o) throws SetException{
		if (this.isEmpty()){
			throw new SetException("Empty Set");
		}
		else{
			Iterator<T> it=this.l.iterator();
			while (it.hasNext()){
				T obj=it.next();
				if (o.equals(obj)){
					it.remove();
				}
			}
			throw new SetException("Element not found");
		}
	}
	
	public MySet<T> union(MySet<T> a){
		MySet<T> u=new MySet<T>();
		u=this;
		Iterator<T> it=a.l.iterator();
		while (it.hasNext()){
			T obj=it.next();
			if (!u.isMember(obj)){
				u.insert(obj);
			}
		}
		return u;
	}
	public MySet<T> interection(MySet<T> a){
		MySet<T> u=new MySet<T>();
		Iterator<T> it=a.l.iterator();
		while (it.hasNext()){
			T obj=it.next();
			if (u.isMember(obj)){
				u.insert(obj);
			}
		}
		return u;
	}
	
/*	public static void main(String[] args) throws SetException{
		MySet<Integer> a=new MySet<Integer>();
		MySet<Integer> b=new MySet<Integer>();
		b.delete(2);
		System.out.println(b.l);
	}*/	
}
