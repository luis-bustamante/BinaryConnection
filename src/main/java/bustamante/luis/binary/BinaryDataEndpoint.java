package bustamante.luis.binary;

import java.net.Socket;

/**
 * Created by luisbustamante on 31/03/2017.
 */
public class BinaryDataEndpoint {

    public String login(Object client, String userName, String password) throws DataEndpointAuthenticationException {
        Socket socket = (Socket)client;

        try {
            BinaryEventSender.sendBinaryLoginMessage(socket, userName, password);
            return BinaryEventSender.processResponse(socket);
        } catch (Exception e) {
            if(e instanceof DataEndpointAuthenticationException) {
                throw (DataEndpointAuthenticationException)e;
            } else {
                throw new DataEndpointAuthenticationException("Error while trying to login to data receiver :" + socket.getRemoteSocketAddress().toString(), e);
            }
        }
    }

    public void logout(Object client, String session) throws DataEndpointAuthenticationException {
        Socket socket = (Socket)client;

        try {
            BinaryEventSender.sendBinaryLogoutMessage(socket, session);
            BinaryEventSender.processResponse(socket);
        } catch (Exception e) {
            if(e instanceof DataEndpointAuthenticationException) {
                throw (DataEndpointAuthenticationException) e;
            } else {
                throw new DataEndpointAuthenticationException("Error while trying to logout to data receiver :" + socket.getRemoteSocketAddress().toString(), e);
            }
        }
    }
}
