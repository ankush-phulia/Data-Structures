import java.util.ArrayList;
import java.util.Collections;

public class MySort<Sortable> {

    public ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfSortableEntries) {
        ArrayList<SearchResult> x = new ArrayList<SearchResult>();
        for (int i = 0; i < listOfSortableEntries.size(); i++) {
            x.add(listOfSortableEntries.l.get(i).element);
        }
        Collections.sort(x);
        return x;
    }
}
