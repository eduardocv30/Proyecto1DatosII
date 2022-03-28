<<<<<<< HEAD
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class main {
    public static void main(String[] args) {
        Timer timer=new Timer();
        TimerTask tarea=new TimerTask() {
            Interfaz itf=new Interfaz("eduardo","otro");

            @Override
            public void run() {
                Cliente c=new Cliente("e");
                if (c.getmensaje() != null){
                    if(c.getmensaje().contains("p1")){
                        itf.p1++;
                        itf.label1.setText(itf.getnombre1()+": "+itf.p1);
                    }
                    if(c.getmensaje().contains("p2")){
                        itf.p2++;
                        itf.label2.setText(itf.getnombre2()+": "+itf.p2);
                    }
                    if (c.getmensaje().contains("d")){
                        int i=Character.getNumericValue(c.getmensaje().charAt(1));
                        int j=Character.getNumericValue(c.getmensaje().charAt(2));
                        itf.listaB[i][j].setEnabled(false);
                    }

                }
                
            }
            
        };

        timer.schedule(tarea, 0,500);
        
            }
        }
    
    

=======
import javax.swing.JButton;
public class main {
    public static void main(String[] args) {
        Interfaz itf=new Interfaz();
        int x=6;
        int y=7;
        int i=0;
        int j=0;
        JButton listaB[][]=new JButton[x][y];
        while(j<y){
            Ficha ficha=new Ficha(i, j);
            itf.add(ficha.boton);
            listaB[i][j]=ficha.boton;
            i++;
            if (i==x){
                i=0;
                j++;
            }
        }
        while (true){
            String mensaje = Ficha.mensaje;
            //System.out.println("hola");
            if(mensaje!=null){
                int i1=Character.getNumericValue(mensaje.charAt(0));
                int j1=Character.getNumericValue(mensaje.charAt(1));
                listaB[i1][j1].setText((String.valueOf(mensaje.charAt(2))));
                Ficha.falso();
            }
            
        }
    }
    
}
>>>>>>> 0eb6127593fda1a79a815ef0bfb535d1f37e2783
