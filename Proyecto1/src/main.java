public class main {
    public static void main(String[] args) {
        Interfaz itf=new Interfaz();
        int x=6;
        int y=7;
        int i=0;
        int j=0;
        while(j<y){
            Ficha ficha=new Ficha(i, j);
            itf.add(ficha.boton);
            i++;
            if (i==x){
                i=0;
                j++;
            }
        }
        
    }
    
}
