package client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerHandler implements Runnable {

    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;

    public ServerHandler() {
        try {
            socket = new Socket("localhost", 8080);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToServer(String s) {
        try {
            dos.writeUTF(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String response = dis.readUTF();
                System.out.println("Server says: " + response);
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socket.close();
                    dis.close();
                    dos.close();
                } catch (Exception r) {
                    r.printStackTrace();
                }
                return;
            }
        }
    }
}
