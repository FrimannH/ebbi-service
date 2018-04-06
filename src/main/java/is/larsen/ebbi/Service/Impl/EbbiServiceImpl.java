package is.larsen.ebbi.Service.Impl;

import is.larsen.ebbi.Service.EbbiService;
import is.larsen.ebbi.Model.*;
import is.larsen.ebbi.Dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class EbbiServiceImpl implements EbbiService {

    @Autowired
    private CustomerDao customerDao;

    public EbbiServiceImpl() {

    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<Customer>();

        return customers;
    }

    @Override
    public  Response addCustomer(Customer customer) {
        return
    }

    @Override
    public Response updateCustomerSurvey(Integer customerId, String input) {
        Response response = new Response();
        //do something

        return response;
    }


}
