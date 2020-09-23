package sample;
import java.net.*;
import java.io.*;
import java.awt.*;

public class MessagesServer extends Thread{
    public static String message;
    TextField messages_box;
    TextArea text_box;
    int port = 5001;
    ServerSocket server;
    Socket socket;
    DataOutputStream out_data;
    BufferedReader in_data;

    public String getMessage() {
        return message;
    }

    public static void setMessage(String messages) {
    }


    public MessagesServer(){


    }
    public void run(){
        class ServerConnection extends Thread{
            @Override
            public void run() {
                while (true){
                    try {
                        socket = server.accept();
                        in_data = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        out_data = new DataOutputStream(socket.getOutputStream());
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                while (!server.isClosed()){
                    message = Controller.getMessage();

                    try {
                        out_data.writeUTF(message);
                        System.out.println(message);
                        sleep(10);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                        try {
                            server.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    //   out_data.writeUTF(message);

                    if (message==null){

                    }
                    else{//System.out.println(message);
                        }
                }
            }
        }
            try{
                server = new ServerSocket(port);
                socket = new Socket();
                Thread serverConnection = new ServerConnection();
                serverConnection.start();

                System.out.println("acepta");

                System.out.println("lee");
                System.out.println(message);
           //     text_box.setText(text_box.getText() + "\n" + "Tu: " + message);
                System.out.println("lee");
             //   out_data = new DataOutputStream(socket.getOutputStream());
             //   out_data.writeUTF("sí funciona xd");
                socket.setKeepAlive(true);


    }catch (Exception e)
        {}
    }
}