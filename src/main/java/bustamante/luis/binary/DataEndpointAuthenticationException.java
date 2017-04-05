package bustamante.luis.binary;

/**
 * Created by luisbustamante on 31/03/2017.
 */
public class DataEndpointAuthenticationException extends Exception {

    private String errorMessage;

    public DataEndpointAuthenticationException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public DataEndpointAuthenticationException(String message, Throwable cause) {
        super(message, cause);
        this.errorMessage = message;
    }

    public DataEndpointAuthenticationException(Throwable cause) {
            super(cause);
        }

    public String getErrorMessage() {
            return this.errorMessage;
        }
}
