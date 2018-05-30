import java.util.ArrayList;

public class AVLTree {

    ANode root;

    public AVLTree() {
        root = null;
    }

    public AVLTree(ANode a) {
        root = a;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int ht(ANode r) {
        if (r == null) {
            return 0;
        } else return r.ht;
    }

    public ANode insert(Position p, ANode r) {
        if (r == null) {
            r = new ANode(p);
        } 
        else {
            if (p.wordIndex < (r.p.wordIndex)) {
                r.lt_child = insert(p, r.lt_child);
                if (ht(r.lt_child) - ht(r.rt_child) == 2) {
                    if (p.wordIndex < r.lt_child.p.wordIndex) {
                        r = lt_rot(r);
                    } else {
                        r.lt_child = rt_rot(r.lt_child);
                        r = lt_rot(r);
                    }
                }
            } 
            else if (p.wordIndex > (r.p.wordIndex)) {
                r.rt_child = insert(p, r.rt_child);
                if (ht(r.rt_child) - ht(r.lt_child) == 2) {
                    if (p.wordIndex > r.rt_child.p.wordIndex) {
                        r = rt_rot(r);
                    } 
                    else {
                        r.rt_child = lt_rot(r.rt_child);
                        r = rt_rot(r);
                    }
                }
            }
        }
        r.ht = Integer.max(ht(r.rt_child), ht(r.lt_child)) + 1;
        return r;
    }

    public ANode lt_rot(ANode r) {
        ANode lch = r.lt_child;
        r.lt_child = lch.rt_child;
        lch.rt_child = r;
        r.ht = Integer.max(ht(r.lt_child), ht(r.rt_child)) + 1;
        lch.ht = Integer.max(ht(lch.lt_child), ht(lch.rt_child)) + 1;
        return lch;
    }

    public ANode rt_rot(ANode r) {
        ANode rch = r.rt_child;
        r.rt_child = rch.lt_child;
        rch.lt_child = r;
        r.ht = Integer.max(ht(r.lt_child), ht(r.rt_child)) + 1;
        rch.ht = Integer.max(ht(rch.lt_child), ht(rch.rt_child)) + 1;
        return rch;
    }

    public void insert(Position p) {
        if (search(p) == null) {
            this.root = this.insert(p, this.root);
        }
    }

    public ANode search(Position p) {
        if (isEmpty()) {
            return null;
        } 
        else {
            if (root.p.wordIndex == p.wordIndex) {
                return root;
            } 
            else if (p.wordIndex < root.p.wordIndex) {
                AVLTree x = new AVLTree(root.lt_child);
                return x.search(p);
            } 
            else {
                AVLTree x = new AVLTree(root.rt_child);
                return x.search(p);
            }
        }
    }

    public ArrayList<Position> traverse() {
        ArrayList<Position> travel = new ArrayList<Position>();
        if (this.isEmpty()) {
            return travel;
        }
        if (root.lt_child == null && root.rt_child == null) {
            travel.add(root.p);
        } 
        else {
            AVLTree lt_tree = new AVLTree(root.lt_child);
            AVLTree rt_tree = new AVLTree(root.rt_child);
            travel.addAll(lt_tree.traverse());
            travel.add(root.p);
            travel.addAll(rt_tree.traverse());
        }
        return travel;
    }

    public ANode max() {
        if (isEmpty()) {
            return root;
        } 
        else if (root.rt_child == null) {
            return root;
        } 
        else {
            AVLTree ne = new AVLTree(root.rt_child);
            return ne.max();
        }
    }

    public ANode min() {
        if (isEmpty()) {
            return root;
        } 
        else if (root.lt_child == null) {
            return root;
        } 
        else {
            AVLTree ne = new AVLTree(root.lt_child);
            return ne.min();
        }
    }

    public Position succ(int p) {
        ArrayList<Position> a = this.traverse();
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).wordIndex == (p)) {
                return a.get(i + 1);
            }
        }
        return null;
    }
}
