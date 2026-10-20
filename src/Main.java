/**
 * Clase Main, con main que lanza 4 hilos, 2 escritores y 2 lectores
 * @author anxo
 */
public class Main {
    /**
     * Método que lanza 4 hilos, 2 escritores y 2 lectores indicando su nombre y su tipo (escritores o no), los arranca y espera a que finalizen
     * @param args
     */
    public static void main(String[] args){
        //Creo todos los hilos
        Buzon b = new Buzon();
        Hilo escritor1 = new Hilo("escritor 1", b, true);
        Hilo escritor2 = new Hilo("escritor 2", b, true);
        Hilo lector1 = new Hilo("lector 1", b , false);
        Hilo lector2 = new Hilo("lector 2", b , false);


        escritor1.start();
        escritor2.start();
        lector1.start();
        lector2.start();

        try{
            escritor1.join();
            escritor2.join();
            lector1.join();
            lector2.join();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }



    }
}
