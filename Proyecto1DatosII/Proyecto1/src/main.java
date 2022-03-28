
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
    
