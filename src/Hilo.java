/**
 * Clase Hilo que mana 3 operaciones a un buzón dependiendo de si es lector o escritor (esEscritor)
 * @author anxo
 */
public class Hilo extends Thread{
    private Buzon b;
    private boolean esEscritor;

    /**
     * Constructor
     * @param name nombre del hilo
     * @param b buzón sobre el que hace operaciones
     * @param esc si es escritor (true) o lector (false)
     */
    public Hilo(String name, Buzon b, boolean esc){
        super.setName(name);
        this.b = b;
        this.esEscritor = esc;
    }

    /**
     * Método run con bucle que hace tres iteraciones para hacer operaciones sobre el buzón
     */
    @Override
    public void run(){
        //manda 3 operaciones por hilo
        for(int i = 0;i<3;i++) {
            if (this.esEscritor) {
                this.b.escribir("Escritor por " + super.getName(), super.getName());
            } else {
                this.b.leer(super.getName());
            }
        }
    }
}
