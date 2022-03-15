import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.net.ServerSocket;
import java.net.Socket;

public class Recibir {
    public static void main(String[] args) {
        Interfaz intf=new Interfaz();
        ServerSocket servidor=null;
        Socket socket=null;
        int PUERTO=9000;
        DataInputStream input;
        DataOutputStream output;
        try {
            servidor=new ServerSocket(PUERTO);

            while (true){
                socket=servidor.accept(); 
                input=new DataInputStream(socket.getInputStream());
                output=new DataOutputStream(socket.getOutputStream());
                String mensaje=input.readUTF();
                System.out.println("soy el servidor "+mensaje);
                socket.close();

            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
    

    