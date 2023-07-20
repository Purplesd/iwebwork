package iweb.util;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class IOManager {
    public static void write(String str, Socket socket) throws IOException {
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos=new DataOutputStream(os);
        dos.writeUTF(str);
    }
    public static String read(Socket socket) throws IOException {
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        return dis.readUTF();
    }
}
