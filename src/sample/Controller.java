package sample;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;



import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public TextField messages_box;
    public TextArea text_box;
    MessagesServer service = new MessagesServer();

    public void newMessage(ActionEvent actionEvent) {
     //   text_box.setText(text_box.getText()+"\n"+"Tu: "+ "text_messages");

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
