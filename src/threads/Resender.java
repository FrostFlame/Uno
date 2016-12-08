package threads;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by 1 on 08.12.2016.
 */
public class Resender extends Thread {
    private BufferedReader in;
    private boolean stoped;

    public Resender(BufferedReader in) {
        this.in = in;
    }

    public void setStop() {
        stoped = true;
    }

    @Override
    public void run() {
        try {
            while (!stoped) {
                String str = in.readLine();
                System.out.println(str);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при получении сообщения.");
            e.printStackTrace();
        }
    }
}