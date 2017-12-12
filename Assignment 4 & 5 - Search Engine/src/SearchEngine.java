import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SearchEngine{

	InvertedPageIndex inv;
	
	public SearchEngine() {
		inv=new InvertedPageIndex();
	}

	
	
	public void performAction(String actionMessage) throws FileNotFoundException {
		try{
			String[] stra=actionMessage.split(" ");
			if (stra[0].equals("addPage")){
				inv.addPage(new PageEntry(stra[1]));
			}
			else if (stra[0].equals("queryFindPagesWhichContainWord")){
				/*System.out.print(stra[1]+" ");*/
				String str=stra[1].toLowerCase();
				if (!str.equals("a")&& !str.equals("an")&& !str.equals("the") && !str.equals("they")&& !str.equals("these")&& !str.equals("for")&& !str.equals("is")&& !str.equals("are")&& !str.equals("of")&& !str.equals("or")&& !str.equals("and")&& !str.equals("does")&& !str.equals("will")&& !str.equals("whose")&& !str.equals("that")){
					if (str.equals("stacks")){
						str="stack";
					}
					else if (str.equals("structures")){
						str="structure";
					}
					else if (str.equals("applications")){
						str="application";
					}
					MySet<PageEntry> m=(inv.getPagesWhichContainWord(str));
					for (int i=0;i<m.size();i++){
						PageEntry p=(PageEntry) m.l.get(i).element;
						System.out.print(p.pname + "   ");
					}
					System.out.println(" ");
				}
				else{
					System.out.println("Its a Stop Word");
				}
			}
			else if(stra[0].equals("queryFindPositionsOfWordInAPage")){
				String str=stra[1].toLowerCase();
				String b=stra[2];
				boolean chk=false;
				for (int j=0;j<inv.pages.size;j++){
					if (inv.pages.get(j).element.pname.equals(b)){
						chk=true;
					}
				}
				if (chk){
					if (!str.equals("a")&& !str.equals("an")&& !str.equals("the") && !str.equals("they")&& !str.equals("these")&& !str.equals("for")&& !str.equals("is")&& !str.equals("are")&& !str.equals("of")&& !str.equals("or")&& !str.equals("and")&& !str.equals("does")&& !str.equals("will")&& !str.equals("whose")&& !str.equals("was")&& !str.equals("does") && !str.equals("this")){
						PageEntry p=new PageEntry(b);
						if (str.equals("stacks")){
							str="stack";
						}
						else if (str.equals("structures")){
							str="structure";
						}
						else if (str.equals("applications")){
							str="application";
						}
						/*MyLinkedList<Position>pp=p.pin.wes.wget_data(str).element.pos;*/
						AVLTree pp=p.pin.wes.wget_data(str).element.po;
						/*System.out.println(p.pin.wes.wget_data(str).element.pos.size);*/
						ArrayList<Position>ppp=pp.traverse();
						/*System.out.print(p.pin.wes.wget_data(str).element.pos.get(0).element.wordIndex+" ");*/
						for (int i=0;i<ppp.size();i++){
							System.out.print(ppp.get(i).wordIndex+" ");
						}
						System.out.println(" ");
					}
					else{
						System.out.println("Its a Stop Word");
					}
				}
				else{
					System.out.println("Page does not Exist in Database");
				}
								
			}
			else if (stra[0].equals("queryFindPagesWhichContainAllWords")){
				String[] str=new String[stra.length-1];
				for (int i=0;i<str.length;i++){
					str[i]=stra[i+1];
				}
				ArrayList<SearchResult> res=inv.getPagesWhichContainAllWords(str);
				for (int j=0;j<res.size();j++){
					System.out.print(res.get(j).getPageEntry().pname+"  ");
					/*System.out.print(res.get(j).getPageEntry().getRelevanceofPage(str, false)+"  ");*/
				}
				System.out.println(" ");
			}
			else if (stra[0].equals("queryFindPagesWhichContainAnyOfTheseWords")){
				String[] str=new String[stra.length-1];
				for (int i=0;i<str.length;i++){
					if (stra[i].equals("stacks")){
						stra[i]="stack";
					}
					else if (stra[i].equals("structures")){
						stra[i]="structure";
					}
					else if (stra[i].equals("applications")){
						stra[i]="application";
					}
					str[i]=stra[i+1];
				}
				ArrayList<SearchResult> res=inv.getPagesWhichContainAnyOfTheseWords(str);
				for (int j=0;j<res.size();j++){
					System.out.print(res.get(j).getPageEntry().pname+"  ");
					/*System.out.print(res.get(j).getPageEntry().getRelevanceofPage(str, false)+"  ");*/
				}
				System.out.println(" ");
			}
			else if(stra[0].equals("queryFindPagesWhichContainPhrase")){
				String[] str=new String[stra.length-1];
				for (int i=0;i<str.length;i++){
					if (stra[i+1].equals("stacks")){
						str[i]="stack";
					}
					else if (stra[i+1].equals("structures")){
						str[i]="structure";
					}
					else if (stra[i+1].equals("applications")){
						str[i]="application";
					}
					else{
						str[i]=stra[i+1];
					}
				}
				MySet<PageEntry> res=inv.getPagesWhichContainPhrase(str);
				MySet<SearchResult> cool=new MySet<SearchResult>();
				for (int j=0;j<res.size();j++){
					if (str.length==1){
						/*System.out.print(res.l.get(j).element.pname+"  ");*/
						cool.insert(new SearchResult(res.l.get(j).element,res.l.get(j).element.getRelevanceofPage(str, false)));
					}
					else if (str.length==2){
						for (int x=0;x<res.l.get(j).element.pin.wget_data(str[0]).element.pos.size;x++){
							int k=res.l.get(j).element.pin.wget_data(str[0]).element.pos.get(x).element.wordIndex;
							if (res.l.get(j).element.atree.succ(k)==null){
								break;
							}
							else if (res.l.get(j).element.atree.succ(k).wor.equals(str[1])){
								/*System.out.print(res.l.get(j).element.pname+"  ");*/
								cool.insert(new SearchResult(res.l.get(j).element,1.0/k/k));
								break;
							}
						}
					}
					else if (str.length==3){
						for (int x=0;x<res.l.get(j).element.pin.wget_data(str[0]).element.pos.size;x++){
							int k=res.l.get(j).element.pin.wget_data(str[0]).element.pos.get(x).element.wordIndex;
							if (res.l.get(j).element.atree.succ(k)==null){
								break;
							}
							else if (res.l.get(j).element.atree.succ(k).wor.equals(str[1])){
								for (int y=0;y<res.l.get(j).element.pin.wget_data(str[1]).element.pos.size;y++){
									int k1=res.l.get(j).element.pin.wget_data(str[1]).element.pos.get(y).element.wordIndex;
									if (res.l.get(j).element.atree.succ(k1)==null){
										break;
									}
									else if (res.l.get(j).element.atree.succ(k1).wor.equals(str[2])){
										/*System.out.print(res.l.get(j).element.pname+"  ");*/
										cool.insert(new SearchResult(res.l.get(j).element,1.0/k/k));
										break;
									}
							}
						}
					}}
					else if (str.length==4){
						for (int x=0;x<res.l.get(j).element.pin.wget_data(str[0]).element.pos.size;x++){
							int k=res.l.get(j).element.pin.wget_data(str[0]).element.pos.get(x).element.wordIndex;
							if (res.l.get(j).element.atree.succ(k)==null){
								break;
							}
							else if (res.l.get(j).element.atree.succ(k).wor.equals(str[1])){
								for (int y=0;y<res.l.get(j).element.pin.wget_data(str[1]).element.pos.size;y++){
									int k1=res.l.get(j).element.pin.wget_data(str[1]).element.pos.get(y).element.wordIndex;
									if (res.l.get(j).element.atree.succ(k1)==null){
										break;
									}
									else if (res.l.get(j).element.atree.succ(k1).wor.equals(str[2])){
										for (int z=0;z<res.l.get(j).element.pin.wget_data(str[1]).element.pos.size;z++){
											int k2=res.l.get(j).element.pin.wget_data(str[2]).element.pos.get(z).element.wordIndex;
											if (res.l.get(j).element.atree.succ(k2)==null){
												break;
											}
											else if (res.l.get(j).element.atree.succ(k2).wor.equals(str[3])){
												/*System.out.print(res.l.get(j).element.pname+"  ");*/
												cool.insert(new SearchResult(res.l.get(j).element,1.0/k/k));
												break;
											}
											
										}
									}
							}
						}
					}}
					else{
						System.out.println("Queries of this size not supported");
						break;
					}
				}
				/*System.out.println(" ");*/
				MySort<SearchResult> m=new MySort<SearchResult>();
				ArrayList<SearchResult> cc=m.sortThisList(cool);
				for (int i=0;i<cc.size();i++){
					System.out.print(cc.get(i).pe.pname+"  ");
				}
				System.out.println(" ");
			}
			else{
				System.out.println("Invalid Command");
			}	
		}
		catch(NullPointerException e){
			System.out.println("Word(s) not Found");
		}
		catch (FileNotFoundException e){
			System.out.println("File not Found");
		}
		catch(Exception e){
			System.out.println("Something went wrong...(Maybe you searched for only stop words?)");
		}		
		
	}
	
	
}
