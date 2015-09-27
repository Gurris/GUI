package CardBase;

import CardBase.Card.Rank;
import CardBase.Card.Suit;
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author Johan
 */
public class Deck {
    private final ArrayList<Card> deck;
 
    public Deck() {
        deck = new ArrayList<>();
        fill();
    }

    public int getNoOfCards() {
        return deck.size();
    }
    
    public Card getCard(int id) throws NoSuchCardException {
        if(id >= deck.size())
            throw new NoSuchCardException("Card with index " + id + " does not exist in deck!");

        return deck.get(id);
    }
    
    public Card dealCard() throws NoSuchCardException{
        if(deck.isEmpty())
            throw new NoSuchCardException("Deck is empty!");
        
        return deck.remove(0);
    }
    
    public void shuffle() {
        Collections.shuffle(deck);
    }
    
    public void fill() {
        deck.clear();

        for(Rank r: Rank.values()){
            for(Suit s: Suit.values()){
                deck.add(new Card(r, s));
            }
        }
    }
    
    @Override
    public String toString() {
        String info = new String();
        info += "Number of cards: " + deck.size() + "\nDeck: \n";
        for(Card c: deck)
            info += c.toString() + "\n";
        
        return info;
    }
    
}
