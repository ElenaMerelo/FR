// YodafyServidorIterativo
// (CC) jjramos, 2012
// Modificado por Elena Merelo, 2018

package ejer4_práctica2_fr;

import java.net.DatagramSocket;


public class YodafyServidorIterativo {
    public static void main(String[] args) {
        // Abrimos el socket en modo pasivo, escuchando en el puerto indicado por "port"
        try {
            //Creamos el socket
            DatagramSocket socketServidor= new DatagramSocket(8989);

            do {     
                // Creamos un objeto de la clase ProcesadorYodafy, pasándole como 
                // argumento el nuevo socket, para que realice el procesamiento
                ProcesadorYodafy procesador=new ProcesadorYodafy(socketServidor);
                procesador.procesa();

                } while (true);

        } catch (Exception e) {
                System.err.println("Error al escuchar en el puerto.");
                e.printStackTrace();
        }

    }

}
