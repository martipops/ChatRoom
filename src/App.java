import java.util.Scanner;

import client.Client;
import server.Server;

public class App {
    public static void main(String[] args) {
        // Ask user if they want to run the server or the client
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to run the server or the client?");
        System.out.println("1. Server");
        System.out.println("2. Client");
        int choice = scanner.nextInt();
        if (choice == 1) {
            // Run the server
            Server.main(args);
        } else if (choice == 2) {
            // Run the client
            Client.main(args);
        } else {
            System.out.println("Invalid choice");
        }
    }
}
