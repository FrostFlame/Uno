package mythreads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()
            ));
            while (!socket.isClosed()) {
                for (Connection connection: connections){
                    PrintWriter pw = new PrintWriter(connection.socket.getOutputStream(), true);
                    pw.println(connections.indexOf(connection) + 1);
                }
                for (Connection connection: connections){
                    PrintWriter pw = new PrintWriter(connection.socket.getOutputStream(), true);
                    String s = br.readLine();
                    pw.println(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
