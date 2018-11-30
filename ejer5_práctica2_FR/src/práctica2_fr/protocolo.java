/**
 *
 * @author elena
 */

package práctica2_fr;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class protocolo {
    static int DISCONNECT = 1000;
    static int LOGIN = 1001;
    static int REGISTRATE = 1002;
    static int CONNECTIONTIME = 1003;
    static int STAYCONNECTED = 1004;
    static int STAYCONNECTEDANSWER = 1005;
    static int OK = 200;
    static int LOGINERROR = 401;
    static int TAKENUSER = 402;

    
    static int DATA = 2000;
    
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