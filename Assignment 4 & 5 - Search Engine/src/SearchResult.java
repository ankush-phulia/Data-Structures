public class SearchResult implements Comparable<SearchResult>{
	
	PageEntry pe;
	double rel;
	
	public SearchResult(PageEntry p, double r){
		pe=p;
		rel=r;
	}
	
	public PageEntry getPageEntry(){
		return pe;
	}
	
	public double getRelevance(){
		return rel;
	}
	
	@Override
	public int compareTo(SearchResult otherObject) {
		if (otherObject.rel>this.rel){
			return 1;
		}
		else if (otherObject.rel==this.rel){
			return 0;
		}
		else return -1;
	}
}
