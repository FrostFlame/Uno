package mythreads;

import classes.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread {
    private Socket socket;
    private Client client;
    private Scanner sc = new Scanner(System.in);

    public ClientThread(Socket s, Client j) {
        this.socket = s;
        this.client = j;
    }

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()
            ));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            String s;

            s = br.readLine();
            if (s.equals("1")) {
                s = sc.nextLine();
                pw.println(s);
            }
            while (true) {
                s = br.readLine();
                System.out.print("Opponent: ");
                System.out.println(s);
                s = sc.nextLine();
                pw.println(s);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}