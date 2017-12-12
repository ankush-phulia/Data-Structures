import java.util.ArrayList;
import java.util.Collections;

public class MySort<Sortable>{

	public ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfSortableEntries){
		ArrayList<SearchResult> x=new ArrayList<SearchResult>();
		for (int i=0;i<listOfSortableEntries.size();i++){
			x.add(listOfSortableEntries.l.get(i).element);
		}
		Collections.sort(x);
		return x;
	}
/*	
	public static void main(String[] args) throws FileNotFoundException{
		PageEntry p1=new PageEntry("stack_cprogramming");
		PageEntry p2=new PageEntry("stack_oracle");
		PageEntry p3=new PageEntry("stack_datastructure_wiki");
		PageEntry p4=new PageEntry("stacklighting");
		PageEntry p5=new PageEntry("stackmagazine");
		PageEntry p6=new PageEntry("stackoverflow");
		InvertedPageIndex inv=new InvertedPageIndex();
		inv.addPage(p1);
		inv.addPage(p2);
		inv.addPage(p3);
		inv.addPage(p4);
		inv.addPage(p5);
		inv.addPage(p6);
		String[] a={"stack"};
		SearchResult s1=new SearchResult(p1,2);
		SearchResult s2=new SearchResult(p1,3);
		SearchResult s3=new SearchResult(p1,1);
		SearchResult s4=new SearchResult(p1,4);
		MySet<SearchResult> m=new MySet<SearchResult>();
		m.l.addl(s1);
		m.l.addl(s2);
		m.l.addl(s3);
		m.l.addl(s4);
		MySort<SearchResult> m1=new MySort<SearchResult>();
		System.out.println(m1.sortThisList(m).get(3).rel);
	}*/
	
}
