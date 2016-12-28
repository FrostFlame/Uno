package ru.kpfu.itis.group11501.serazetdinov.uno.entities;

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
                for (int i = 1; i < 13; i++) {
                    cards.add(new Card(id, i, Color.RED));
                    id++;
                }
            }
            for (int j = 0; j < 2; j++) {
                for (int i = 1; i < 13; i++) {
                    cards.add(new Card(id, i, Color.YELLOW));
                    id++;
                }
            }
            for (int j = 0; j < 2; j++) {
                for (int i = 1; i < 13; i++) {
                    cards.add(new Card(id, i, Color.GREEN));
                    id++;
                }
            }
            for (int j = 0; j < 2; j++) {
                for (int i = 1; i < 13; i++) {
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

    public boolean isContainsId(long id) {
        for (Card card : cards) {
            if (card.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean isContainsPlayable(Card prevcard) {
        for (Card card : cards) {
            if (card.isPlayable(prevcard)) {
                return true;
            }
        }
        return false;
    }

    public Card draw() {//Вытащить карту из этой колоды
        Card draw = cards.get(0);
        cards.remove(0);
        return draw;
    }

    public void takeCard(Deck deck, Deck discardPile) {//Взять карту из другой колоды и положить в эту
        if (deck.getCards().size() == 0){
            int i = discardPile.getCards().size();
            for (int j = 0; j < i; j++){
                Card card = discardPile.draw();
                deck.putCard(card);
            }
            deck.reshuffle();
        }
        Card card = deck.draw();
        this.putCard(card);
    }

    public void putCard(Card card) {//Положить карту в колоду
        cards.add(card);
    }

    public Card getCardById(long card_id) {
        for (Card card : cards) {
            if (card.getId() == card_id)
                return card;
        }
        return null;
    }

    public void playCardById(long card_id, Deck discardPile) {
        Card card = this.getCardById(card_id);
        discardPile.getCards().add(card);
        this.getCards().remove(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void reshuffle() {
        Collections.shuffle(cards);
    }
}
