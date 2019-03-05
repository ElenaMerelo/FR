/**
 *
 * @author elena
 */

package práctica2_fr;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Banco extends Thread {
    static ArrayList<Afiliado> clientes;
    
    private PrintWriter outPrinter;
    private BufferedReader inReader;   
    // Referencia a un socket para enviar/ recibir las peticiones/respuesta
    private Socket socketServicio;
    private int index;
    // Stream de lectura ( por aquí se recibe lo que envía el cliente) 
    private InputStream inputStream;
    
    // Stream de escritura ( por aquí se envían los datos al cliente 
    private OutputStream outputStream; 
    
    public Banco(Socket socketServicio){
        this.socketServicio= socketServicio;
    }
    
    // Dado un número de tarjeta, miramos a ver si se corresponde con el de alguno de los clientes registrados
    public int buscarAfiliado( String r ) {
        for( int i = 0; i < clientes.size(); i++ ) {
            if( r.equals(clientes.get(i).getnTarjeta()) )
                return i;
        }
        return -1;
    }
    
    public void run(){      
        try{
            inputStream= socketServicio.getInputStream();
            outputStream= socketServicio.getOutputStream();
            
            inReader= new BufferedReader( new InputStreamReader(inputStream));
            outPrinter= new PrintWriter( outputStream, true );
            
            String[] respuesta;
            
            do {
                respuesta = protocolo.receivePacket( inReader );
                
                //Nuevo login
                if( Integer.parseInt( respuesta[0] ) == protocolo.LOGINCUENTA ) {
                    index = buscarAfiliado( respuesta[1] );
                    if( index != -1 )
                        protocolo.emitPacket( outPrinter, protocolo.OK, "Login correcto." );
                    else 
                        protocolo.emitPacket(outPrinter, protocolo.ERRORCUENTA, "No hay ninguna cuenta asociada a ese numero.");
                }
                
                //Registrar nuevo usuario
                if( Integer.parseInt( respuesta[0]) == protocolo.NUEVACUENTA ) {
                    if( buscarAfiliado(respuesta[1]) == -1 ){
                        ArrayList<String> nombre = new ArrayList<>(Arrays.asList(respuesta[4].split(" ")));
                        clientes.add( new Afiliado(respuesta[1], respuesta[2], respuesta[3], nombre, 0.0) );
                        protocolo.emitPacket(outPrinter, protocolo.OK, "Cuenta creada correctamente");
                    } else {
                        protocolo.emitPacket(outPrinter, protocolo.ERRORCUENTA, "Este número de cuenta ya existe.");
                    }
                }
                                
                //Operacion sacar dinero
                if( Integer.parseInt( respuesta[0] ) == protocolo.SACAR ) {
                    int nIntentos= 0;
                    boolean correcto= false;
                            
                    if(clientes.get(index).getDinero() < -1000) // si debe más de 1000 euros no le dejamos sacar dinero
                        protocolo.emitPacket(outPrinter, protocolo.ERRORFONDOS, "Debe " + Double.toString(clientes.get(index).getDinero()) + ". No puede sacar más.");
                    else{
                        System.out.println( "Iniciando operación de extracción de dinero..." );
                        int dinero_a_sacar= Integer.parseInt(respuesta[1]);
                        
                        // Si va a sacar entre 20 y 300 euros le pedimos el pin 1
                        if(dinero_a_sacar > 20 && dinero_a_sacar < 300 ){
                            System.out.println( "Pidiendo pin 1..." );
                            nIntentos= 0;
                            correcto= false;
                            
                            System.out.println("Mandando paquete PEDIRPIN1");
                            protocolo.emitPacket(outPrinter, protocolo.PEDIRPIN1, "Es necesario que introduzca su pin 1 para realizar esta operación");
                            do{
                                respuesta= protocolo.receivePacket(inReader);

                                if(Integer.parseInt(respuesta[0]) == protocolo.SACARCONPIN1){ 
                                    if(clientes.get(index).getPin1().equals(respuesta[1])){
                                        correcto= true;
                                    }
                                    else{                             
                                        protocolo.emitPacket(outPrinter, protocolo.PIN1INCORRECTO, "Pin 1 incorrecto. Lleva " + nIntentos + " intentos");
                                        nIntentos++;
                                    }
                                }
                            }while(nIntentos < 3 && correcto== false ); // le dejamos tres intentos para que ponga el pin 1
                            if(!correcto)
                                protocolo.emitPacket(outPrinter, protocolo.DISCONNECT, "Número de intentos agotados. Cerrando sesión...");
                        }
                        
                        // Si va a sacar más de 300 euros le pedimos también el pin 2
                        if(dinero_a_sacar >= 300) {
                            nIntentos= 0;
                            correcto= false;
                            
                            protocolo.emitPacket(outPrinter, protocolo.PEDIRPIN2, "Ha de introducir además su pin 2 para realizar esta operación");  
                            do{
                                respuesta= protocolo.receivePacket(inReader); 
                                if(Integer.parseInt(respuesta[0]) == protocolo.SACARCONPIN2){

                                    if(clientes.get(index).getPin2().equals(respuesta[1])){
                                        correcto= true;
                                    }
                                    else{                             
                                        protocolo.emitPacket(outPrinter, protocolo.PIN2INCORRECTO, "Pin 2 incorrecto. Lleva " + nIntentos + " intentos");
                                        nIntentos++;
                                    } 
                                }
                            }while(nIntentos < 3 && correcto== false ); // le dejamos tres intentos para que ponga el pin 2
                            
                            if(!correcto)
                                protocolo.emitPacket(outPrinter, protocolo.DISCONNECT, "Número de intentos agotados. Cerrando sesión...");
                        }

                        //Si saca menos de 20 euros, los saca directamente ya que no hace falta pin.
                        clientes.get(index).sacarDinero(dinero_a_sacar);
                        protocolo.emitPacket(outPrinter, protocolo.OK, "Saldo: " + Double.toString(clientes.get(index).getDinero()));
                   
                    }
                }
 
                //Operacion ingreso
                if( Integer.parseInt( respuesta[0] ) == protocolo.INGRESAR ) {
                    System.out.println("Metiendo dinero...");
                    clientes.get(index).meterDinero(Integer.parseInt( respuesta[1] ));
                    protocolo.emitPacket( outPrinter, protocolo.OK, "PIN Correcto. Saldo: " + Double.toString(clientes.get(index).getDinero()) );
                }
                
            } while( true );
            
        } catch (Exception e) {};
    }

}
    