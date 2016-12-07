package classes;
/**
 * Created by 1 on 05.12.2016.
 */

import mythreads.ClientThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String args[]) {
        try {
            Socket socket = new Socket("localhost", 3456);
            Client c = new Client();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()
            ));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            String s;
            Scanner sc = new Scanner(System.in);
            s = br.readLine();
            if (s.equals("1")){
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