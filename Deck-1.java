package GameDesign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards; // List to hold the Card

    //Constructor to initialize and shuffle a new deck of cards.
    public Deck() {
        initializeDeck();
        shuffle();
    }

    //Method to initialize the deck with a standard set of playing cards.
    private void initializeDeck() {
        // string arrays of suits and ranks of cards
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        cards = new ArrayList<>();
        // using a loop to add all types of cards to the list
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    // Method to shuffle the cards in the deck
    public void shuffle() {

        Collections.shuffle(cards);
    }

    //Method to deal one card from the top of the deck
    public Card deal() throws IllegalStateException {
        if (cards.isEmpty()) {
            throw new IllegalStateException("No more cards in the deck");
        }
        return cards.remove(0);
    }
}

