package is.larsen.ebbi.Model.requests;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AddCustomerRequest {
    private String customerName;
    private String customerDescription;
}
