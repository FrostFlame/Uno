package mythreads;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Created by 1 on 05.12.2016.
 */
public class Connection implements Runnable {
    private Thread thread;
    private Socket socket;
    private List<Connection> connections;

    public Connection(Socket socket, List<Connection> connections) {
        this.thread = new Thread(this);
        this.socket = socket;
        this.connections = connections;
        this.thread.start();
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream in = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while (!socket.isClosed()) {
                for (Connection connection: connections){
                    PrintWriter pw = new PrintWriter(connection.socket.getOutputStream(), true);
                    pw.println(connections.indexOf(connection) + 1);
                }
                for (Connection connection: connections){
                    OutputStream os = connection.socket.getOutputStream();
                    PrintWriter pw = new PrintWriter(os, true);
                    String s = br.readLine();
                    pw.println(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
