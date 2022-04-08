import java.util.Timer;
import java.util.TimerTask;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class main {
    public static void main(String[] args) {
        Timer timer=new Timer();
        String nombre1=JOptionPane.showInputDialog("nombre del jugador 1: ");
        String nombre2=JOptionPane.showInputDialog("nombre del jugador 2: ");
        Interfaz itf=new Interfaz(nombre1,nombre2);
        TimerTask tarea=new TimerTask() {
            @Override
            public void run() {
                Cliente c=new Cliente("rev");
                if (c.getmensaje() != null){
                    if (c.getmensaje().contains("d") || c.getmensaje().contains("v")){
                        char clave=c.getmensaje().charAt(0);
                        int i1=Character.getNumericValue(c.getmensaje().charAt(1));
                        int j1=Character.getNumericValue(c.getmensaje().charAt(2));
                        int i2=Character.getNumericValue(c.getmensaje().charAt(3));
                        int j2=Character.getNumericValue(c.getmensaje().charAt(4));
                        if (clave=='d'){
                            itf.listaB[i1][j1].setEnabled(false);
                            itf.listaB[i2][j2].setEnabled(false);
                            if(c.getmensaje().charAt(5)=='1'){
                                
                                itf.p1+=Character.getNumericValue(c.getmensaje().charAt(6));
                                itf.label1.setText(itf.getnombre1()+": "+itf.p1);
                            }
                            else{
                                itf.p2+=Character.getNumericValue(c.getmensaje().charAt(6));;
                                itf.label2.setText(itf.getnombre2()+": "+itf.p2);
                            }
                        }
                        else{
                            itf.listaB[i1][j1].setIcon(new ImageIcon(itf.reves.getImage().getScaledInstance(70,60,Image.SCALE_SMOOTH)));
                            itf.listaB[i2][j2].setIcon(new ImageIcon(itf.reves.getImage().getScaledInstance(70,60,Image.SCALE_SMOOTH)));
                        }
                        
                    }
                    else if(c.getmensaje().contains("p")){
                        if (c.getmensaje().contains("p1")){
                            JOptionPane.showMessageDialog(null,"turno del jugador 1");
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"turno del jugador 2");
                        }
                    }

                }
                
            }
            
        };

        timer.schedule(tarea, 0,500);
        
            }
        }
    