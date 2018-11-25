// YodafyServidorIterativo
// (CC) jjramos, 2012
// Modificado por Elena Merelo, 2018

package ejer4_práctica2_fr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;



public class YodafyClienteUDP {
    public static void main(String[] args) {
        String respuesta;

        byte [] buffer= new byte[256];
        byte [] buffer2= new byte[256];
        // Nombre del host donde se ejecuta el servidor:
        String host="localhost";
        int port= 8989;

        InetAddress direccion= null;

        try{
            direccion= InetAddress.getByName(host);
        }catch(UnknownHostException e){
            System.err.println("Error: host desconocido");
        }
        DatagramPacket paquete;


        // Socket para la conexión TCP
        DatagramSocket socketServicio=null;

        try {
            // Creamos un socket que se conecte a "host" y "port":

            socketServicio= new DatagramSocket();

            buffer= "Al monte del volcán debes ir sin demora".getBytes();

            paquete= new DatagramPacket(buffer, buffer.length, direccion, port);
            socketServicio.send(paquete);
            
            DatagramPacket receivePacket = new DatagramPacket( buffer2, buffer2.length );
            socketServicio.receive( receivePacket );
            respuesta = new String( receivePacket.getData() );
            System.out.println( "Respuesta recibida: " + respuesta );


            // Una vez terminado el servicio, cerramos el socket
            socketServicio.close();

            // Excepciones:
        } catch (UnknownHostException e) {
            System.err.println("Error: Nombre de host no encontrado.");
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al abrir el socket.");
        }
    }
}
