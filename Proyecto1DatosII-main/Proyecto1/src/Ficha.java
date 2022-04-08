import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Base64;


public class Ficha{
   private int i;
   private int j;
   private String mensaje;
   public JButton boton;

   public Ficha(int i, int j){
       this.i=i;
       this.j=j;
       boton=new JButton();
       ActionListener evento=new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               Cliente c=new Cliente(getij());
               mensaje=c.getmensaje();
               ImageIcon imagen=new ImageIcon(decode(mensaje));
               boton.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(),Image.SCALE_SMOOTH)));
           }
       };
       boton.addActionListener(evento);
   }

   public String getij(){
       String retorno= String.valueOf(i)+String.valueOf(j);
       return retorno;
   }

   public static byte[] decode(String base64string){
       return Base64.getDecoder().decode(base64string);

   }
}


