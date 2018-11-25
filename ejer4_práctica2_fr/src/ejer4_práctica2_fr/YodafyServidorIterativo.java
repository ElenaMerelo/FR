//
// YodafyServidorIterativo
// (CC) jjramos, 2012
// Modificado por Elena Merelo, 2018

package ejer4_práctica2_fr;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.DatagramSocket;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
// Modificado por Elena Merelo, 2018

public class YodafyServidorIterativo {
    public static void main(String[] args) {
        // Puerto de escucha
        int port=8989;
        byte [] buffer= new byte[256];
        // Abrimos el socket en modo pasivo, escuchando en el puerto indicado por "port"
        DatagramSocket socketServidor;
        try {
            socketServidor= new DatagramSocket(port);
            //Socket socketConexion= null;

            // Mientras ... siempre!
            do {
                //Recibimos mensaje
                DatagramPacket paquete= new DatagramPacket(buffer, buffer.length);
                socketServidor.receive(paquete); 
                paquete.getData();
                paquete.getAddress();
                paquete.getPort();


                // Creamos un objeto de la clase ProcesadorYodafy, pasándole como 
                // argumento el nuevo socket, para que realice el procesamiento
                // Este esquema permite que se puedan usar hebras más fácilmente.
                ProcesadorYodafy procesador=new ProcesadorYodafy(socketServidor);
                procesador.procesa();
                socketServidor.close();

                } while (true);

        } catch (Exception e) {
                System.err.println("Error al escuchar en el puerto "+port);
                e.printStackTrace();
        }

    }

}
