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
        Deck deck = new Deck(false);
        deck.reshuffle();
        Deck discardpile = new Deck(true);

        //start hands
        for (User user : users) {
            for (int i = 0; i < 7; i++) {
                user.getHand().putCard(deck.draw());
            }
        }

        //first hands show
        for (User user : users) {
            user.handOut();
        }

        Card prevCard = null;

        //Game loop
        while (users.get(0).getHand().getCards().size() != 0 && users.get(1).getHand().getCards().size() != 0 && users.get(2).getHand().getCards().size() != 0 && users.get(3).getHand().getCards().size() != 0) {
            for (User user : users) {
                if (prevCard != null) {
                    if (prevCard.getValue() == 10) {
                        prevCard = new Card(-1, prevCard.getColor());
                        continue;
                    }
                    if (prevCard.getValue() == 12) {
                        user.getHand().takeCard(deck);
                        user.getHand().takeCard(deck);
                    } else if (prevCard.getValue() == 14) {
                        user.getHand().takeCard(deck);
                        user.getHand().takeCard(deck);
                        user.getHand().takeCard(deck);
                        user.getHand().takeCard(deck);
                    }
                }
                try {
                    for (User user1 : users) {
                        user1.getOut().println(user.getName() + " goes now: ");
                    }
                    user.handOut();
                    BufferedReader in = user.getIn();
                    boolean flag = false;
                    while (!flag) {
                        String message = in.readLine();
                        if (message.equals("draw")) {
                            if (user.getHand().isContainsPlayable(prevCard)) {
                                user.getOut().println("Sorry, you have a playable card. You can not draw.");
                            }
                            else {
                                user.getHand().takeCard(deck);
                                user.handOut();
                            }
                        } else {
                            long id = Long.parseLong(message);
                            if (user.getHand().isContainsId(id)) {
                                Card card = user.getHand().getCardById(id);
                                if (card.isPlayable(prevCard)) {
                                    prevCard = card;
                                    user.getHand().playCardById(id, discardpile);
                                    if (card.getValue() == 13 || card.getValue() == 14) {
                                        user.getOut().println("Choose color:");
                                        boolean flag2 = false;
                                        while (!flag2) {
                                            message = in.readLine();
                                            switch (message) {
                                                case "red":
                                                    card.setColor(Color.RED);
                                                    flag2 = true;
                                                    break;
                                                case "green":
                                                    card.setColor(Color.GREEN);
                                                    flag2 = true;
                                                    break;
                                                case "blue":
                                                    card.setColor(Color.BLUE);
                                                    flag2 = true;
                                                    break;
                                                case "yellow":
                                                    card.setColor(Color.YELLOW);
                                                    flag2 = true;
                                                    break;
                                                default:
                                                    user.getOut().println("There is now such color. Choose between red, blue, yellow and green.");
                                            }
                                        }
                                        for (User user1 : users) {
                                            user1.getOut().println("Color is " + message + " now.");
                                        }
                                    }
                                    for (User user1 : users) {
                                        PrintWriter out = user1.getOut();
                                        out.println(card);
                                    }
                                    user.handOut();
                                    flag = true;
                                } else {
                                    user.getOut().println("Try again.");
                                }
                            } else {
                                user.getOut().println("You have no such card. Try again.");
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //Results
        ArrayList<Integer> results = new ArrayList<>();
        for (User user : users) {
            int result = 0;
            for (Card card : user.getHand().getCards()) {
                if (card.getColor().equals(Color.BLACK)) {
                    result += 50;
                } else if (card.getValue() > 9) {
                    result += 20;
                } else {
                    result += card.getValue();
                }
            }
            results.add(result);
        }
        for (int i = 0; i < users.size(); i++) {
            int x = results.indexOf(Collections.min(results));
            String name = users.get(x).getName();
            for (User user : users) {
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
