package classes;

import entities.Card;
import entities.Color;
import entities.Deck;
import entities.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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

        for (User user: users){//first hands show
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
        ArrayList<Integer>results = new ArrayList<>();
        for(User user: users){
            int result = 0;
            for (Card card: user.getHand().getCards()){
                if (card.getColor().equals(Color.BLACK)){
                    result += 50;
                }
                else if (card.getValue() > 9){
                    result += 20;
                }
                else {
                    result += card.getValue();
                }
            }
            results.add(result);
        }
        int x = users.indexOf(Collections.min(results));
        String winner = users.get(x).getName();//ToDo fix ArrayIndexOutOfBoundException
        results.remove(x);
        x = users.indexOf(Collections.min(results));
        String secondplace = users.get(x).getName();
        int secondpoints = results.get(x);
        x = users.indexOf(Collections.min(results));
        String thirdplace = users.get(x).getName();
        int thirdpoints = results.get(x);
        x = users.indexOf(Collections.min(results));
        String lastplace = users.get(x).getName();
        int lastpoints = results.get(x);
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
