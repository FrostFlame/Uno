package ru.kpfu.itis.group11501.uno.classes;

import java.util.Scanner;

/**
 * Created by 1 on 25.12.2016.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Choose: server or client");
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        while (!flag) {
            String in = sc.next();
            switch (in) {
                case "server":
                    new Server();
                    flag = true;
                    break;
                case "client":
                    new Client();
                    flag = true;
                    break;
                default:
                    System.out.println("There is no such choise. Choose again");
                    break;
            }
        }
    }
}
