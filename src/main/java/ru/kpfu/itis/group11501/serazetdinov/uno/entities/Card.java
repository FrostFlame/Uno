package ru.kpfu.itis.group11501.serazetdinov.uno.entities;

/**
 * Created by 1 on 05.12.2016.
 */
public class Card {
    private long id;
    private String link;
    private int value; //10 - pass, 11 - reverse, 12 - draw 2, 13 - choose color, 14 - draw 4
    private String back;
    private Color color;

    public Card(long id, String link, int value, String back, Color color) {
        this.id = id;
        this.link = link;
        this.value = value;
        this.back = back;
        this.color = color;
    }

    public Card(long id, int value, Color color) {
        this.id = id;
        this.value = value;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Card(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    @Override
    public String toString() {
        return "(id=" + id +
                ", value=" + value +
                ", color=" + color + ")";
    }

    public boolean isPlayable(Card prevcard){
        return prevcard == null || this.color == Color.BLACK | this.color == prevcard.color | this.value == prevcard.value | (this.value == 10 & prevcard.value == -1);
    }
}
