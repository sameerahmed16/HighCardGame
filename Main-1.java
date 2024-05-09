import GameDesign.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Asking user to enter name and initial money for player 1
        String playerName1 = JOptionPane.showInputDialog(null, "Enter Player 1 name:");
        int player1Money = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Player 1 initial money:"));

        //Asking user to enter name and initial money for player 2
        String playerName2 = JOptionPane.showInputDialog(null, "Enter Player 2 name:");
        int player2Money = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Player 2 initial money:"));

        //Creating a game using the information entered by user
        Game game = new Game(playerName1, player1Money, playerName2, player2Money);
    }
}

