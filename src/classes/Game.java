package classes;

import entities.Card;
import entities.Deck;
import entities.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

//import threads.Connection;

/**
 * Created by 1 on 11.12.2016.
 */
public class Game {
    private List<User> users;

    public Game(List<User> users) {
        this.users = users;
    }

    public void play() {
        //start game
        User c = users.get(0);
        for (User user : users) {
            user.getOut().println(c.getName() + " goes first.");
        }
        Deck deck = new Deck(false);
        deck.reshuffle();
        Deck discardpile = new Deck(true);

        //start hands
        for (User user: users) {
            for (int i = 0; i < 7; i++) {
                user.getHand().putCard(deck.draw());
            }
        }

        for (User user: users){
            PrintWriter out = user.getOut();
            for (Card card: user.getHand().getCards()){
                out.print(card + " ");
            }
            System.out.println();
        }

        //Game loop
        for (User user : users) {
            BufferedReader in = user.getIn();
            String message = null;
            try {
                message = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (User user1 : users) {
                PrintWriter out = user1.getOut();
                out.println(message);
            }
        }

        //Results
        String winner = users.get(0).getName();
        String secondplace = users.get(1).getName();
        String thirdplace = users.get(2).getName();
        String lastplace = users.get(3).getName();
        int secondpoints = 0;
        int thirdpoints = 0;
        int lastpoints = 0;
        for (User user : users) {
            user.getOut().println(winner + " has won");
            user.getOut().println(secondplace + " took the second place with " + secondpoints + " points");
            user.getOut().println(thirdplace + " took the third place with " + thirdpoints + " points");
            user.getOut().println(lastplace + " took the last place with " + lastpoints + " points");
            user.getOut().println("exit");
            user.close();
        }
    }
}
