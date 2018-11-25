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
import java.net.InetAddress;


public class YodafyServidorIterativo {
    public static void main(String[] args) {
        // Puerto de escucha
        int port;
        InetAddress address;
        
        
        byte [] buffer= new byte[256];
        String mensaje= "";
        mensaje= new String(buffer);
        
        DatagramPacket paquete= new DatagramPacket(buffer, buffer.length);
        
        // Abrimos el socket en modo pasivo, escuchando en el puerto indicado por "port"
        //DatagramSocket socketServidor;
        try {
            //Creamos el socket
            DatagramSocket socketServidor= new DatagramSocket(8989);

            // Mientras ... siempre!
            do {
                //Recibimos paquete 
                socketServidor.receive(paquete);
                buffer= paquete.getData();
                // Formateamos el mensaje recibido
                mensaje= new String(buffer).trim();
                System.out.println(mensaje);
                
                address= paquete.getAddress();
                System.out.println("Recibido desde la inet address " + address );
                
                port= paquete.getPort();
                System.out.println("Recibido desde puerto " + port);

                // Creamos un objeto de la clase ProcesadorYodafy, pasándole como 
                // argumento el nuevo socket, para que realice el procesamiento
                // Este esquema permite que se puedan usar hebras más fácilmente.
                ProcesadorYodafy procesador=new ProcesadorYodafy(socketServidor, address, port);
                procesador.procesa();
                //socketServidor.close();

                } while (true);

        } catch (Exception e) {
                System.err.println("Error al escuchar en el puerto.");
                e.printStackTrace();
        }

    }

}
