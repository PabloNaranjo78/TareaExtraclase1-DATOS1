package sample;
import java.net.*;
import java.io.*;

public class MessagesServer extends Thread{
    public static String message;


    private int port = Controller.getPort();
    static ServerSocket server;
    Socket socket;
    static DataOutputStream out_data;
    static BufferedReader in_data;


    public String getMessage() {
        return message;
    }

    public MessagesServer(){
    }

    public static void send() throws IOException {
        try {
            message = Controller.getMessage();
          //  String mm = in_data.readLine();
            out_data.writeUTF(message);
            System.out.println(message);
        } catch (IOException e) {
            System.out.println("Se ha perdido la conexión");
        }
    }

    public void run() {
        System.out.println(port+"");
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        socket = new Socket();
        while (true) {

            try {

                socket = server.accept();
                in_data = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out_data = new DataOutputStream(socket.getOutputStream());

                break;
            } catch (IOException e) {


            }

        }
    }
}