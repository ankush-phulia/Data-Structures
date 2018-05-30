import java.util.LinkedList;

public class ExchangeList {

    LinkedList<Exchange> l;
    Exchange par;

    public ExchangeList(Exchange parent) {
        l = new LinkedList<Exchange>();
        par = parent;
    }

    public void add(Exchange e) {
        e.parent = par;
        par.numChildren++;
        l.add(e);
    }

    public void rem(Exchange e) {
        par.numChildren--;
        l.remove(e);
    }

}
