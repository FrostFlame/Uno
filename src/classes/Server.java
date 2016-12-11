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
                List<Connection> roomconnectionslist = Collections.synchronizedList(new ArrayList<Connection>());
                while (roomconnectionslist.size() < 4) {
                    Socket socket = server.accept();

//                    Connection con = new Connection(socket, connections);
                    Connection con = new Connection(socket, roomconnectionslist);
                    roomconnectionslist.add(con);
//                    connections.add(con);
                    con.start();


                    Game game = new Game(connections);
                    String winner = game.play();
                    synchronized (connections) {
                        Iterator<Connection> iter = connections.iterator();
                        while (iter.hasNext()) {
                            ((Connection) iter.next()).getOut().println(winner + " has won");
                        }
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}