package sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;

/***Clase encargada del manejo de mensajes de los clientes.
 *  Toma por entrada a la ip y puerto que se obtuvieron por las entradas de texto correspondientes.
 */
public class MessagesClient extends Thread {

    private static String messages;
    Socket client;
    private static BufferedReader in_data, data;
    private static PrintStream out_data;
    private int port;
    private String ip;
    private static String in_message;

    public static Logger log = LoggerFactory.getLogger(MessagesClient.class); //Logger

    /*** Constructor de la clase MessagesClient.
     *
     * @param port Es el puerto en el que el cliente está esperando una llamada.
     * @param ip  Es la dirección ip en el que se encuentra el otro cliente, si es local se deberá usar 127.0.0.1
     *
     */
    public MessagesClient(int port, String ip){
        this.port = port;
        this.ip = ip;
    }

    /*** Es la llamda iniciar el Thread
     *  Se encarga de crear la conexión con el Cliente mediente el puerto e ip dadas.
     */
    public void run(){
            log.debug("Conectando con:"+ ip+" "+port);
                try{
                    client = new Socket(ip, port); //Inicia la conexión con el cliente.
                    log.debug("Conexión exitosa con: "+ port);
                    in_data = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    data = new BufferedReader(new InputStreamReader(System.in));
                    out_data = new PrintStream(client.getOutputStream());
                    Thread incomingDataThreadClient = new Thread(()-> { //Crea un Thread para el manejo de mensajes nuevos.
                        while(true){
                            try {
                                in_message = in_data.readLine();
                                if (in_message!=null){
                                    Controller.setFlag(); //Cambia el estado del flag para poder mostrar los mensajes.
                                }
                            } catch (IOException e) {
                                log.error(e.getMessage(),e); //Envía el error al logger
                            }
                        }
                    });
                    incomingDataThreadClient.start(); //Inicia el Thread.
                }catch (Exception e){
                    log.error(e.getMessage(),e);
                }
        }

    /*** Envía mensajes nuevos
     * Envía por el out_data un nuevo mensaje.
     */
    public static void send() {
        messages = Controller.getMessage();
        out_data.println(messages);

    }

    /***Devuelve el mensaje nuevo.
     * @return Retorna el mesaje que se tiene en la entrada.
     */
    public static String getIn_message() {
        if (in_message == null){
            return "";
        }
        else{
            return in_message;
        }
    }
}
