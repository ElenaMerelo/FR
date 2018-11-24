//
// YodafyServidorIterativo
// (CC) jjramos, 2012
// Modificado por Elena Merelo, 2018

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class YodafyClienteTCP {
	public static void main(String[] args) {
		String mensaje= "Al monte del volcán debes ir sin demora";
		String respuesta;
		
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
		
		// Socket para la conexión TCP
		Socket socketServicio=null;
		
		try {
			// Creamos un socket que se conecte a "host" y "port":
			try{
                            socketServicio= new Socket(host, port);
                        }catch(UnknownHostException e){
                            System.err.println("Error: equipo desconocido");
                        }catch(IOException e){
                            System.err.println("Error: no se pudo establecer la conexión");
                        }
			
                        //Obtenemos los flujos de lectura y escritura del socket, en modo texto
                        BufferedReader inReader= new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
                        PrintWriter outPrinter= new PrintWriter(socketServicio.getOutputStream(), true);
			
			
			// Mandamos el mensaje por el outPrinter
			outPrinter.println(mensaje);
			respuesta= inReader.readLine();
			
                        //Mostramos lo recibido
			System.out.println("Recibido: ");
			System.out.println(respuesta);
                        
			
			// Una vez terminado el servicio, cerramos el socket (automáticamente se cierran
			// el inpuStream  y el outputStream)
			socketServicio.close();
			
			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
