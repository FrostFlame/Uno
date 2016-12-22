package classes;

import entities.Deck;
import entities.User;
//import threads.Connection;

import java.net.Socket;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 1 on 11.12.2016.
 */
public class Game {
    private List<User> users;

    public Game(List<User> users) {
        this.users = users;
    }

    public void play() {
        User c = users.get(0);
        for (User user : users) {
            user.getOut().println(c.getName() + " goes first.");
        }
        Deck deck = new Deck(false);
        Deck discardpile = new Deck(true);
        for (User user : users) {

        }
        String winner = users.get(0).getName();
        for (User user : users) {
            user.getOut().println(winner + " has won");
            user.getOut().println("exit");
            user.close();
        }
    }
}
