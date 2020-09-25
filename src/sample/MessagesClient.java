package sample;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;

public class MessagesClient extends Thread {

        Socket client;
        int port = Controller.getPort_client();
        String ip = Controller.getIp();
        BufferedReader in_data, teclado;
        PrintStream out_data;
        String mms = "hola putos";


        public MessagesClient(){

        }

        public void start(){
                try{
                        client = new Socket(ip, port);
                        in_data = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        teclado = new BufferedReader(new InputStreamReader( mms ));


                }catch (Exception e){

                }
        }
}
