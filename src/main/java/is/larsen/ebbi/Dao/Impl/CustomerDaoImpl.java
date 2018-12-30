package is.larsen.ebbi.Dao.Impl;


import is.larsen.ebbi.Dao.CustomerDao;
import is.larsen.ebbi.Model.Customer;
import is.larsen.ebbi.Model.responses.GetCustomersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer addCustomer(String customerName, String customerDescription) {
        String query = "INSERT INTO Customers (customer_name, customer_description) VALUES (\"" + customerName + "\", \"" +customerDescription + "\");";

        return jdbcTemplate.update(query);
    }

    @Override
    public GetCustomersResponse getCustomers() {

        List<Customer> customers = jdbcTemplate.query("SELECT * FROM Customers", new CustomerRowMapper());
        return new GetCustomersResponse(0, "", customers);
    }

    public Customer getCustomer(Integer customerId) {

        List<Customer> customers = jdbcTemplate.query("SELECT * FROM Customers", new CustomerRowMapper());
        if ( customers.size() > 0) {
            return customers.get(0);
        } else {
            return new Customer(0, "", "");
        }
    }

    @Override
    public Integer deleteCustomer(Integer customerId) {
        return jdbcTemplate.update("DELETE FROM Customers where customer_id = " + customerId + ";");
    }

    protected class CustomerRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setCustomerId(rs.getInt("customer_id"));
            customer.setCustomerName(rs.getString("customer_name"));
            customer.setCustomerDescription(rs.getString("customer_description"));
            return customer;
        }
    }
}
