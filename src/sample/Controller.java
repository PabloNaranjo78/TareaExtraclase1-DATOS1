package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;



import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    private static String message = "";
    private static int port = (int)(Math.random()*(60000-5000+1)+5000);



    private static String ip;

    private static int port_client;
    public TextField messages_box;
    public TextArea text_box;
    public TextField address;
    public TextField new_chat_port;
    public TextField new_chat_ip;

    public static int getPort() {
        return port;
    }

    public static String getMessage() {
        return message;

    }


    public void getMessages_box () {

    }

    public void newMessage() throws IOException {
        message = messages_box.getText();
        text_box.setText(text_box.getText()+"\n"+"Tu: "+ message);

        MessagesServer.send();
     //   System.out.println("j"+message);
        

/*        String op = String.valueOf(this.messages_box.getText());

        this.text_box.setText(text_box.getText()+"\n"+"Tu: "+ op);

       */

        //service.startService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        address.setText("127.0.0.1:"+port);


    }

    public void newChat(ActionEvent actionEvent) throws IOException {
        try{
        port_client = Integer.valueOf( new_chat_port.getText());
        ip = new_chat_ip.getText();
        Thread messagesClient = new MessagesClient();
        messagesClient.start();}
        catch (Exception e){
            System.out.println("Dato inválido de ip y puerto");
        }

    }

    public static int getPort_client() {
        return port_client;
    }

    public static String getIp() {
        return ip;
    }

    }

