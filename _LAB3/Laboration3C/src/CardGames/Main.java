package CardGames;

//import CardBase.*;


/**
 *
 * @author Johan
 */
public class Main {

    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.play();
        
//        Deck deck = new Deck();
//        Hand hand = new Hand("Player");
//        CardByRankComparator comp = new CardByRankComparator();
//        
//        deck.shuffle();
//        for(int i=0; i < 5; i++)
//            hand.addCard(deck.dealCard());
//        
//        System.out.println(hand);
//        
//        //Sort the hand
//        hand.sort(comp);
//        System.out.println(hand);
//        
//        // Get non-existant card from the hand to invoke an exception
//        try{
//            System.out.println(hand.getCard(5));
//        }
//        catch(NoSuchCardException ex){
//            System.out.println("FAIL KEK");
//        }
       
    }
}

