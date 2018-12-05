package is.larsen.ebbi.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse extends BaseResponse {
    private Integer customerId;
    private String customerName;
    private String customerDescription;
}