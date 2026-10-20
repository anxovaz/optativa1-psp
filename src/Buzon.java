/**
 * Clase Buzón con método leer y escribir
 * @author anxo
 */
public class Buzon {
    public String mensaje = ""; //mensaje del buzón, solo puede haber uno

    /**
     * Método sincronizado que escribe en el buzón, al entrar comprueba si está lleno o no, si lo está hace un await() hasta que un hilo lector lo vacíe, si no, escribe en el buzón y manda una señal a todos los hilos (notifyAll)
     * @param texto texto que a escribir
     * @param nombreHiloEmisor el nombre del hilo que llama a este método
     */
    public synchronized void escribir(String texto, String nombreHiloEmisor) {
        while(mensaje.compareTo("") != 0){ //mientras esté lleno
            System.out.println("[LOG] " + nombreHiloEmisor + " - Buzón lleno, no se puede escribir, esperando...");

            try{
                wait(); //el hilo espera hasta recibir una señal
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        this.mensaje = texto;
        System.out.println("El hilo " + nombreHiloEmisor + " acaba de escribir en el buzón");
        notifyAll(); //notifica a todos los hilos
    }

    /**
     * Método que lee y devuelve un String con el mensaje, si el mensaje está vacío el hilo hace un await() hasta que un escritor lo esciba, una vez leído lo vacía para que un escritor lo pueda escribir
     * @param nombreHiloEmisor el nombre del hilo que llama a este método
     * @return mensaje
     */
    public synchronized String leer(String nombreHiloEmisor){
        while(this.mensaje.compareTo("") == 0){ //mientras esté vacío
            System.out.println("[LOG] " + nombreHiloEmisor + " - Buzón vacío, no se puede leer, esperando...");
            try{
                wait(); //el hilo espera hasta recibir una señal
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("El hilo " + nombreHiloEmisor + " acaba de leer en el buzón");
        String textoAdevolver = this.mensaje;
        System.out.println("[LOG] Vaciando buzón");
        this.mensaje =""; //Cuando se lee un mensaje ese mensaje se elimina
        notifyAll(); //notifica a todos los hilos
        return textoAdevolver;
    }
}
