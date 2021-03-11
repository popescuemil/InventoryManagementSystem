package com.barclays.ims.DAOClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.barclays.ims.DAO;
import com.barclays.ims.DbUtils;
import com.barclays.ims.Models.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerDAO implements DAO<Customer> {
    private static final Logger LOGGER = LogManager.getLogger();
    DbUtils dbUtils = new DbUtils();

    @Override
    public List<Customer> readAll() {
        try {
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
            return customerList;
        } catch (Exception e) {
            LOGGER.debug(e);
            return null;
        }
    }

    @Override
    public Customer readById(Long id) {
        try {
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM IMS.CUSTOMERS WHERE CustomerID = ?");
            statement.setInt(1, id.intValue());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String customerName = resultSet.getString("Name");

                return (new Customer(id.intValue(), customerName));
            }
        } catch (Exception e) {
            LOGGER.debug(e);
        }
        return null;
    }

    @Override
    public Customer readLatest() {
        try {
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM IMS.CUSTOMERS ORDER BY CustomerID DESC LIMIT 1");

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                int customerId = resultSet.getInt("CustomerID");
                String customerName = resultSet.getString("Name");

                return (new Customer(customerId, customerName));
            }
        } catch (Exception e) {
            LOGGER.debug(e);
        }
        return null;
    }

    @Override
    public Customer create(Customer t) {
        try {
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO IMS.CUSTOMERS (Name) VALUES (?)");
            statement.setString(1, t.getCustomerName());

            statement.executeUpdate();

            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            return null;
        }
    }

    @Override
    public Customer update(Customer t) {
        try {
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("UPDATE IMS.CUSTOMERS SET Name = ? WHERE CustomerID = ?");
            statement.setString(1, t.getCustomerName());
            statement.setInt(2, t.getCustomerID());

            statement.executeUpdate();

            return readById(Long.valueOf(t.getCustomerID()));
        } catch (Exception e) {
            LOGGER.debug(e);
            return null;
        }
    }

    @Override
    public int delete(Long id) {       
        try {
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("DELETE FROM IMS.CUSTOMERS WHERE CustomerID = ?");
            statement.setInt(1, id.intValue());

            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            LOGGER.debug(e);
            return 0;
        }
    }

    @Override
    public Customer modelFromResultSet(ResultSet resultSet) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
