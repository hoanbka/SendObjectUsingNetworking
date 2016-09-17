package transfer.object;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static ObjectInputStream inputStream;
    private static ObjectOutputStream outputStream;
    private static Socket socket;


    public static void main(String[] args) {
        try {

            socket = new Socket("localhost", 9999);

            System.out.println("Input an object to send to server:");
            System.out.println("Host:");
            Scanner input = new Scanner(System.in);
            String host = input.next();
            System.out.println("Port:");
            int port = input.nextInt();

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(new NetworkingObject(host, port));
            System.out.println("An object sent to server is " + new NetworkingObject(host, port).toString());

            inputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("An object received from server is " + inputStream.readObject().toString());

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
