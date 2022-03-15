import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;

public class Interfaz extends JFrame {
    private int x=10;
    private int y=7;

    public Interfaz(){
        this.setSize(650,650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(y,x));
        this.setVisible(true);
        this.botones();
    }
    public void botones() {
        int i=0;
        int j=0;
        while(j<y){
            Ficha ficha=new Ficha(i, j);
            this.add(ficha.boton);
            i++;
            if (i==x){
                i=0;
                j++;
            }
        }
    }
}
