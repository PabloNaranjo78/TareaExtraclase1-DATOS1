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
  //  private static int port = (int)(Math.random()*(60000-5000+1)+5000);
  private static int port = 999;


    private static String ip;

    private static int port_client = 0;
    public TextField messages_box;
    public TextArea text_box;
    public TextField address;
    public TextField new_chat_port;
    public TextField new_chat_ip;
//    public String iincomingMessages = "putos todos";
    public static boolean flag = true;

    public static int getPort() {
        return port;
    }

    public static String getMessage() {
        return message;

    }


    public void startReceiveMessages(){
        Thread incomingData = new Thread(()->{
            try{
            while(true){
                String incomingMessages = MessagesServer.getIn_message();
                if (incomingMessages == null){

                }else{
                    text_box.setText(text_box.getText()+"\n"+"Amigo"+": "+ incomingMessages);
                    flag = false;
                }
            }}catch (Exception )

        });
        incomingData.start();
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
      //  startReceiveMessages();


    }

    public void newChat(ActionEvent actionEvent) throws IOException {
        startReceiveMessages();
    /*    try{
        port_client = Integer.valueOf( new_chat_port.getText());
        ip = new_chat_ip.getText();
        Thread messagesClient = new MessagesClient();
        messagesClient.start();

        }
        catch (Exception e){
            System.out.println("Dato inválido de ip y puerto");
        }
*/
    }

    public static int getPort_client() {
        return port_client;
    }

    public static void setFlag(){
        flag = true;
    }
    public static String getIp() {
        return ip;
    }

    }

