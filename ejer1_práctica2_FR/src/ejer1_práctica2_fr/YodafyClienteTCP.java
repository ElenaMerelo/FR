// YodafyClienteTCP
// (CC) jjramos, 2012
// Rellenado por Elena Merelo, 2018

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
		byte []buferEnvio;
		byte []buferRecepcion= new byte[256];
		int bytesLeidos= 0;
		
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
			
                        //Obtenemos los flujos de lectura y escritura del socket
			InputStream inputStream = socketServicio.getInputStream();
			OutputStream outputStream = socketServicio.getOutputStream();
			
			// Si queremos enviar una cadena de caracteres por un OutputStream, hay que pasarla primero
			// a un array de bytes:
			buferEnvio= "Al monte del volcán debes ir sin demora".getBytes();
			
			// Enviamos el array de bytes por el outputStream;
			outputStream.write(buferEnvio, 0, buferEnvio.length);
			
			// Aunque le indiquemos a TCP que queremos enviar varios arrays de bytes, sólo
			// los enviará efectivamente cuando considere que tiene suficientes datos que enviar...
			// Podemos usar "flush()" para obligar a TCP a que no espere para hacer el envío:
			outputStream.flush();
			
			// Leemos la respuesta del servidor. Para ello le pasamos un array de bytes, que intentará
			// rellenar. El método "read(...)" devolverá el número de bytes leídos.
			bytesLeidos= inputStream.read(buferRecepcion);
			
			// Mostramos la cadena de caracteres recibidos:
			System.out.println("Recibido: ");
			for(int i= 0; i< bytesLeidos; i++)
				System.out.print((char)buferRecepcion[i]);
			
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
