package is.larsen.ebbi.Service;

import is.larsen.ebbi.Model.Response;
import is.larsen.ebbi.Model.Customer;

import java.util.List;

public interface EbbiService {

    List<Customer> getCustomers();

    Response addCustomer(Customer customer);

    Response updateCustomerSurvey(Integer customerId, String input);
}
