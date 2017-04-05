package bustamante.luis.binary;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by luisbustamante on 03/04/2017.
 */
public class SSLConnector {

    public void testConnection(String hostname, int port, String username, String password){

        try {

            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            SSLSocket sslSocket = (SSLSocket) sc.getSocketFactory().createSocket(hostname,port);
            //SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            //SSLSocket sslSocket = (SSLSocket) sslsocketfactory.createSocket(hostname, port);
            sslSocket.setSoTimeout(10000);

            BinaryDataEndpoint bde = new BinaryDataEndpoint();
            String session = bde.login(sslSocket, username, password);
            System.out.println("SSL session retrieved: " + session);
            bde.logout(sslSocket, session);
            System.out.println("Tested successfully SSL on host: " + hostname + ", port: " + port);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataEndpointAuthenticationException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

    }
}
