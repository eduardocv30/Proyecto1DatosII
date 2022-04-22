import java.awt.Image;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class main {
    public static void main(String[] args) {
        Timer timer=new Timer(); // utilizo un timer para reducir el número de consultas innecesarias al servidor
        String nombre1=JOptionPane.showInputDialog("nombre del jugador 1: ");
        String nombre2=JOptionPane.showInputDialog("nombre del jugador 2: ");
        Interfaz itf=new Interfaz(nombre1,nombre2);
        TimerTask tarea=new TimerTask() {
            @Override
            
            public void run() {
                Cliente c=new Cliente("rev"); // indico al servidor que quiero revisar el estado actual
                if (c.getmensaje() != null && c.getmensaje().contains("n")==false){
                    if(c.getmensaje().contains("p")){//si contienen p indica el turno del jugador
                        if (c.getmensaje().contains("p1")){
                            JOptionPane.showMessageDialog(null,"turno del jugador 1");
                            if(c.getmensaje().length()>2){ // si el mensaje contiene más de 2 digitos es porque esta activo el power up (p1r)
                                JOptionPane.showMessageDialog(null,"el jugador 1 puede remontar");
                            }
                        
                        }
                        //mismo logica pero con el jugador 2
                        else{
                            JOptionPane.showMessageDialog(null,"turno del jugador 2");
                            if(c.getmensaje().length()>2){
                                JOptionPane.showMessageDialog(null,"el jugador 2 puede remontar");
                            }
                        }
                    }else{
                        try {// es necesario por Thread del metodo diferentes
                            manipulador manipulador=new manipulador(c.getmensaje(), itf);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block

                            e.printStackTrace();
                        }
                        
                    }
                
                }
            }
        };

        timer.schedule(tarea, 0,100);
        
            }
        }
    