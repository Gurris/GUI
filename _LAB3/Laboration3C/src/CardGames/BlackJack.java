package CardGames;

import CardBase.Card;
import CardBase.Card.Rank;
import CardBase.CardByRankComparator;
import CardBase.Deck;
import CardBase.Hand;
import CardBase.NoSuchCardException;
import java.util.Scanner;

/**
 *
 * @author Johan
 */
public class BlackJack {
    private final Hand[] hand;
    private final Deck deck;
    private final Scanner scan;
    private final CardByRankComparator comp = new CardByRankComparator();
    
    public BlackJack() {
        hand = new Hand[2];             // Dealer and player
        deck = new Deck();
        scan = new Scanner(System.in);
        
        hand[0] = new Hand("Player");
        hand[1] = new Hand("Dealer");
    }
    
    public void play() {
        boolean playing;
        
        do{
            initGame();                 // Prepares a new round (fresh deck, reset+deal cards to hands)
            handleRound();              // Plays a whole round of blackjack until winner(s) have been crowned
            playing = askIfKeepPlaying();
        } while(playing);
        
        
        
    }
    
    private void initGame() {
        Card tempCard;
        deck.fill();
        deck.shuffle();
        for(Hand h: hand){
            h.clearHand();
            for(int i=0; i < 2; i++){
                tempCard = deck.dealCard();
                h.addCard(tempCard);
            }
        }
    }
    
    private void handleRound() {
        boolean playerIsPassing = false;

/*       **        
        Uncomment the following 3 lines to test whether the exceptionhandling 
        for dealing cards from an empty deckis working
        (Since every round has a fresh deck this could not happen naturally)
*/
//        Card temporaryCard;
//        for(int i=0; i < 48; i++)
//            temporaryCard = deck.dealCard();

        
        for(;;){
            System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            for(PlayerID id: PlayerID.values()){
                updateScore(id.ordinal());
                hand[id.ordinal()].sort(comp);
                System.out.println(hand[id.ordinal()].toString());
            }
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            
            // Check for initial blackjacks
            if(testForGameOver(PlayerID.HUMAN.ordinal(), playerIsPassing))
                break;
            if(testForGameOver(PlayerID.DEALER.ordinal(), playerIsPassing))
                break;
            
            // Player plays
            if(!playerIsPassing){
                System.out.print(" Hit? (Y/N) ");
                if(testIfYes())
                    dealCard(PlayerID.HUMAN);
                else
                    playerIsPassing = true;
            }
            else
                System.out.println("Player passes!");
            
            if(testForGameOver(PlayerID.HUMAN.ordinal(), playerIsPassing))
                break;
            
            // Dealer plays
            if(hand[PlayerID.DEALER.ordinal()].getScore() < 17)                     // Dealer always passes if score >=17
                dealCard(PlayerID.DEALER);
            else
                System.out.println("Dealer passes!");
            
            if(testForGameOver(PlayerID.DEALER.ordinal(), playerIsPassing))
                break;
        }
    }
    
    private void dealCard(PlayerID id) {
        Card tempCard;
        try{
            tempCard = deck.dealCard();
            hand[id.ordinal()].addCard(tempCard);
            System.out.println(id + " drew " + tempCard.toString());
            updateScore(id.ordinal());
        }
        catch(NoSuchCardException ex) {
            deck.fill();
            deck.shuffle();
            System.out.println("Deck empty, refilled and shuffled!");
            dealCard(id);
        }
    }
    
    private boolean askIfKeepPlaying() {
        System.out.print("\nPlay again? (Y/N) ");
        return testIfYes();
    }
    
    private boolean testIfYes() {
        String keepPlaying;
        keepPlaying = scan.next();
        keepPlaying = keepPlaying.toUpperCase();
        return keepPlaying.contains("Y");
    }
    
    private boolean testForGameOver(int id, boolean playerIsPassing) {
        if(hand[id].getScore() == 21){
            System.out.println(hand[id].getName() + " wins by blackjack!");
            return true;
        }
        else if(hand[id].getScore() > 21){
            System.out.println(hand[id].getName() + " went bust! (Score: " + hand[id].getScore() + ")");
            return true;
        }
        else if(id == PlayerID.DEALER.ordinal() && playerIsPassing && hand[id].getScore() >= 17){ // Player & dealer passing; highest hand wins
            if(hand[0].getScore() > hand[1].getScore())
                System.out.println(hand[0].getName() + " wins! ");
            else if(hand[0].getScore() < hand[1].getScore())
                System.out.println(hand[1].getName() + " wins! ");
            else
                System.out.println("Draw!");
            
            return true;
        }
        return false;
    }
    
    private void updateScore(int id) {                          // Resets and calculates the hand's score
        int aces = 0, value;
        Card tempCard;
        
        hand[id].setScore(0);
        for(int i=0; i < hand[id].getNoOfCards(); i++){
            tempCard = hand[id].getCard(i);
            value = tempCard.getRank().ordinal() + 1;           // +1 because ACE = 0, TWO = 1 etc. in Rank
            
            if(value >= 10)
                hand[id].changeScore(10);
            else
                hand[id].changeScore(value);
            
            if(tempCard.getRank() == Rank.ACE)
                aces++;
        }
        
        while(aces > 0 && hand[id].getScore() <= 11){           // Allow aces to be valued either 1 or 11 depending on whichever is best
            hand[id].changeScore(10);
            aces--;
        }
    }
    
    public enum PlayerID {
        HUMAN,
        DEALER;
    }
}
