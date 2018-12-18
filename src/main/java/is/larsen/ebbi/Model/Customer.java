package is.larsen.ebbi.Model;

import is.larsen.ebbi.Model.responses.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Integer customerId;
    private String customerName;
    private String customerDescription;
}
