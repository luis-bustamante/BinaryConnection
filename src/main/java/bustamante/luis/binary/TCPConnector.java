package bustamante.luis.binary;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by luisbustamante on 31/03/2017.
 */
public class TCPConnector {

    public void testConnection(String hostname, int port, String username, String password){

        try {
            Socket socket = new Socket(hostname, port);
            socket.setSoTimeout(10000);

            BinaryDataEndpoint bde = new BinaryDataEndpoint();
            String session = bde.login(socket, username, password);
            System.out.println("TCP session retrieved: " + session);
            bde.logout(socket, session);
            System.out.println("Tested successfully TCP on host: " + hostname + ", port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataEndpointAuthenticationException e) {
            e.printStackTrace();
        }
    }

}
