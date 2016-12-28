package ru.kpfu.itis.group11501.serazetdinov.uno.classes;

import ru.kpfu.itis.group11501.serazetdinov.uno.entities.User;
//import main.java.ru.kpfu.itis.group11501.uno.threads.Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 05.12.2016.
 */
public class    Server {

//    public static void main(String[] args) {
//        new Server();
//    }


    private ServerSocket server;


    public Server() {
        try {
            server = new ServerSocket(3456);

            while (true) {
                List<User> users = new ArrayList<User>();
                while (users.size() < 4) {
                    Socket socket = server.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));
                    User user = new User(users.size(), in.readLine(), socket);
                    users.add(user);
                }
                Game game = new Game(users);
                game.play();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}