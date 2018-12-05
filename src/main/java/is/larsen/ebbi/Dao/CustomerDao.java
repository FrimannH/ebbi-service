package is.larsen.ebbi.Dao;


import is.larsen.ebbi.Model.CustomerResponse;

import java.util.List;

public interface CustomerDao {

    public Integer addCustomer(String customerName, String customerDescription);

    public Integer deleteCustomer(Integer customerId);

    public List<CustomerResponse> getCustomers();
}
