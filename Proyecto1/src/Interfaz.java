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
    }
   
}
