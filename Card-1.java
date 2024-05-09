package GameDesign;

public class Card {
    private String suit;
    private String rank;

   // Constructor for creating a new card with the specified rank and suit.
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Method to get rank of the card
    public String getRank() {

        return rank;
    }

    // Method to get suit of the card
    public String getSuit() {
        return suit;
    }

    //Method to display the rank and suit of the card
    @Override
    public String toString() {

        return rank + " of " + suit;
    }
}

