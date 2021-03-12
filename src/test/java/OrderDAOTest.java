import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.barclays.ims.DbUtils;
import com.barclays.ims.DAOClasses.ItemDAO;
import com.barclays.ims.DAOClasses.OrderDAO;
import com.barclays.ims.Models.Order;

import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class OrderDAOTest {
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
        int orderId = 3;
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.delete(Long.valueOf(orderId));
        assertEquals(null, orderDAO.readById(Long.valueOf(orderId)));
    }

    @Test
    public void deleteItemFromOrder() {
        int orderId = 1;
        int itemId = 2;
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.deleteItemFromOrder(Long.valueOf(itemId), Long.valueOf(orderId));

        Order resultOrder = orderDAO.readById(Long.valueOf(orderId));

        for(int i : resultOrder.getItemIDs()){
            assertNotEquals(2, i);
        }
    }

    @Test
    public void calculateCost(){
        List<Integer> itemIds = new ArrayList<>();
        itemIds.add(4);
        itemIds.add(2);
        ItemDAO itemDAO = new ItemDAO();
        double totalCost = itemDAO.readById(Long.valueOf(4)).getItemCost() + itemDAO.readById(Long.valueOf(2)).getItemCost();
        OrderDAO orderDAO = new OrderDAO();
        Order testOrder = new Order(orderDAO.readLatest().getOrderID() + 1, 3, itemIds);
        Order resultOrder = orderDAO.create(testOrder);
        double resultCost = orderDAO.calculateCost(Long.valueOf(resultOrder.getOrderID()));
        assertEquals(totalCost, resultCost, 0);
    }

    @Test
    public void addItemToOrder(){
        int itemId = 1;
        int orderId = 6;
        OrderDAO orderDAO = new OrderDAO();
        Order resultOrder = orderDAO.addItemToOrder(Long.valueOf(itemId), Long.valueOf(orderId));
        assertTrue(resultOrder.getItemIDs().contains(1));
    }
}
