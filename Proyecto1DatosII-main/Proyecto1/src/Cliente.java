import java.io.BufferedInputStream;

import java.io.DataOutputStream;

import java.net.Socket;



public class Cliente { //inicio el cliente cada vez que deseo enviar y reccibir un nuevo mensaje
    private String Host="127.0.0.1"; //local host
    private int Port=8000;
    private BufferedInputStream input;
    private DataOutputStream output;
    private String mensaje; 
    public Cliente(String envio){
        try {
            Socket socket=new Socket(Host,Port);
            output = new DataOutputStream(socket.getOutputStream());
            input=new BufferedInputStream(socket.getInputStream());
            byte[] buffer=new byte[25000]; //creo un byte[] para recibir la imagen en base 64
            int bytesRead=0;
            output.writeUTF(envio);
            while((bytesRead=input.read(buffer))!=-1){
                mensaje=new String(buffer,0,bytesRead); //utilizó un buffer para obtener el mensaje desde c++
                break;
            }
            socket.close();
        } catch (Exception e) {
        }
    }
    public String getmensaje() { //retorna el mensaje
        return mensaje;  
    }
}
  