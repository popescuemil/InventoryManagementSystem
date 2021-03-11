import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.barclays.ims.DbUtils;
import com.barclays.ims.DAOClasses.CustomerDAO;
import com.barclays.ims.Models.Customer;

import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestCustomerDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test 
    public void readAll() throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        CustomerDAO customerDAO = new CustomerDAO();
        
        List<Customer> resultList = customerDAO.readAll();
        
    }

    @Test
    public void createCustomer() throws SQLException{
        Customer testCustomer = new Customer("Emil");
        CustomerDAO customerDAO = new CustomerDAO();
        Customer resultCustomer = customerDAO.create(testCustomer);
        assertEquals(testCustomer.getCustomerName(), resultCustomer.getCustomerName());
    }

    @Test
    public void updateCustomer() throws SQLException{
        CustomerDAO customerDAO = new CustomerDAO();
        Customer resultCustomer = customerDAO.update(new Customer(2, "Emil"));
        assertEquals("Emil", resultCustomer.getCustomerName());
    }

    @Test
    public void deleteCustomer(){
        int customerId = 9;
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.delete(Long.valueOf(customerId));
        assertEquals(null, customerDAO.readById(Long.valueOf(customerId)));
    }

    @Test
    public void readLatestCustomer() {
        DbUtils dbUtils = new DbUtils();
        CustomerDAO customerDAO = new CustomerDAO();
        try {
            Connection connection = dbUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO IMS.CUSTOMERS (Name) VALUES (?)");
            statement.setString(1, "Michael");
            statement.executeUpdate();
            assertEquals("Michael", customerDAO.readLatest().getCustomerName());
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    @Test
    public void readCustomerById() {
        DbUtils dbUtils = new DbUtils();
        CustomerDAO customerDAO = new CustomerDAO();
        try {
            Connection connection = dbUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO IMS.CUSTOMERS (Name) VALUES (?)");
            statement.setString(1, "Mike");
            statement.executeUpdate();
            assertEquals("Mike", customerDAO.readById(Long.valueOf(customerDAO.readLatest().getCustomerID())).getCustomerName());
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }
}