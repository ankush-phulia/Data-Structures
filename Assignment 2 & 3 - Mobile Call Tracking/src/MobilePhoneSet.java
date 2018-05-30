public class MobilePhoneSet {

    Exchange par;
    MySet<MobilePhone> a;

    public MobilePhoneSet(Exchange p) {
        par = p;
        a = new MySet<MobilePhone>();
    }

    public void insert(MobilePhone m) {
        a.insert(m);
    }

    public void delete(MobilePhone m) throws SetException {
        a.delete(m);
    }
}
