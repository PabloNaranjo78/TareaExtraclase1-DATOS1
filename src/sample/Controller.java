package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import org.w3c.dom.Text;

import java.awt.*;
import javafx.scene.control.TextField;

import javax.management.StringValueExp;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public TextField textCampo;
    @FXML
    public TextField textCampo1;
    public TextArea mensajes;

    public void newMessage(ActionEvent actionEvent) {

        String op = String.valueOf(this.textCampo.getText());

        this.mensajes.setText(mensajes.getText()+"\n"+"Tu: "+ op);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
