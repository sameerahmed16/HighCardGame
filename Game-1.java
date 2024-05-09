package GameDesign;

import javax.swing.*;
import java.awt.*;

public class Game {
    private Deck deck; //Deck of cards used in the game
    private Player player1,player2; //Player Objects
    private JLabel player1MoneyLabel, player2MoneyLabel; // Labels to display player's money

    private JFrame frame; // Main window of the application
    private JTextArea textArea; // Area to display game logs and results

    // Buttons to control game action
    private JButton playButton;
    private JButton quitButton;

   // Constructor to initialize a new game with two players.
    public Game(String player1Name, int player1Money, String player2Name, int player2Money) {
        deck = new Deck();
        player1 = new userPlayer(player1Name, player1Money);
        player2 = new userPlayer(player2Name, player2Money);
        initializeGUI(player1Name, player1Money, player2Name, player2Money);
    }

    //Method to initialize the graphical user interface for the game.
    private void initializeGUI(String player1Name, int player1Money, String player2Name, int player2Money) {
        frame = new JFrame("High Card - Final Project by Sameer, Samarth and Kethan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        ImageIcon trio = new ImageIcon("Trio.jpg");
        frame.setIconImage(trio.getImage());

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.X_AXIS));

        JPanel player1Panel = new JPanel();
        player1Panel.setLayout(new BoxLayout(player1Panel, BoxLayout.Y_AXIS));
        JLabel player1NameLabel = new JLabel(player1Name);
        player1NameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        player1MoneyLabel = new JLabel("$" + player1Money);
        player1MoneyLabel.setFont(new Font("Arial", Font.BOLD, 12));
        player1Panel.add(player1NameLabel);
        player1Panel.add(player1MoneyLabel);
        playerPanel.add(player1Panel);

        JPanel player2Panel = new JPanel();
        player2Panel.setLayout(new BoxLayout(player2Panel, BoxLayout.Y_AXIS));
        JLabel player2NameLabel = new JLabel(player2Name);
        player2NameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        player2MoneyLabel = new JLabel("$" + player2Money);
        player2MoneyLabel.setFont(new Font("Arial", Font.BOLD, 12));
        player2Panel.add(player2NameLabel);
        player2Panel.add(player2MoneyLabel);
        playerPanel.add(Box.createHorizontalGlue());
        playerPanel.add(player2Panel);

        frame.getContentPane().add(playerPanel, BorderLayout.NORTH);

        textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        playButton = new JButton("Play");
        quitButton = new JButton("Quit");

        playButton.addActionListener(e -> playRound());
        quitButton.addActionListener(e -> endGameImmediately());

        buttonPanel.add(playButton);
        buttonPanel.add(quitButton);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    //Method to manage each round of the game
    private void playRound() {
        if (player1.getMoney() < 1 || player2.getMoney() < 1) {
            textArea.append("Game over. Insufficient funds.\n");
            endGame();
            return;
        }

        int mutualBetAmount = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter the mutual bet amount:"));

        if (mutualBetAmount > player1.getMoney() || mutualBetAmount > player2.getMoney()) {
            textArea.append("Invalid bet amount. Please enter a valid amount.\n");
            textArea.append("------------------------------------------------\n");
            return;
        }

        textArea.append("Bet: $" + mutualBetAmount + "\n\n");

        player1.pay(mutualBetAmount);
        player2.pay(mutualBetAmount);
        int pot = mutualBetAmount * 2;

        // Dealing cards and determining the winner
        Card card1 = deck.deal();
        Card card2 = deck.deal();

        textArea.setFont(new Font("TimesRoman", Font.BOLD, 12));
        textArea.append(player1.getName() + " draws " + card1 + "\n");
        textArea.append(player2.getName() + " draws " + card2 + "\n\n");

        int compareResult = compareCards(card1, card2);
        if (compareResult > 0) {
            player1.receive(pot);
            textArea.append(player1.getName() + " wins round!\n");
        } else if (compareResult < 0) {
            player2.receive(pot);
            textArea.append(player2.getName() + " wins round!\n");
        } else {
            player1.receive(pot / 2);
            player2.receive(pot / 2);
            textArea.append("Tie!\n");
        }

        // Update money labels for both players
        player1MoneyLabel.setText("$" + player1.getMoney());
        player2MoneyLabel.setText("$" + player2.getMoney());

        textArea.append("------------------------------------------------\n");

        deck.shuffle(); // Re-shuffle to simulate continuous play with a single deck
    }

    //Ends the game immediately, closes the application window, and displays final scores.
    private void endGameImmediately() {
        displayFinalScores();
        frame.dispose();
    }


    private void endGame() {
        displayFinalScores();
        playButton.setEnabled(false);
    }

    // Method to display the final scores of the game
    private void displayFinalScores() {
        textArea.append("\nFinal scores:\n");
        textArea.append(player1.getName() + ": $" + player1.getMoney() + "\n");
        textArea.append(player2.getName() + ": $" + player2.getMoney() + "\n");
    }
    //Method to compares two cards and returns an integer based on their ranks
    private int compareCards(Card c1, Card c2) {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int rank1 = indexOf(ranks, c1.getRank());
        int rank2 = indexOf(ranks, c2.getRank());
        return Integer.compare(rank1, rank2);
    }

    private int indexOf(String[] array, String key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }
}