/**
 *
 * @author elena
 */

package práctica2_fr;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;

import java.util.ArrayList;

public class Banco extends Thread {
    private ArrayList<Afiliado> clientes;
    // Referencia a un socket para enviar/ recibir las peticiones/respuesta
    private Socket socketServicio;
    
    // Stream de lectura ( por aquí se recibe lo que envía el cliente) 
    private InputStream inputStream;
    
    // Stream de escritura ( por aquí se envían los datos al cliente 
    private OutputStream outputStream; 
    
    public Banco(Socket socketServicio, ArrayList<Afiliado> c){
        this.socketServicio= socketServicio;
        clientes.clear();
        clientes.addAll(c);
    }
    
    public void run(){
        
        try{
            inputStream= socketServicio.getInputStream();
            outputStream= socketServicio.getOutputStream();
            
            BufferedReader inReader= new BufferedReader( new InputStreamReader(inputStream));
            PrintWriter outPrinter= new PrintWriter( outputStream, true );
            
            
        }
    }

}

