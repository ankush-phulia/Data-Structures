class QueueException extends Exception {
    private static final long serialVersionUID = 2L;

    QueueException(String s) {
        super(s);
    }
}

public class myqueue {

    // implement the stack using an array
    int[] myarray = new int[100];
    int f = 0;
    int r = -1;

    public void enqueue(int element) throws QueueException {
        if (isFull()) {
            throw new QueueException("Full Queue");
        }
        else {
            r++;
            myarray[r % 100] = element;
        }
    }

    public int dequeue() throws QueueException {
        if (isEmpty()) {
            throw new QueueException("Empty Queue");
        }
        else {
            int tmp = myarray[f];
            myarray[f % 100] = 0;
            f++;
            return tmp;
        }
    }

    public int front() throws QueueException {
        if (isEmpty()) {
            throw new QueueException("Empty Queue");
        }
        else {
            return myarray[f];
        }
    }

    public boolean isEmpty() {
        return (f == r + 1);
    }

    public boolean isFull() {
        return !(r == -1) && ((r - f + 1) % 100 == 0);
    }
}
