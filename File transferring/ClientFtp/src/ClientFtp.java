import java.io.*;
import java.net.Socket;

public class ClientFtp {
    private Socket ftpS;
    private DataOutputStream out;
    private DataInputStream in;

    public ClientFtp(String host, int port, String file) throws IOException {

        ftpS = new Socket(host , port);
        out = new DataOutputStream(ftpS.getOutputStream());
        in = new DataInputStream(ftpS.getInputStream());
        sendingFile(file);

        out.close();
        in.close();
    }

    public void sendingFile(String file) throws IOException{
        int size = 0;
        File fi = new File(file);
        FileInputStream fiInput = new FileInputStream(fi);

        out.writeLong(fi.length());
        byte[] buffer = new byte[4*1024];

        while ((size = fiInput.read(buffer))!=-1){
            out.write(buffer,0,size);
            out.flush();
        }
        fiInput.close();

    }
    public static void main(String[] args) throws IOException {
        ClientFtp ftp = new ClientFtp("localhost", 30, "D:/Work/5th semster/ClientFtp/src/test.txt");

    }
    }

