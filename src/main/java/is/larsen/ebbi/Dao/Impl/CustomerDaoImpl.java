package is.larsen.ebbi.Dao.Impl;


import is.larsen.ebbi.Dao.CustomerDao;
import is.larsen.ebbi.Model.Customer;
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
        String query = "INSERT INTO Customer (customer_name, customer_description) VALUES(customerName, customerDescription);";

        return jdbcTemplate.update(query);
    }

    @Override
    public List<Customer> getCustomers() {
        return jdbcTemplate.query("SELECT * FROM Customers", new CustomerRowMapper());
    }

    @Override
    public Integer deleteCustomer(Integer customerId) {
        return jdbcTemplate.update("DELETE FROM Customer where customer_id = " + customerId + ";");
    }

    public class CustomerRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setCustomerId(rs.getInt("customer_id"));
            customer.setCustomerName(rs.getString("customer_name"));
            customer.setCustomerDescription(rs.getString("customer_description"));
            return customer;
        }
    }
}
