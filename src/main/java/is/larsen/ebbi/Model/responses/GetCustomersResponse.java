package is.larsen.ebbi.Model.responses;


import is.larsen.ebbi.Model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GetCustomersResponse extends BaseResponse {
    List<Customer> customers;

    public GetCustomersResponse(Integer statusCode, String message, List<Customer> customers) {
        super(statusCode, message);
        this.customers = customers;
    }
}
