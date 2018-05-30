class ExchangeException extends Exception {
    private static final long serialVersionUID = 5L;

    ExchangeException(String s) {
        super(s);
    }
}

public class Exchange {

    public int numChildren;
    public Exchange parent;
    public MobilePhoneSet mobset;
    public ExchangeList children;
    public int id;

    public Exchange(int i) {
        // TODO Auto-generated constructor stub
    }

    public Exchange(int i, Exchange e1) {
        // TODO Auto-generated constructor stub
    }

    public Exchange parent() {
        // TODO Auto-generated method stub
        return null;
    }

    public int numChildren() {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean containsPhone(int a) {
        // TODO Auto-generated method stub
        return false;
    }

    public MobilePhone getPhone(int a) {
        // TODO Auto-generated method stub
        return null;
    }

    public Exchange child(int i) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isRoot() {
        // TODO Auto-generated method stub
        return false;
    }
}
