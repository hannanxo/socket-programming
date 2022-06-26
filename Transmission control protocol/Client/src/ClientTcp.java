import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientTcp {
    private DataOutputStream out;
    private DataInputStream in;

    public ClientTcp(String host, int port) throws IOException {

        Socket cc = new Socket(host, port);
        System.out.println("Client is running");

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter something: ");

         out = new DataOutputStream(cc.getOutputStream());
         in = new DataInputStream(cc.getInputStream());

        while(true)
        {
            String str = scan.nextLine();
            out.writeUTF(str);
            String clientMsg =  in.readUTF();
            System.out.println(clientMsg);

            if(str.equalsIgnoreCase("exit"))
                break;
        }

    }
    public static void main(String[] args) throws IOException {
        ClientTcp client = new ClientTcp("localhost", 40);
    }
}

