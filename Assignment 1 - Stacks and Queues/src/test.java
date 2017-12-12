public class test {
	
	public static boolean isSorted(myqueue x) throws QueueException{
		if (x.isEmpty()){
			return true;
		}
		int y=x.dequeue();
		while (!x.isEmpty()){
			int tmp=x.dequeue();
			if (tmp>=y){
				y=tmp;
			}
			else{
				return false;
			}
		}
		return true;
	}
	
	public boolean isStackSortablePermutation(myqueue input) throws QueueException, StackException {
		myqueue oq=new myqueue();
		mystack s=new mystack();
		while (!input.isEmpty()){
			int element=input.dequeue();
			while (!s.isEmpty() && s.getElementAtTopOfStack()<=element){
				int tm=s.pop();
				oq.enqueue(tm);
			}
			s.push(element);
		}
		while (!s.isEmpty()){
			oq.enqueue(s.pop());
		}
		return isSorted(oq);
		
	}

}
