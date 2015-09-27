package CardBase;

/**
 *
 * @author Johan
 */
public class CardByRankComparator implements java.util.Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if(o1 instanceof Card == false || o2 instanceof Card == false)
            throw new IllegalArgumentException();
        
        Card c1 = (Card)o1;
        Card c2 = (Card)o2;
        
        int result;
        result = c1.getRank().ordinal() - c2.getRank().ordinal();
        
        if(result == 0)
            result = c1.getSuit().ordinal() - c2.getSuit().ordinal();
        
        return result;
    }
}

