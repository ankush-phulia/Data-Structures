class StackException extends Exception{
	private static final long serialVersionUID = 1L;

	StackException(String s){
		super(s);
	}
}

public class mystack {

    // implement the stack using an array
	int[] myarray=new int[100];
	// declare additional variables here
	int t=-1;

	public void push(int element) throws StackException {
		if (isFull()){
			throw new StackException("Full Stack");
		}
		else{
			t++;
			myarray[t]=element;
		}
	}

	public int pop() throws StackException {
		if (isEmpty()){
			throw new StackException("Empty Stack");
		}
		else{
			int tmp=myarray[t];
			myarray[t]=0;
			t--;
			return tmp;
		}
	}

	public boolean isEmpty() {
		return (t==-1);
	}
	
	public boolean isFull() {
		return (t==99);
	}
	
//	public int size() {
//		return t+1;
//	}

	public int getElementAtTopOfStack() throws StackException {
		if (isEmpty()){
			throw new StackException("Empty Stack");
		}
		else{
			return myarray[t];
		}
	}
}
