package sample;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;



import javafx.scene.control.TextField;


import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    private static String message = "p";
    public TextField messages_box;
    public TextArea text_box;

    public static String getMessage() {
        return message;
    }


    public void getMessages_box () {

    }


    Thread service = new MessagesServer();

    public void newMessage() {
        message = messages_box.getText();
        text_box.setText(text_box.getText()+"\n"+"Tu: "+ message);

     //   System.out.println("j"+message);
        

/*        String op = String.valueOf(this.messages_box.getText());

        this.text_box.setText(text_box.getText()+"\n"+"Tu: "+ op);

       */

        //service.startService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void newChat(ActionEvent actionEvent) {

    }
}
