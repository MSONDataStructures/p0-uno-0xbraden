import java.util.ArrayList;
import java.util.List;

public class Braden_UnoPlayer implements UnoPlayer {

    @Override
    public int play(List<Card> hand, Card top, Color calledColor, GameState state) {

        List<Integer> playableCards = new ArrayList<>(); // list of playable cards

        // checks which cards are playable
        for (int i = 0; i < hand.size(); i++) {
            Card currentCard = hand.get(i);

            if (currentCard.getColor() == top.getColor() || currentCard.getRank() == top.getRank() || currentCard.getColor() == calledColor) {
                // add card to list if color or rank matches
                playableCards.add(i);
            }
        }

        // might add strategy here, just plays card from list
        if (!playableCards.isEmpty()) {
            // goes thru cards to check for duplicates
            for (int i = 0; i < playableCards.size(); i++) {
                // compare each card with the rest of the cards
                for (int j = i + 1; j < playableCards.size(); j++) {
                    // check if rank color and number match (makes it a duplicate)
                    if (hand.get(playableCards.get(i)).getRank() == hand.get(playableCards.get(j)).getRank() &&
                            hand.get(playableCards.get(i)).getColor().equals(hand.get(playableCards.get(j)).getColor()) &&
                            hand.get(playableCards.get(i)).getNumber() == hand.get(playableCards.get(j)).getNumber()) {

                        // return duplicate
                        return playableCards.get(i);
                    }
                }
            }

            // if no duplicates, just play the first playable card
            return playableCards.get(0);
        }


        // play wild card if nothing else works
        for (int i = 0; i < hand.size(); i++) {
            Card currentCard = hand.get(i);
            if (currentCard.getRank() == UnoPlayer.Rank.WILD || currentCard.getRank() == UnoPlayer.Rank.WILD_D4) {
                return i;
            }
        }

        // draw
        return -1;
    }


    @Override
    public Color callColor(List<Card> hand) {

        // initializing variables to count how many of each color in hand
        int redCount = 0;
        int yellowCount = 0;
        int greenCount = 0;
        int blueCount = 0;

        // counting the colors
        for (Card card : hand) {
            if (card.getColor() == Color.RED) {
                redCount++;
            } else if (card.getColor() == Color.YELLOW) {
                yellowCount++;
            } else if (card.getColor() == Color.GREEN) {
                greenCount++;
            } else if (card.getColor() == Color.BLUE) {
                blueCount++;
            }
        }

        // finds which has the most
        if (yellowCount >= redCount && yellowCount >= greenCount && yellowCount >= blueCount) {
            return Color.YELLOW;
        } else if (greenCount >= redCount && greenCount >= yellowCount && greenCount >= blueCount) {
            return Color.GREEN;
        } else if (blueCount >= redCount) {
            return Color.BLUE;
        } else {
            return Color.RED;
        }
    }
}
