//
// YodafyServidorConcurrente
// (CC) jjramos, 2012
// Rellenado por Elena Merelo, 2018

package ejer3_práctica2_fr;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class YodafyServidorConcurrente {
	public static void main(String[] args) {
		// Puerto de escucha
		int port=8989;
                // Abrimos el socket en modo pasivo, escuchando en el puerto indicado por "port"
                ServerSocket socketServidor;
		try {
                            socketServidor= new ServerSocket(port);
                            Socket socketConexion= null;

			// Mientras ... siempre!
			do {
				
				// Aceptamos una nueva conexión con accept()
                                try{
                                    socketConexion= socketServidor.accept();
                                }catch(Exception e){
                                    System.out.println("\nError: no se pudo aceptar la conexión solicitada");
                                }finally{
                                    Thread.sleep(1000);
                                }                          
				
				// Creamos un objeto de la clase ProcesadorYodafy, pasándole como 
				// argumento el nuevo socket, para que realice el procesamiento
				// Este esquema permite que se puedan usar hebras más fácilmente.
				ProcesadorYodafy procesador=new ProcesadorYodafy(socketConexion, 5);
                                procesador.start();
				
			} while (true);
			
		} catch (Exception e) {
			System.err.println("Error al escuchar en el puerto "+port);
                        e.printStackTrace();
		}

	}

}
