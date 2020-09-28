package sample;
import java.io.*;
import java.net.*;

import static java.lang.System.in;
import static java.lang.System.setOut;

public class MessagesClient extends Thread {

    private static String messages;
    Socket client;
//        int port = Controller.getPort_client();
  //      String ip = Controller.getIp();
     //   BufferedReader in_data, teclado;
      //  PrintStream out_data;
        String mms = "hola putos";
        //private static DataOutputStream out_data;
        private static BufferedReader in_data, data;
        private static PrintStream out_data;

    private int port;
    private String ip;
    private static String in_message;

    /***
     *
     * @param port
     * @param ip
     */
    public MessagesClient(int port, String ip){
        this.port = port;
        this.ip = ip;
    }

    public static String getIn_message() {
        if (in_message == null){
            return "";
        }
        else{
            return in_message;
        }
    }

    public void run(){
            System.out.println("Conectando con:"+ ip+" "+port);
                try{
                    client = new Socket(ip, port);
                    System.out.println("Conexión exitosa con: "+ port);
                    in_data = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    data = new BufferedReader(new InputStreamReader(System.in));
               //     String inMessages = data.readLine();

                    out_data = new PrintStream(client.getOutputStream());
                    Thread incomingDataThreadClient = new Thread(()-> {
                        while(true){
                            try {
                                in_message = in_data.readLine();
                                if (in_message!=null){
                                    System.out.println("inC:"+in_message);
                                    Controller.setFlag();
                                }
                            } catch (IOException e) {}
                    });
                    incomingDataThreadClient.start();



                }catch (Exception e){
                    System.out.println("conexión fallida");
                }
        }
    public static void send() {
        messages = Controller.getMessage();
        out_data.println(messages);

    }
}
