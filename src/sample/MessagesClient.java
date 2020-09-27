package sample;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;

public class MessagesClient extends Thread {

        Socket client;
        int port = Controller.getPort_client();
        String ip = Controller.getIp();
     //   BufferedReader in_data, teclado;
      //  PrintStream out_data;
        String mms = "hola putos";
        private static DataOutputStream out_data;
        private static BufferedReader in_data;

        public MessagesClient(){

        }

        public void start(){
                try{
                        client = new Socket(ip, port);
                    System.out.println("gogogog");
                        in_data = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    System.out.println("jajaja");
                    out_data.writeUTF("message");
                    System.out.println("ddddd");
                    System.out.println("mm");


                }catch (Exception e){

                }
        }
}
