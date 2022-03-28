import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
               boton.setText(mensaje);
           }
       };
       boton.addActionListener(evento);
   }

   public String getij(){
       String retorno= String.valueOf(i)+String.valueOf(j);
       return retorno;
   }
}

