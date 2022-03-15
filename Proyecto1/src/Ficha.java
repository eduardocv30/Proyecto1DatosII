import javax.swing.Action;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class Ficha {
   private int i;
   private int j;
   public JButton boton;

   public Ficha(int i, int j){
       this.i=i;
       this.j=j;
       boton=new JButton();
       ActionListener evento=new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               enviar(getij());
           }
       };
       boton.addActionListener(evento);
   }

   public String getij(){
       String retorno= String.valueOf(i)+String.valueOf(j);
       return retorno;
   }

   public void enviar(String mensaje){
       String Host="127.0.0.1";
       int Port=9000;
       DataInputStream input;
       DataOutputStream output;
       try {
           Socket socket=new Socket(Host,Port);

           output = new DataOutputStream(socket.getOutputStream());
           output.writeUTF(mensaje);
           socket.close();


       } catch (Exception e) {
       }
   }
}
