package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/*** Clase principal de la aplicaición.
 * @author Pablo Naranjo Monge
 * Recomendaciones: estar revisando la consola en caso de mensajes, por ejemplo, el estado de
 * conección con otro cliente, también recordar que la aplicación, en caso de que se ejecute dentro de una misma
 * computadora se deberá abrir dos veces el proyecto y ejecutar el Main, para así tener dos ventanas y poder
 * interactuar entre ellas.
 */
public class Main extends Application {

    /*** Lanzador de la interfaz gráfica
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("ChatApp");
        primaryStage.setScene(new Scene(root, 800, 500));

        primaryStage.show();
    }

    /*** Main de la clase, crea un nuevo MessagesServer() en un Thread, esto para que se ejecute por una parte la
     * interfaz gráfica y por otro lado las funciones lógicas que hacen posible la utización del socket.
     */
    public static void main(String[] args) {
        Thread service = new MessagesServer();
        service.start();
        launch(args);

    }
}



