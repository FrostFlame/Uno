package classes;

import mythreads.Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 05.12.2016.
 */
public class Server {
    private List<Connection> connectionPool;
    private final int PORT = 3456;

    private void init(){
        connectionPool = new ArrayList<>();
    }

    private void go(){
        try {
            ServerSocket ss = new ServerSocket(PORT);
            while (true) {
                Socket socket = ss.accept();
                Connection connection = new Connection(socket, connectionPool);
                connectionPool.add(connection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server(){init();}

    public static void main(String[] args) {
        (new Server()).go();
    }
}