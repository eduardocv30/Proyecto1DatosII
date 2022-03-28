import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class prueba {
    public static void main(String[] args) {
        ServerSocket servidor=null;
        Socket socket=null;
        int PUERTO=9000;
        DataInputStream input;
        DataOutputStream output;
        int n=0;
        try {
            servidor=new ServerSocket(PUERTO);

            while (true){
                socket=servidor.accept(); 
                input=new DataInputStream(socket.getInputStream());
                output=new DataOutputStream(socket.getOutputStream());
                String mensaje=input.readUTF();
                System.out.println("soy yo "+mensaje);
                if(mensaje.contains("e")){
                    if(n==0){
                        output.writeUTF("p1");
                        n++;}
                    else if(n==1){
                        output.writeUTF("p2");
                        n++;}
                    else if(n==2){
                        output.writeUTF("d12");
                        n=0;
                    }
                }
                else{
                    output.writeUTF("ho");

                }
                socket.close();

            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
