import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFtp{

    private ServerSocket ss;
    private DataInputStream in;
    private DataOutputStream out;
    public ServerFtp(int port) throws IOException {

        ss = new ServerSocket(port);
        System.out.println("Listening to port 30");
        Socket listenSocket = ss.accept();
        System.out.println("Client is connected to the server");

        in = new DataInputStream(listenSocket.getInputStream());
        out = new DataOutputStream((listenSocket.getOutputStream()));

        saveFile("test.txt");

        System.out.println("Following file is received on the server: " + "test.txt");
        in.close();
        out.close();
        listenSocket.close();
    }

    private void saveFile(String name) throws IOException, FileNotFoundException {
    int size = 0;
        FileOutputStream fiOut = new FileOutputStream(name);
        byte[] buffer = new byte[4*1024];

        long len = in.readLong();

        while (len > 0 && (size = in.read(buffer, 0, (int)Math.min(buffer.length, len))) != -1) {
            fiOut.write(buffer,0,size);
            len -= size;
        }

        fiOut.close();

    }
    public static void main(String[] args) throws IOException {
        ServerFtp sftp = new ServerFtp(30);
    }
}
