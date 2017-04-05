package bustamante.luis.binary;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by luisbustamante on 31/03/2017.
 */
public class BinaryEventSender {

    public BinaryEventSender() {
    }

    public static void sendBinaryLoginMessage(Socket socket, String userName, String password) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(13 + userName.length() + password.length());
        buf.put((byte)0);
        buf.putInt(8 + userName.length() + password.length());
        buf.putInt(userName.length());
        buf.putInt(password.length());
        buf.put(userName.getBytes("UTF-8"));
        buf.put(password.getBytes("UTF-8"));
        BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
        outputStream.write(buf.array());
        outputStream.flush();
    }

    public static void sendBinaryLogoutMessage(Socket socket, String sessionId) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(9 + sessionId.length());
        buf.put((byte)1);
        buf.putInt(4 + sessionId.length());
        buf.putInt(sessionId.length());
        buf.put(sessionId.getBytes("UTF-8"));
        BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
        outputStream.write(buf.array());
        outputStream.flush();
    }

    public static String processResponse(Socket socket) throws Exception {
        InputStream inputStream = socket.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        int messageType = bufferedInputStream.read();
        ByteBuffer bbuf;
        switch(messageType) {
            case 0:
            default:
                return null;
            case 1:
                bbuf = ByteBuffer.wrap(BinaryMessageConverterUtil.loadData(bufferedInputStream, new byte[8]));
                int errorClassNameLength = bbuf.getInt();
                int errorMsgLength = bbuf.getInt();
                String className = new String(ByteBuffer.wrap(BinaryMessageConverterUtil.loadData(bufferedInputStream, new byte[errorClassNameLength])).array());
                String errorMsg = new String(ByteBuffer.wrap(BinaryMessageConverterUtil.loadData(bufferedInputStream, new byte[errorMsgLength])).array());
                throw (Exception)((Exception)BinaryDataEndpoint.class.getClassLoader().loadClass(className).getConstructor(new Class[]{String.class}).newInstance(new Object[]{errorMsg}));
            case 2:
                bbuf = ByteBuffer.wrap(BinaryMessageConverterUtil.loadData(bufferedInputStream, new byte[4]));
                int sessionIdLength = bbuf.getInt();
                String sessionId = new String(ByteBuffer.wrap(BinaryMessageConverterUtil.loadData(bufferedInputStream, new byte[sessionIdLength])).array());
                return sessionId;
        }
    }

}
