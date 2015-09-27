package CardBase;

import java.util.ArrayList;

/**
 *
 * @author Johan
 */
public class Hand {
    private final ArrayList<Card> hand;
    private final String name;
    private int score;
    
    public Hand(String name) {
        this.hand = new ArrayList<>();
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public void changeScore(int amount) {
        score += amount;
    }
    
    public int getNoOfCards() {
        return hand.size();
    }
    
    public void addCard(Card card) {
        this.hand.add(card);
    }
    
    public Card getCard(int id) throws NoSuchCardException {
        if(id >= hand.size())
            throw new NoSuchCardException("Card with index " + id + " does not exist in hand!");

        return hand.get(id);
    }
    
    public Card removeCard(int id) throws NoSuchCardException{
        if(id >= hand.size())
            throw new NoSuchCardException("Card with index " + id + " does not exist in hand!");
        
        Card temp = hand.get(id);
        hand.remove(id);
        return temp;
    }
    
    public boolean removeCard(Card card) {
        for(Card c: hand){
            if(c.equals(card)){
                hand.remove(c);
                return true;
            }
        }
        return false;
    }
    
    public void clearHand() {
        hand.clear();
    }
    
    public void sort(CardByRankComparator comp) {
        java.util.Collections.sort(hand, comp);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Name: ").append(name).append(" - Score: ").append(score).append(" - Cards: ");
        for(Card c: hand)
            info.append(c.toString()).append(" | ");
        
        return info.toString();
    }

}
