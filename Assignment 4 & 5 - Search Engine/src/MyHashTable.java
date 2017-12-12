import java.util.ArrayList;

public class MyHashTable {

	public MyLinkedList<WordEntry>[] l=new MyLinkedList[1000];;
	
	public MyHashTable(){
		for (int i=0;i<1000;i++){
			l[i]=new MyLinkedList<WordEntry>();
		}
	}
	
	public int getHashIndex(String str){
		long x=0;
		String s=str.toLowerCase();
		for (int i=0;i<str.length();i++){
			x=((x*33)%1000+(str.charAt(i)-'a'+1))%1000;
		}
		if (x<0){
			return (int) (x%1000+1000);
		}
		else{
			return (int) (x%1000);
		}
	}
	
	public void addPositionsForWord(WordEntry w){
		int x=getHashIndex(w.word);
		if (!l[x].wsearch(w.word)){
			l[x].addl(w);
		}
		else{
			l[x].wget_data(w.word).element.pos.union(w.pos);
		}
	}
	
	public WordEntry search(String s){
		return l[getHashIndex(s)].wget_data(s).element;
	}
	
/*	public static void main(String[] args) {
		MyHashTable h=new MyHashTable();
		WordEntry w1=new WordEntry("abc");
		WordEntry w2=new WordEntry("abc");
		WordEntry w3=new WordEntry("abcd");
		WordEntry w4=new WordEntry("abce");
		h.l[18].addl(w1);
		h.addPositionsForWord(w1);
		h.addPositionsForWord(w2);
		h.addPositionsForWord(w3);
		h.addPositionsForWord(w4);
		System.out.println(h.search("abc").pos.get(0).element);
	}*/

}
