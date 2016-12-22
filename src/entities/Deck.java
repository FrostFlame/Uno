package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 1 on 09.12.2016.
 */
public class Deck {
    private List<Card> cards;

    public Deck(boolean empty) {
        cards = new ArrayList<>();
        if (!empty) {
            int id = 1;
            cards.add(new Card(id, 0, Color.RED));
            id++;
            cards.add(new Card(id, 0, Color.YELLOW));
            id++;
            cards.add(new Card(id, 0, Color.GREEN));
            id++;
            cards.add(new Card(id, 0, Color.BLUE));
            id++;
            for (int j = 0; j < 2; j++) {
                for (int i = 1; i < 12; i++) {
                    cards.add(new Card(id, i, Color.RED));
                    id++;
                }
            }
            for (int j = 0; j < 2; j++) {
                for (int i = 1; i < 12; i++) {
                    cards.add(new Card(id, i, Color.YELLOW));
                    id++;
                }
            }
            for (int j = 0; j < 2; j++) {
                for (int i = 1; i < 12; i++) {
                    cards.add(new Card(id, i, Color.GREEN));
                    id++;
                }
            }
            for (int j = 0; j < 2; j++) {
                for (int i = 1; i < 12; i++) {
                    cards.add(new Card(id, i, Color.BLUE));
                    id++;
                }
            }
            for (int i = 0; i < 4; i++) {
                cards.add(new Card(id, 13, Color.BLACK));
                id++;
            }
            for (int i = 0; i < 4; i++) {
                cards.add(new Card(id, 14, Color.BLACK));
                id++;
            }
        }
    }

    public Card draw(){
        Card draw = cards.get(0);
        cards.remove(0);
        return draw;
    }

    public void putCard(Card card){
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void reshuffle(){
        Collections.shuffle(cards);
    }
}
