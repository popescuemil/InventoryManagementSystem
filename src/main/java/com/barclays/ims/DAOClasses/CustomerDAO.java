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

public class CustomerDAO implements DAO<Customer> {

    @Override
    public List<Customer> readAll() throws SQLException {

        List<Customer> customerList = new ArrayList<>();
        DbUtils dbUtils = new DbUtils();
        Connection connection = dbUtils.getConnection();

        PreparedStatement statement = 
                connection.prepareStatement("SELECT * FROM CUSTOMERS");

        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next()){
            int customerId = resultSet.getInt("CustomerID");
            String customerName = resultSet.getString("Name");

            Customer customer = new Customer(customerId, customerName);
            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public Customer readById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Customer readLatest() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Customer create(Customer t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Customer update(Customer t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(Long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Customer modelFromResultSet(ResultSet resultSet) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
