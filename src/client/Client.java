package client;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServerHandler serverHandler = new ServerHandler();
        Thread handlerThread = new Thread(serverHandler);
        handlerThread.start();

        while (true) {
            String message = scanner.nextLine();
            serverHandler.sendToServer(message);
        }

    }
}
