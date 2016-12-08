package classes;

import threads.Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 1 on 05.12.2016.
 */
public class Server {

    public static void main(String[] args) {
        new Server();
    }


    private List<Connection> connections =
            Collections.synchronizedList(new ArrayList<Connection>());
    private ServerSocket server;


    public Server() {
        try {
            server = new ServerSocket(3456);

            while (true) {
                Socket socket = server.accept();

                Connection con = new Connection(socket, connections);
                connections.add(con);

                con.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}