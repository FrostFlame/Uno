package classes;

import entities.Deck;
import threads.Connection;

import java.util.List;

/**
 * Created by 1 on 11.12.2016.
 */
public class Game {
    private List<Connection> connections;

    public Game(List<Connection> connections) {
        this.connections = connections;
    }

    public String play() {
        Connection c = connections.get(0);
        c.getOut().println(c.getUserName() + " goes first");
        Deck deck = new Deck(false);
        Deck discardpile = new Deck(true);
        for (Connection connection : connections) {

        }
        return "";
    }
}
