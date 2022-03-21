import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.net.ServerSocket;
import java.net.Socket;

public class Cliente {
    private String Host="127.0.0.1";
    private int Port=9000;
    private DataInputStream input;
    private DataOutputStream output;
    private static String mensaje;
    public Cliente(String envio){
        try {
            Socket socket=new Socket(Host,Port);
            output = new DataOutputStream(socket.getOutputStream());
            input=new DataInputStream(socket.getInputStream());
            output.writeUTF(envio);
            mensaje=input.readUTF();
            socket.close();
        } catch (Exception e) {
        }
    }
    public String getmensaje() {
        return mensaje;
        
    }
}
