package ru.kpfu.itis.group11501.serazetdinov.uno.classes;
/**
 * Created by 1 on 05.12.2016.
 */

import ru.kpfu.itis.group11501.serazetdinov.uno.threads.Resender;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;

//    public static void main(String[] args) {
//        new Client();
//    }

    public Client() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Введите IP для подключения к серверу");
        System.out.println("Формат: xxx.xxx.xxx.xxx");

        String ip = scan.nextLine();

        try {
            try {
                socket = new Socket(ip, 3456);
            }catch (UnknownHostException e){
                System.out.println("Такого хоста нет.");
                close();
            }
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Введите свой ник:");
            out.println(scan.nextLine());

            Resender resend = new Resender(in);
            resend.start();

            String str = "";
            while (!str.equals("exit") & resend.isAlive()) {
                str = scan.nextLine();
                out.println(str);
            }
            resend.setStop();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }


    private void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Такого хоста нет.");
        }
    }
}