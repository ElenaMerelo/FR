import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
// Modificado por Elena Merelo, 2018

public class YodafyServidorIterativo {
	public static void main(String[] args) {
		// Puerto de escucha
		int port=8989;
		// array de bytes auxiliar para recibir o enviar datos.
		byte []buffer=new byte[256];
		// Número de bytes leídos
		int bytesLeidos=0;
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
				ProcesadorYodafy procesador=new ProcesadorYodafy(socketConexion);
				procesador.procesa();
				
			} while (true);
			
		} catch (Exception e) {
			System.err.println("Error al escuchar en el puerto "+port);
                        e.printStackTrace();
		}

	}

}
