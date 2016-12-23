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

        //first hands show
        for (User user: users){
            PrintWriter out = user.getOut();
            for (Card card: user.getHand().getCards()){
                out.print(card + " ");
            }
            user.getOut().println();
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
        for(int i = 0; i < users.size(); i++){
            int x = results.indexOf(Collections.min(results));
            String name = users.get(x).getName();
            for(User user: users){
                user.getOut().println(name + " takes place №" + (i + 1) + " with " + results.get(x) + " points.");
            }
            results.remove(x);
            results.add(x, 1000);
        }
        for (User user : users) {
            user.getOut().println("exit");
            user.close();
        }
    }
}
