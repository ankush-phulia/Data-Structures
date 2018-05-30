import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PageEntry {

    String pname;
    PageIndex pin = new PageIndex();
    AVLTree atree = new AVLTree();


    public PageEntry(String pageName) throws IOException, FileNotFoundException {
        try {
            FileInputStream file = new FileInputStream("webpages/" + pageName);
            Scanner s = new Scanner(file);
            String connectors[] = {
                "a", "an", "the", "they", "these", "this", "for", "is", "are", "was", "of", "or",
                "and", "does", "will", "whose"
            };
            int i = 0;
            while (s.hasNext()) {
                String toPut = s.next();
                boolean connectorFound = false;
                for (int c = 0; c < connectors.length; c++) {
                    if (toPut.equals(connectors[c])) {
                        connectorFound = true;
                        break;
                    }
                }
                if (connectorFound) {
                    i++;
                    continue;
                }
                toPut = filter(toPut);
                String split[] = toPut.split(" ");
                int j = 0;
                while (j < split.length) {
                    Position p = new Position(this, i);
                    pin.addPositionForWord(split[j], p);
                    Position p1 = new Position(this, i, split[j]);
                    atree.insert(p1);
                    j++;
                    i++;
                }
            }
            s.close();
            file.close();
        } 
        catch (FileNotFoundException e) {
            System.out.print("FileNotFound,");
            return;
        }
        this.pname = pageName;
    }

    public String filter(String str) {
        str = str.replaceAll("[\\-\\:\\^\\,\\.\\;\\'\\?\\!\\#\\<\\>\\[\\]\\=\\(\\)\\{\\}]", " ");
        str = str.replace('"', ' ');
        str = str.trim();
        str = str.toLowerCase();
        if (str.equals("stacks") || str.equals("structures") || str.equals("applications"))
            str = str.substring(0, str.length() - 1);
        return str;
    }

    public PageIndex getPageIndex() {
        return pin;
    }

    public double getRelevanceofPage(String[] str, boolean doTheseWordsRepresentAPhrase) {
        double rev = 0.0;
        if (!doTheseWordsRepresentAPhrase) {
            for (int i = 0; i < str.length; i++) {
                if (pin.wsearch(str[i].toLowerCase())) {
                    WordEntry w = pin.wget_data(str[i].toLowerCase()).element;
                    for (int j = 0; j < w.pos.size; j++) {
                        if (w.pos.get(j).element.pe.pname.equals(this.pname)) {
                            int k = (w.pos.get(j).element.wordIndex) + 1;
                            rev = rev + (1.0) / (k * k);
                        }
                    }
                }
            }
        } 
        return rev;
    }
}
