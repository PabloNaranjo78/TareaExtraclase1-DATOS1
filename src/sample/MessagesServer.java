package sample;
import javafx.scene.control.TextArea;

import java.net.*;
import java.io.*;

public class MessagesServer extends Thread{
    public static String message;
    public TextArea text_box;

    private final int port = Controller.getPort();
    static ServerSocket server;
    Socket socket;
    private static DataOutputStream out_data;
    private static BufferedReader in_data;


    private static String in_message;

    public static String getIn_message() {
        if (in_message == null){
            return "$%$%";
        }
        else{
        return in_message;}
    }
    public MessagesServer(){
    }

    public static void send() {
        try {
            message = Controller.getMessage();
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
                Thread ttt = new Thread(()-> {
                    while(true){
                        try {
                            in_message = in_data.readLine();
                            if (in_message!=null){
                                System.out.println("Servidor:"+in_message);
                                Controller.setFlag();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                ttt.start();
                break;
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}