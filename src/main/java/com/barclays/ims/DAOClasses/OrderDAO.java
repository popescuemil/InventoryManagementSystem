package com.barclays.ims.DAOClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.barclays.ims.DAO;
import com.barclays.ims.DbUtils;
import com.barclays.ims.Models.Order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderDAO implements DAO<Order> {
    private static final Logger LOGGER = LogManager.getLogger();
    DbUtils dbUtils = new DbUtils();

    @Override
    public List<Order> readAll() throws SQLException {

        List<Order> orderList = new ArrayList<>();
        try {
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection
                    .prepareStatement("SELECT DISTINCT OrderID, CustomerId from ORDERS");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int orderId = resultSet.getInt("OrderID");
                int customerId = resultSet.getInt("CustomerId");

                PreparedStatement statementItems = connection
                        .prepareStatement("Select ItemId from ORDERS where OrderId = ?");
                statementItems.setInt(1, orderId);

                ResultSet resultSetItems = statementItems.executeQuery();

                List<Integer> itemIDs = new ArrayList<>();

                while (resultSetItems.next()) {
                    int itemId = resultSetItems.getInt("ItemId");
                    itemIDs.add(itemId);
                }

                Order order = new Order(orderId, customerId, itemIDs);
                orderList.add(order);
            }
            return orderList;
        } catch (Exception e) {
            LOGGER.debug(e);
            return null;
        }
    }

    @Override
    public Order readById(Long id) {
        try {
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection
                    .prepareStatement("SELECT DISTINCT OrderID, CustomerId from ORDERS WHERE OrderID = ?");
            
            statement.setInt(1, id.intValue());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int customerId = resultSet.getInt("CustomerId");

                PreparedStatement statementItems = connection
                        .prepareStatement("Select ItemId from ORDERS where OrderId = ?");
                statementItems.setInt(1, id.intValue());

                ResultSet resultSetItems = statementItems.executeQuery();

                List<Integer> itemIDs = new ArrayList<>();

                while (resultSetItems.next()) {
                    int itemId = resultSetItems.getInt("ItemId");
                    itemIDs.add(itemId);
                }

                return (new Order(id.intValue(), customerId, itemIDs));
            }
        } catch (Exception e) {
            LOGGER.debug(e);
        }
        return null;
    }

    //selecting last record so we can increment orderId by 1 and add new order
    @Override
    public Order readLatest() {
        try {
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ORDERS ORDER BY OrderID DESC LIMIT 1");

            ResultSet resultSetOrder = statement.executeQuery();

            if(resultSetOrder.next()){
                int orderId = resultSetOrder.getInt("OrderID");

                return readById(Long.valueOf(orderId));
            }
        } catch (Exception e) {
            LOGGER.debug(e);
        }
        return null;
    }

    @Override
    public Order create(Order t) {
        try {
            Connection connection = dbUtils.getConnection();

            for(Integer itemId : t.getItemIDs()){
                PreparedStatement statement = connection.prepareStatement("INSERT INTO ORDERS (OrderID, CustomerId, ItemId) VALUES (?,?,?)");
                statement.setInt(1, t.getOrderID());
                statement.setInt(2, t.getCustomerID());
                statement.setInt(3, itemId);
    
                statement.executeUpdate();

                return readLatest();
            }            
        } catch (Exception e) {
            LOGGER.debug(e);
        }
        return null;
    }

    @Override
    public Order update(Order t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(Long id) {
        try {
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("DELETE FROM ORDERS WHERE CustomerID = ?");
            statement.setInt(1, id.intValue());

            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            LOGGER.debug(e);
            return 0;
        }
    }

    public void deleteItemFromOrder(Long itemId, Long orderId){
        try {
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("DELETE FROM ORDERS WHERE ItemId = ? and OrderID = ?");
            statement.setInt(1, itemId.intValue());
            statement.setInt(2, orderId.intValue());

            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    public void addItemToOrder(Long itemId, Long orderId){
        try {
            Connection connection = dbUtils.getConnection();

            Order order = readById(orderId);

            PreparedStatement statement = connection.prepareStatement("INSERT INTO ORDERS (OrderID, CustomerId, ItemId) VALUES (?,?,?)");
            statement.setInt(1, orderId.intValue());
            statement.setInt(2, order.getCustomerID());
            statement.setInt(3, itemId.intValue());

            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    public double calculateCost(Long orderId){
        try {
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT Cost from ORDERS LEFT JOIN ITEMS ON ORDERS.ItemId = ITEMS.ItemID where OrderID = ?");
            statement.setInt(1, orderId.intValue());

            ResultSet resultSet = statement.executeQuery();

            double cost = 0;

            while (resultSet.next()) {
                cost = cost + resultSet.getDouble("Cost");
            }
            return cost;
        } catch (Exception e) {
            LOGGER.debug(e);
            return 0;
        }
    }

    @Override
    public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
