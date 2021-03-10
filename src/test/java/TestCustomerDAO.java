import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.barclays.ims.DbUtils;
import com.barclays.ims.Models.Customer;

import org.junit.Test;

public class TestCustomerDAO {

    @Test 
    public void readAll() throws SQLException {
        DbUtils dbUtils = new DbUtils();
        List<Customer> customerList = new ArrayList<>();        
            
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM IMS.CUSTOMERS");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int customerId = resultSet.getInt("CustomerID");
                String customerName = resultSet.getString("Name");

                Customer customer = new Customer(customerId, customerName);
                customerList.add(customer);
            }
        assertEquals(7, customerList.size());
    }
}