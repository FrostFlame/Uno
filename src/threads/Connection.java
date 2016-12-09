package threads;

import classes.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 1 on 08.12.2016.
 */
public class Connection extends Thread {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private List<Connection> connections;

    private String name = "";

    public Connection(Socket socket, List<Connection> connections) {
        this.socket = socket;
        this.connections = connections;

        try {
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
            close();
        }
    }

    @Override
    public void run() {
        try {
            name = in.readLine();
            synchronized (connections) {
                Iterator<Connection> iter = connections.iterator();
                while (iter.hasNext()) {
                    ((Connection) iter.next()).out.println(name + " cames now");
                }
            }

            String str = "";
            while (true) {
                try {
                    str = in.readLine();
                }catch (SocketException e){
                    break;
                }
                if (str.equals("exit")) break;

                synchronized (connections) {
                    Iterator<Connection> iter = connections.iterator();
                    while (iter.hasNext()) {
                        ((Connection) iter.next()).out.println(name + ": " + str);
                    }
                }
            }

            synchronized (connections) {
                Iterator<Connection> iter = connections.iterator();
                while (iter.hasNext()) {
                    ((Connection) iter.next()).out.println(name + " has left");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();

            connections.remove(this);
        } catch (Exception e) {
            System.err.println("Ошибка в методе close() сервера.");
        }
    }
}
