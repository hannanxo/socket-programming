import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTcp {
    private DataOutputStream out;
    private DataInputStream in;
    private ServerSocket serverSocket;
    private Socket listenSocket;

    public void initialize(int port) throws IOException {
       serverSocket = new ServerSocket(port);
       System.out.println("Listening to port 40");
       listenSocket = serverSocket.accept();
       System.out.println("Client is connected to the server");

        in = new DataInputStream(listenSocket.getInputStream());
        out = new DataOutputStream(listenSocket.getOutputStream());

        while(true){
            String checkIn = in.readUTF();
            out.writeUTF("Server : Message received successfully");

            if(checkIn.equalsIgnoreCase("exit")){
                break;
            }
            System.out.println("Client: " + checkIn);
        }

    }
    public static void main(String[] args) throws IOException {
        ServerTcp ss = new ServerTcp();
        ss.initialize(40);

    }

}
