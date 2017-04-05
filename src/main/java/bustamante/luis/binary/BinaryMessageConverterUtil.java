package bustamante.luis.binary;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by luisbustamante on 03/04/2017.
 */
public class BinaryMessageConverterUtil {


    public static byte[] loadData(InputStream in, byte[] dataArray) throws IOException {
        int start = 0;

        do {
            int readCount = in.read(dataArray, start, dataArray.length - start);
            if(readCount == -1) {
                throw new EOFException("Connection closed from remote end.");
            }

            start += readCount;
        } while(start != dataArray.length);

        return dataArray;

    }
}
