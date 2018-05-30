import java.util.ArrayList;

public class WordEntry {

    MyLinkedList<Position> pos;
    String word;
    AVLTree po;


    public boolean psearch(Position w) {
        if (po.search(w) == null) {
            return false;
        } 
        else {
            return true;
        }
    }

    public AVLTree punion(MyLinkedList<Position> l) {
        for (int i = 0; i < l.size; i++) {
            addPosition(l.get(i).element);
        }
        return po;
    }

    public AVLTree punion(AVLTree tr) {
        ArrayList<Position> l = tr.traverse();
        for (int i = 0; i < l.size(); i++) {
            addPosition(l.get(i));
        }
        return po;
    }

    public WordEntry(String w) {
        word = w;
        pos = new MyLinkedList<Position>();
        po = new AVLTree();
    }

    public void addPosition(Position posi) {
        if (!psearch(posi)) {
            po.insert(posi);
        }
    }

    public double getrel() {
        double r = 0.0;
        for (int i = 0; i < this.pos.size; i++) {
            int k = pos.get(i).element.wordIndex + 1;
            r = r + 1.0 / k / k;
        }
        return r;
    }

    public void addPositions(MyLinkedList<Position> posi) {
        this.punion(posi);
    }

    public void addPositions(AVLTree tr) {
        this.punion(tr);
    }

    public AVLTree getAllPositionsForThisWord() {
        return po;
    }
}
