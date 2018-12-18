package is.larsen.ebbi.Dao;


import is.larsen.ebbi.Model.Customer;
import is.larsen.ebbi.Model.responses.GetCustomersResponse;

import java.util.List;

public interface CustomerDao {

    public Integer addCustomer(String customerName, String customerDescription);

    public Integer deleteCustomer(Integer customerId);

    public GetCustomersResponse getCustomers();
}
