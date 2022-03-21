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
