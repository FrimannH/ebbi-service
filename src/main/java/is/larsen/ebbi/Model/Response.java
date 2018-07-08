package is.larsen.ebbi.Model;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {
    private int statusCode;
    private String message;
}
