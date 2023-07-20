package iweb.client;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8888);
            new ClientThread(socket).start();
        } catch (IOException e) {
        }
    }
}
