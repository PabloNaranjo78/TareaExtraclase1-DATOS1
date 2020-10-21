package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



/***La clase controller es la principal encargada del manejo de la interfaz gráfica y de a donde se mandandarán
 * las funciones, si al MessagesServer o al MessagesClient. El cógido de la interfaz se encuentra en el archivo
 * sample.fxml
 */
public class Controller implements Initializable {

    /**
     * Aquí se declaran funciones importantes para el manejo del servidor y cliente, así como las variables
     * correspondientes a los id de los elementos de la interfaz gráfica.
     */
    private static String message = "";
    private static int port = (int)(Math.random()*(60000-5000+1)+5000); //Genera un número aleatorio entre 5000 y 60000
    public TextField messages_box;
    public TextArea text_box;
    public TextField address;
    public TextField new_chat_port;
    public TextField new_chat_ip;
    public static boolean sending = false;
    public static boolean flag = true;
    String incomingMessages;

    /***
     * Esta función se ejecuta cuando se inicia la interfaz gráfica, pondrá en el cuadro de la ip el valor que se le
     * asignó aleatoriamente, también llama a la función startReceiveMessages() para también empezar la espera de
     * una entrada de mensaje.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        address.setText("127.0.0.1:"+port);
        startReceiveMessages();
    }

    /***
     * Dentro de esta función se crea un Thread con un Lambda para esperar mensajes nuevos y así poderlos mostrar
     * en pantalla.
     */
    public void startReceiveMessages(){

        Thread incomingData = new Thread(()->{
            while(true){
                if (sending){
                    incomingMessages = MessagesClient.getIn_message();
                }else{
                    incomingMessages = MessagesServer.getIn_message();
                }
                if (flag){ //Este flag será verdadero cuando haya un mensaje nuevo.
                    text_box.setText(text_box.getText()+"\n"+"Amigo"+": "+ incomingMessages);
                    flag = false;
                }else{
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }}
            }

        });

        incomingData.start(); //Inicia el Thread.
    }

    /***Esta función es accionada por el botón de "Enviar".
     *También obtiene el las palabras de la caja de texto de enviar mensajes y la pasa al cajón con los todos los
     * mensajes.
     */
    public void newMessage() {
        message = messages_box.getText();
        text_box.setText(text_box.getText()+"\n"+"Tu: "+ message);
        messages_box.setText("");
        /* sending funciona como selector de si se debe responder a un cliente que llama o a un cliente que ha sido
        * llamado */
        if (sending){
            MessagesClient.send();
        }else{
            MessagesServer.send();
        }
    }

    /*** Es accionada por el botón de Nuevo Chat.
     * Toma a los valores que se encuentren en las casillas de ip y puerto, para así enviarlos a la clase MessagesClient
     */
    public void newChat(ActionEvent actionEvent) {
        try{
        String ip = new_chat_ip.getText();
        int port_client = Integer.valueOf(new_chat_port.getText());
        System.out.println("Iniciando conexión");
        Thread startNewChat = new MessagesClient(port_client, ip);
        setReceiveOrSend();
        startNewChat.start();}
       catch (Exception e){
           System.out.println("IP o Puerto inválido");
       }
    }

    /***
     * Cambia el flag al valor de true.
     */
    public static void setFlag(){
        flag = true;
    }

    /***
     * Cambia el sending a true.
     */
    public static void setReceiveOrSend(){
        sending = true;
    }

    /***
     * Devuelve el pueto
     * @return retorna el puerto asignado al usuario.
     */
    public static int getPort() {
        return port;
    }

    /***
     * Devuelve el mensaje
     * @return retorna un string con el contenido del mensaje
     */
    public static String getMessage() {
        return message;
    }
    }

