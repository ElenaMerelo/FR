// ProcesadorYodafy
// (CC) jjramos, 2012
// Modificado por Elena Merelo, 2018
package ejer4_práctica2_fr;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;


//
// Nota: si esta clase extendiera la clase Thread, y el procesamiento lo hiciera el método "run()",
// ¡Podríamos realizar un procesado concurrente! 
//
public class ProcesadorYodafy {
	// Referencia a un socket para enviar/recibir las peticiones/respuestas
	private DatagramSocket socketServicio;
        
        private DatagramPacket paquete;
        
        private InetAddress address;
        private int port;
        
        byte [] buffer;
	
	// Para que la respuesta sea siempre diferente, usamos un generador de números aleatorios.
	private Random random;
	
	// Constructor que tiene como parámetro una referencia al socket abierto en por otra clase
	public ProcesadorYodafy(DatagramSocket socketServicio, InetAddress a, int p) {
            this.socketServicio=socketServicio;
            random=new Random();
            buffer= new byte[256];
            paquete= new DatagramPacket(buffer, buffer.length);
            this.address= a;
            this.port= p;
	}
	
	
	// Aquí es donde se realiza el procesamiento realmente:
	void procesa(){
            String frase;

            try {
                socketServicio.receive(paquete); 
                
                buffer= paquete.getData();
                
                //Pasamos los bytes a string
                String mensaje= new String(buffer).trim();

                // Yoda reinterpreta el mensaje:
                String respuesta=yodaDo(mensaje);
                
                byte [] envmensaje= new byte [256];
                envmensaje= respuesta.getBytes();

                // Enviamos la traducción de Yoda:
                DatagramPacket envio= new DatagramPacket(envmensaje, envmensaje.length, address, port );
                socketServicio.send(envio);

            } catch (IOException e) {
                System.err.println("Error al obtener los flujos de entrada/salida.");
            }

	}

	// Yoda interpreta una frase y la devuelve en su "dialecto":
	private String yodaDo(String peticion) {
		// Desordenamos las palabras:
		String[] s = peticion.split(" ");
		String resultado="";
		
		for(int i=0;i<s.length;i++){
			int j=random.nextInt(s.length);
			int k=random.nextInt(s.length);
			String tmp=s[j];
			
			s[j]=s[k];
			s[k]=tmp;
		}
		
		resultado=s[0];
		for(int i=1;i<s.length;i++){
		  resultado+=" "+s[i];
		}
		
		return resultado;
	}
}
