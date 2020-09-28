package sample;
import javafx.scene.control.TextArea;

import java.net.*;
import java.io.*;

public class MessagesServer extends Thread{
    public static String message;

    private final int port = Controller.getPort();
    static ServerSocket server;
    Socket socket;
    private static PrintStream out_data;
    private static BufferedReader in_data;


    private static String in_message;

    public static String getIn_message() {
        if (in_message == null){
            return "";
        }
        else{
            return in_message;
        }
    }
    public MessagesServer(){
    }

    public static void send() {
        message = Controller.getMessage();
        out_data.println(message);
        System.out.println(message);
    }

    public void run() {
        System.out.println(port);
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        socket = new Socket();
        while (true) {
            try {
                socket = server.accept();
                System.out.println("conexión entrante lista");
                in_data = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out_data = new PrintStream(socket.getOutputStream());
                Thread incomingDataThreadServer = new Thread(()-> {
                    while(true){
                        try {
                            in_message = in_data.readLine();
                            if (in_message!=null){
                                System.out.println("inS:"+in_message);
                                Controller.setFlag();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                incomingDataThreadServer.start();
                break;
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}