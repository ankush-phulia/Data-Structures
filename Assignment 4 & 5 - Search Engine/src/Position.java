public class Position {

    PageEntry pe;
    int wordIndex;
    String wor;

    public Position(PageEntry p, int word) {
        pe = p;
        wordIndex = word;
    }

    public Position(PageEntry p, int word, String w) {
        pe = p;
        wordIndex = word;
        wor = w;
    }

    public PageEntry getPageEntry() {
        return pe;
    }

    public int getWordIndex() {
        return wordIndex;
    }
}
