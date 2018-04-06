package is.larsen.ebbi.Model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Customer {
    private int customerId;
    private String customerName;
    private String customerDescription;
}
