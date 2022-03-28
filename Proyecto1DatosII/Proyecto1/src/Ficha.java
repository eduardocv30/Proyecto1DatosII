<<<<<<< HEAD

=======
>>>>>>> 0eb6127593fda1a79a815ef0bfb535d1f37e2783
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ficha{
   private int i;
   private int j;
<<<<<<< HEAD
   private String mensaje;
=======
   public static String mensaje;
   public static boolean turno;
>>>>>>> 0eb6127593fda1a79a815ef0bfb535d1f37e2783
   public JButton boton;

   public Ficha(int i, int j){
       this.i=i;
       this.j=j;
       boton=new JButton();
       ActionListener evento=new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
<<<<<<< HEAD
               Cliente c=new Cliente(getij());
               mensaje=c.getmensaje();
               boton.setText(mensaje);
=======
               turno=true;
               Cliente c=new Cliente(getij());
               mensaje=c.getmensaje();
>>>>>>> 0eb6127593fda1a79a815ef0bfb535d1f37e2783
           }
       };
       boton.addActionListener(evento);
   }

   public String getij(){
       String retorno= String.valueOf(i)+String.valueOf(j);
       return retorno;
   }
<<<<<<< HEAD
=======

>>>>>>> 0eb6127593fda1a79a815ef0bfb535d1f37e2783
}


