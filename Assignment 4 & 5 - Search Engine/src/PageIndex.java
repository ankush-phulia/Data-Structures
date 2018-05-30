public class PageIndex {

    MyLinkedList<WordEntry> wes = new MyLinkedList<WordEntry>();
    AVLTree voc = new AVLTree();

    public DNode<WordEntry> wget_data(String w) {
        DNode<WordEntry> a = (DNode<WordEntry>) wes.head.getNext();
        while (a != wes.tail) {
            if (a.element.word.equals(w)) {
                return a;
            } 
            else {
                a = a.getNext();
            }
        }
        return a;
    }

    public boolean wsearch(String w) {
        if (wes.isEmpty()) {
            return false;
        } 
        else {
            DNode<WordEntry> a = (DNode<WordEntry>) wes.head;
            while (!a.next.equals(wes.tail)) {
                DNode<WordEntry> b = a.getNext();
                if (b.element.word.equals(w)) {
                    return true;
                } 
                else {
                    a = b;
                }
            }
            return false;
        }
    }

    public void addPositionForWord(String str, Position p) {
        boolean chk = false;
        for (int i = 0; i < wes.size(); i++) {
            WordEntry a = wes.get(i).element;
            if (a.word.equals(str)) {
                a.pos.addl(p);
                Position p1 = p;
                p.wor = str;
                a.po.insert(p1);
                chk = true;
                break;
            }
        }
        if (!chk) {
            WordEntry s = new WordEntry(str);
            s.pos.addl(p);
            Position p1 = p;
            p.wor = str;
            s.po.insert(p1);
            wes.addl(s);
        }
    }
}
