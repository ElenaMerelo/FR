/**
 *
 * @author elena
 */

package práctica2_fr;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class protocolo {
    static int DISCONNECT = 1000;
    static int NUEVACUENTA = 1001;
    static int LOGINCUENTA = 1002;
    static int SACAR= 1003;
    static int INGRESAR = 1004;
    static int ERRORFONDOS = 1005;
    static int PEDIRPIN1= 1006;
    static int PEDIRPIN2= 1007;
    static int SACARCONPIN1= 1008;
    static int SACARCONPIN2= 1009;
    static int ERRORCUENTA = 401;
    static int PIN1INCORRECTO= 402;
    static int PIN2INCORRECTO= 403;
    static int OK = 200;
    
    static String SEPARATOR = "//";
    
    public static void emitPacket( PrintWriter outPrinter, int code, String message  ) {
        String packetMessage = Integer.toString(code) + protocolo.SEPARATOR + message;
        outPrinter.println( packetMessage );
        outPrinter.flush();
    }
    
    public static String[] receivePacket( BufferedReader inReader ) {
        String[] message = null;
        try { 
            message = inReader.readLine().split( SEPARATOR ); 
        } catch ( Exception e ) {};
        return message;
    }

}