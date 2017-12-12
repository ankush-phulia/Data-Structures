import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PageEntry {

	String pname;
	PageIndex pin=new PageIndex();
	AVLTree atree=new AVLTree();
	
	/*PageEntry(String Page) throws FileNotFoundException{
		pname=Page;
		FileInputStream f1=new FileInputStream("C:\\Python27\\COL 106 Java\\Assignment 4 & 5 - Search Engine\\webpages\\"+Page);
		Scanner s=new Scanner(f1);
		int i=0;
		while (s.hasNext()){
			String str=s.next();
			System.out.println(str);
			if (str.contains("<") || str.contains(">") || str.contains("[") ||str.contains("]") || str.contains("{")||str.contains("}")||str.contains("(")||str.contains(")")||str.contains("#")||str.contains("\'")||str.contains("\"")|| str.contains("-")){
				str=str.replace('<', ' ');
				str=str.replace('>',' ');
				str=str.replace('[', ' ');
				str=str.replace(']', ' ');
				str=str.replace('{', ' ');
				str=str.replace('{', ' ');
				str=str.replace('#', ' ');
				str=str.replace("(", " ");
				str=str.replace(")", " ");
				str=str.replace('\'', ' ');
				str=str.replace('\"', ' ');
				str=str.replace('.', ' ');
				str=str.replace(',', ' ');
				str=str.replace('!', ' ');
				str=str.replace('?', ' ');
				str=str.replace('/', ' ');
				str=str.replace(';', ' ');
				str=str.replace(':', ' ');
				str=str.replace('-', ' ');
				str=str.replace('-', ' ');
				str=str.trim();
				String[] sta=str.split("\\s+");
				for (int sw=0;sw<sta.length;sw++){
					if (!sta[sw].equals("")){
						Position p=new Position(this,i);
						pin.addPositionForWord(sta[sw].toLowerCase(), p);
						Position p1=new Position(this,i,str);
						atree.insert(p1);
						i++;
					}
				}			
			}
			else if (str.contains(".") || str.contains("?") || str.contains(",")|| str.contains("!")|| str.contains(";")|| str.contains("-")|| str.contains(":")){
				str=str.substring(0, str.length()-1);
				Position p=new Position(this,i);
				Position p1=new Position(this,i,str);
				atree.insert(p1);
				pin.addPositionForWord(str.toLowerCase(), p);
			}
			else if (str.equals("a")||str.equals("to")||str.equals("and")||str.equals("an")||str.equals("the")||str.equals("is")||str.equals("are")||str.equals("they")||str.equals("these")||str.equals("this")||str.equals("am")||str.equals("of")||str.equals("was")||str.equals("and")||str.equals("will")||str.equals("whose")||str.equals("that")){
				continue;
			}
			else{
				Position p=new Position(this,i);
				str=str.toLowerCase();
				if (str.equals("stacks")){
					str="stack";
				}
				else if (str.equals("structures")){
					str="structure";
				}
				else if (str.equals("applications")){
					str="application";
				}
				pin.addPositionForWord(str, p);
				Position p1=new Position(this,i,str);
				atree.insert(p1);
				i++;
			}			
		}
		s.close();
	}*/
	
	public PageEntry(String pageName) throws IOException,FileNotFoundException {
		// TODO Auto-generated constructor stub
		try {
			//File fileSource=new File("webpages/"+pageName);
			FileInputStream file=new FileInputStream("webpages/"+pageName);
			Scanner s=new Scanner(file);
			String connectors[]={"a","an","the","they","these","this","for","is","are","was","of","or","and","does","will","whose"};
			int i=0;
			while(s.hasNext())
			{
				String toPut=s.next();
					boolean connectorFound=false;
					for(int c=0;c<connectors.length;c++)
					{					
						if(toPut.equals(connectors[c]))
						{	
							connectorFound=true;
							break;
						}
					}
					if(connectorFound)
					{
						i++;
						continue;
					}
				toPut=filter(toPut);				
				String split[]=toPut.split(" ");
				int j=0;
				while(j<split.length)
				{					
					Position p=new Position(this, i);
					pin.addPositionForWord(split[j], p);
					Position p1=new Position(this,i,split[j]);
					atree.insert(p1);
					j++;
					i++;
				}
			}
			s.close();
			file.close();
		} catch (FileNotFoundException e) {
			System.out.print("FileNotFound,");
			return;
		}
		this.pname=pageName;
	}

	public String filter(String str)
	{
		str = str.replaceAll("[\\-\\:\\^\\,\\.\\;\\'\\?\\!\\#\\<\\>\\[\\]\\=\\(\\)\\{\\}]", " ");
		str = str.replace('"', ' ');
		str = str.trim();
		str = str.toLowerCase();
		if(str.equals("stacks") || str.equals("structures") || str.equals("applications")) 
			str = str.substring(0, str.length() - 1);
		return str;
	}
	
	public PageIndex getPageIndex(){
		return pin;
	}
	
	public double getRelevanceofPage(String[] str, boolean doTheseWordsRepresentAPhrase){
		double rev=0.0;
		if (!doTheseWordsRepresentAPhrase){
			for (int i=0;i<str.length;i++){
				if (pin.wsearch(str[i].toLowerCase())){
					WordEntry w=pin.wget_data(str[i].toLowerCase()).element;
					for (int j=0;j<w.pos.size;j++){
						if (w.pos.get(j).element.pe.pname.equals(this.pname)){
							int k=(w.pos.get(j).element.wordIndex)+1;
							rev=rev+(1.0)/(k*k);
						}
					}
				}
			}
		}
		else{
			
		}
		return rev;
	}
/*	
	public static void main(String[] args) throws IOException{
		PageEntry p=new PageEntry("stackoverflow");
		String[] str={"overflow"};
		System.out.println(p.pin.wget_data("stack").element.pos.size);
		System.out.println(p.getRelevanceofPage(str, false));
	}*/

}
