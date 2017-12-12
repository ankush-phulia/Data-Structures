import java.util.ArrayList;

public class InvertedPageIndex {
	
	MyLinkedList<PageEntry> pages=new MyLinkedList<PageEntry>();
	MyHashTable ht=new MyHashTable();
	
	public void addPage(PageEntry p){
		int n=p.pin.wes.size;
		pages.addl(p);
		for (int i=0;i<n;i++){
			ht.addPositionsForWord(p.pin.wes.get(i).element);
		}
	}
	
	public MySet<PageEntry> getPagesWhichContainWord(String str){
		str=str.toLowerCase();
		MyLinkedList<Position>pp=((ht.l[ht.getHashIndex(str)]).wget_data(str).element.pos);
		MySet<PageEntry> mys=new MySet<PageEntry>();
		for (int i=0;i<pp.size;i++){
			mys.l.addl(pp.get(i).element.pe);
		}
		return mys;
	}
	
	public ArrayList<SearchResult> getPagesWhichContainAllWords(String[] str){
		MySet<PageEntry> ms=new MySet<PageEntry>();
		ms=this.getPagesWhichContainWord(str[0]);
		for (int i=1;i<str.length;i++){
			MySet<PageEntry> m=this.getPagesWhichContainWord(str[i]);
			ms=ms.intersection(m);
		}
		MySet<SearchResult> res=new MySet<SearchResult>();
		for (int i=0;i<ms.size();i++){
			PageEntry p=ms.l.get(i).element;
			double d=p.getRelevanceofPage(str, false);
			SearchResult s=new SearchResult(p,d);
			res.l.addl(s);
		}
		MySort<SearchResult> m=new MySort<SearchResult>();
		return m.sortThisList(res);
	}
	
	public ArrayList<SearchResult> getPagesWhichContainAnyOfTheseWords(String[] str){
		MySet<PageEntry> ms=new MySet<PageEntry>();
		ms=this.getPagesWhichContainWord(str[0]);
		for (int i=1;i<str.length;i++){
			MySet<PageEntry> m=this.getPagesWhichContainWord(str[i]);
			ms=ms.union(m);
		}
		MySet<SearchResult> res=new MySet<SearchResult>();
		for (int i=0;i<ms.size();i++){
			PageEntry p=ms.l.get(i).element;
			double d=p.getRelevanceofPage(str, false);
			SearchResult s=new SearchResult(p,d);
			res.l.addl(s);
		}
		MySort<SearchResult> m=new MySort<SearchResult>();
		return (m.sortThisList(res));
	}
	
	public MySet<PageEntry> getPagesWhichContainPhrase(String[] str){
		ArrayList<String> s=new ArrayList<String>();
		for (int i=0;i<str.length;i++){
			if (!str[i].equals("a")&& !str[i].equals("an")&& !str[i].equals("the") && !str[i].equals("they")&& !str[i].equals("these")&& !str[i].equals("for")&& !str[i].equals("is")&& !str[i].equals("are")&& !str[i].equals("of")&& !str[i].equals("or")&& !str[i].equals("and")&& !str[i].equals("does")&& !str[i].equals("will")&& !str[i].equals("whose")&& !str[i].equals("was")&& !str[i].equals("does") && !str[i].equals("this")){
				if (str[i].equals("stacks")){
					s.add("stack");
				}
				else if (str[i].equals("structures")){
					s.add("structure");
				}
				else if (str[i].equals("applications")){
					s.add("applications");
				}
				else{
					s.add(str[i]);
				}
			}

		}
		String[] stra=new String[s.size()];
		for (int i=0;i<stra.length;i++){
			stra[i]=s.get(i);
		}
		MySet<PageEntry> mys=new MySet<PageEntry>();
		ArrayList<SearchResult> arr=getPagesWhichContainAllWords(stra);
		for (int i=0;i<arr.size();i++){
			mys.insert(arr.get(i).pe);
		}
		return mys;
	}
		
/*	public static void main(String[] args) throws FileNotFoundException{
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
		String[] a={"stack","a"};
		System.out.println(inv.getPagesWhichContainAnyOfTheseWords(a));
	}*/

}
