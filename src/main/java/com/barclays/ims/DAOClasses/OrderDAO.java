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

public class OrderDAO implements DAO<Order> {

    @Override
    public List<Order> readAll() throws SQLException {
        
        List<Order> orderList = new ArrayList<>();
        DbUtils dbUtils = new DbUtils();
        Connection connection = dbUtils.getConnection();

        PreparedStatement statement = 
                connection.prepareStatement("SELECT DISTINCT OrderID, CustomerId from ORDERS");

        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next()){
           
            int orderId = resultSet.getInt("OrderID");
            int customerId = resultSet.getInt("CustomerId");

            PreparedStatement statementItems = 
                connection.prepareStatement("Select ItemId from ORDERS where OrderId = ?");
                statementItems.setInt(1, orderId);

                ResultSet resultSetItems = statementItems.executeQuery();

                List<Integer> itemIDs = new ArrayList<>();

                while(resultSetItems.next()){
                    int itemId = resultSetItems.getInt("ItemId");
                    itemIDs.add(itemId);
                }        

            Order order = new Order(orderId, customerId, itemIDs);
            orderList.add(order);
        }
        return orderList;
    }

    @Override
    public Order readById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Order readLatest() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Order create(Order t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Order update(Order t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(Long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
