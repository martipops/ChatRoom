package server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private int id;

    ClientHandler(Socket socket) {
        this.socket = socket;
        try {
            dis = new DataInputStream(socket.getInputStream()); 
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public void run() {
        while (true) {
            try {
                String message = dis.readUTF();
                System.out.println("Client says: " + message);
                for (ClientHandler client : Server.clients) {
                    client.dos.writeUTF(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                Server.clients.remove(this);
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
