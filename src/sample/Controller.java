package sample;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;



import javafx.scene.control.TextField;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    private static String message = "";
    private static int port = (int)(Math.random()*(60000-5000+1)+5000);
  //private static int port = 999;



    public TextField messages_box;
    public TextArea text_box;
    public TextField address;
    public TextField new_chat_port;
    public TextField new_chat_ip;
    public static boolean sending = false;
//    public String iincomingMessages = "putos todos";
    public static boolean flag = true;
    String incomingMessages;

    public static int getPort() {
        return port;
    }

    public static String getMessage() {
        return message;
    }

    public void startReceiveMessages(){

        Thread incomingData = new Thread(()->{
            while(true){
                if (sending){
                    incomingMessages = MessagesClient.getIn_message();
                }else{
                    incomingMessages = MessagesServer.getIn_message();
                }

                if (flag){
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

        incomingData.start();
    }

    public void getMessages_box () {

    }


    public void newMessage() throws IOException {
        message = messages_box.getText();
        text_box.setText(text_box.getText()+"\n"+"Tu: "+ message);
        if (sending){
            MessagesClient.send();
        }else{
            MessagesServer.send();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        address.setText("127.0.0.1:"+port);
        startReceiveMessages();
      //  startReceiveMessages();


    }

    public void newChat(ActionEvent actionEvent) throws IOException {
        String ip = new_chat_ip.getText();
        int port_client = Integer.valueOf(new_chat_port.getText());
        System.out.println("Iniciando conexión");
        Thread startNewChat = new MessagesClient(port_client, ip);
        setReceiveOrSend();
        startNewChat.start();
    }


    public static void setFlag(){
        flag = true;
    }

    public static void setReceiveOrSend(){
        sending = true;
    }

    }

