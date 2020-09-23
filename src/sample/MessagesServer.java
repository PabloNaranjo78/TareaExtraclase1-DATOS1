package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MessagesServer {
    public static void main(String[] args) throws IOException {
        int port = 5000;

        ServerSocket serverSocket = new ServerSocket(port);

        while(true){
            Socket socket= null;


        try{
            socket = serverSocket.accept();

            System.out.println(socket+"");

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());


            Thread thread = new ClientHandler(serverSocket,dis,dos);
            thread.start();
        } catch (Exception e){
            serverSocket.close();
            e.printStackTrace();
        }
        }
    }

}