import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.barclays.ims.DbUtils;
import com.barclays.ims.DAOClasses.OrderDAO;
import com.barclays.ims.Models.Order;

import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TestOrderDAO {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void createOrder() {
        List<Integer> itemIds = new ArrayList<>();
        itemIds.add(5);
        OrderDAO orderDAO = new OrderDAO();
        Order testOrder = new Order(orderDAO.readLatest().getOrderID() + 1, 2, itemIds);
        Order resultOrder = orderDAO.create(testOrder);
        assertEquals(testOrder.getCustomerID(), resultOrder.getCustomerID());
        assertEquals(itemIds, resultOrder.getItemIDs());
    }

    @Test
    public void deleteOrder() {
        OrderDAO orderDAO = new OrderDAO();

    }
}
