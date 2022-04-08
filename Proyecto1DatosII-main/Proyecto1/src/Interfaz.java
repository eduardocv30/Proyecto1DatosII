import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Interfaz extends JFrame {
    private int x=6;
    private int y=7;
    public int p1=0;
    public int p2=0;
    private String nombre1;
    private String nombre2;
    public JLabel label1;
    public JLabel label2;
    private JPanel panel;
    public ImageIcon reves=new ImageIcon("signo.jpg");
    public JButton listaB[][]=new JButton[x][y];
    

    public Interfaz(String _nombre1,String _nombre2){
        this.setSize(550,550);
        nombre1=_nombre1;
        nombre2=_nombre2;
        panel=new JPanel();
        label1=new JLabel(nombre1+": "+p1);
        label2=new JLabel(nombre2+": "+p2);
        this.add(panel);
        this.add(label1);
        this.add(label2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        panel.setBounds(0, 0, 550, 450);
        label1.setBounds(10, 460, 150, 30);
        label1.setFont(new Font("Arial",Font.PLAIN,20));
        label2.setFont(new Font("Arial",Font.PLAIN,20));
        label2.setBounds(310, 460, 150, 30);
        panel.setLayout(new GridLayout(y,x));
        this.setVisible(true);
        this.botones(panel);
    
    }
    public void botones(JPanel panel) {
        int i=0;
        int j=0;
        while(j<y){
            Ficha ficha=new Ficha(i, j);
            panel.add(ficha.boton);
            listaB[i][j]=ficha.boton;
            ficha.boton.setIcon(new ImageIcon(reves.getImage().getScaledInstance(70, 60,Image.SCALE_SMOOTH)));
            i++;
            if (i==x){
                i=0;
                j++;
            }
        }
    }

    
    public String getnombre1() {
        return nombre1;
    }

    public String getnombre2() {
        return nombre2;
    }

    public int getx(){
        return x;
    }

    public int gety(){
        return y;
    }
}
