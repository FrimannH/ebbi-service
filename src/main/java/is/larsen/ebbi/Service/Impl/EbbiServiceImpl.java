package is.larsen.ebbi.Service.Impl;

import is.larsen.ebbi.Service.EbbiService;
import is.larsen.ebbi.Model.*;
import is.larsen.ebbi.Dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EbbiServiceImpl implements EbbiService {

    @Autowired
    private CustomerDao customerDao;

    public EbbiServiceImpl() {

    }

    @Override
    public List<Customer> getCustomers() {
        return customerDao.getCustomers();
    }

    @Override
    public  Response addCustomer(String customerName, String customerDescription) {

        Integer status = customerDao.addCustomer(customerName, customerDescription);

        return new Response(status, "");
    }

    @Override
    public Response updateCustomerSurvey(Integer customerId, String input) {
        Response response = new Response();
        //do something

        return response;
    }


}
