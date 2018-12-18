package is.larsen.ebbi.Model.responses;


import is.larsen.ebbi.Model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetCustomersResponse extends BaseResponse {
    List<Customer> customers;
}
