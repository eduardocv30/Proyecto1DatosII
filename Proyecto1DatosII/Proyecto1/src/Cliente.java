import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente {
    private String Host="127.0.0.1";
    private int Port=9000;
    private DataInputStream input;
    private DataOutputStream output;
<<<<<<< HEAD
    private String mensaje;
=======
    private static String mensaje;
>>>>>>> 0eb6127593fda1a79a815ef0bfb535d1f37e2783
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
<<<<<<< HEAD
        return mensaje;  
=======
        return mensaje;
        
>>>>>>> 0eb6127593fda1a79a815ef0bfb535d1f37e2783
    }
}
