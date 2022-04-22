import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Timer;




public class manipulador { //manipula los mensajes recibidos por el cliente
    String mensaje;
    Interfaz interfaz;
    private ImageIcon estrella=new ImageIcon("extrella.png");

    public manipulador(String mensaje, Interfaz itf) throws InterruptedException {
        this.mensaje = mensaje;
        this.interfaz = itf;
        System.out.println(mensaje);
        char clave = mensaje.charAt(0);
        int i1 = Character.getNumericValue(mensaje.charAt(1));
        int j1 = Character.getNumericValue(mensaje.charAt(2));
        int i2 = Character.getNumericValue(mensaje.charAt(3));
        int j2 = Character.getNumericValue(mensaje.charAt(4));
        if (clave == 'd') {

            iguales(j1, j2, i1, i2); //si son iguales desactiva las fichas 
            sumarpuntos(mensaje.charAt(5), Character.getNumericValue(mensaje.charAt(6)));// suma los puntos 
            try {// si se activa un power up, los puntos extra viene en la posición 7, si no la posición 7 no existe

                if(mensaje.charAt(7)=='e'){
                    sumarpuntos(mensaje.charAt(5), 3);
                }
                else{sumarpuntos(mensaje.charAt(5), Character.getNumericValue(mensaje.charAt(7)));
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
        } 
        else if(clave=='e'){// colocar y descolocar la imagen en el power up de la estrella
            itf.listaB[i1][j1].setIcon(new ImageIcon(itf.reves.getImage().getScaledInstance(70, 60,Image.SCALE_SMOOTH)));
            itf.listaB[i2][j2].setIcon(new ImageIcon(estrella.getImage().getScaledInstance(70, 60,Image.SCALE_SMOOTH)));

        }
        else {
            diferentes(j1, j2, i1, i2);// da la vuelta a las imagenes si son diferentes
            Thread.sleep(300);// para asegurarme que se vea la segunda imagen 
        }
    }

    public void iguales(int j1, int j2, int i1, int i2) {
        interfaz.listaB[i1][j1].setEnabled(false);
        interfaz.listaB[i2][j2].setEnabled(false);

    }

    public void diferentes(int j1, int j2, int i1, int i2) {
        interfaz.listaB[i1][j1]
                .setIcon(new ImageIcon(interfaz.reves.getImage().getScaledInstance(70, 60, Image.SCALE_SMOOTH)));
        interfaz.listaB[i2][j2]
                .setIcon(new ImageIcon(interfaz.reves.getImage().getScaledInstance(70, 60, Image.SCALE_SMOOTH)));
    }

    public void sumarpuntos(char jugador, int puntos) {
        if (jugador == '1') {
            interfaz.p1 += puntos;
            interfaz.label1.setText(interfaz.getnombre1() + ": " + interfaz.p1);
        } else {
            interfaz.p2 += puntos;
            interfaz.label2.setText(interfaz.getnombre2() + ": " + interfaz.p2);
        }

    }
}
