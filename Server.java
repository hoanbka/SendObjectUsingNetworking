package transfer.object;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;


    public static void main(String[] args) {
        try {

            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("Server started ");

            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("connected to client...");

                inputStream = new ObjectInputStream(socket.getInputStream());
                System.out.println("An object received from client is " + inputStream.readObject().toString());

                System.out.println("Input an object to send to client:");
                System.out.println("Host:");
                Scanner input = new Scanner(System.in);
                String host = input.next();
                System.out.println("Port:");
                int port = input.nextInt();

                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(new NetworkingObject(host, port));
                System.out.println("An object sent to client is " + new NetworkingObject(host, port).toString());

            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();

        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

