package is.larsen.ebbi.Model;


import java.io.Serializable;
import lombok.Data;

@Data
public class Response implements Serializable {
    private int statusCode;
    private String message;
}
