package GameDesign;

public abstract class Player {
    protected String name; // Player's name
    protected int money; //Player's current money

    //Constructor for creating a new player with a name and initial money.
    public Player(String name, int mon) {
        this.name = name;
        this.money = mon;
    }

    //Method for getting the player's name.
    public String getName() {
        return name;
    }

    //Method to display the current amount of money the player has
    public int getMoney() {

        return money;
    }

    //Method to deduct money from player's money
    public void pay(int amount) {
        money -= amount;
    }

    //Method to add money to player's money
    public void receive(int amount) {

        money += amount;
    }
}

//This is a design in case we want to add more options to the user player, and add more types of players like dealer.
class userPlayer extends Player {
    public userPlayer(String name, int Money) {
        super(name, Money);
    }
}