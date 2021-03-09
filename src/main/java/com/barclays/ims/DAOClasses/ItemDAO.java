package com.barclays.ims.DAOClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.barclays.ims.DAO;
import com.barclays.ims.DbUtils;
import com.barclays.ims.Models.Item;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ItemDAO implements DAO<Item> {
    
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public List<Item> readAll() {
        try {
            List<Item> itemList = new ArrayList<>();
            DbUtils dbUtils = new DbUtils();
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ITEMS");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int itemId = resultSet.getInt("ItemID");
                String itemName = resultSet.getString("Name");
                int itemCost = resultSet.getInt("Cost");

                Item item = new Item(itemId, itemName, itemCost);
                itemList.add(item);
            }
            return itemList;
        } catch (Exception e) {
            LOGGER.debug(e);
            return null;
        }
    }

    @Override
    public Item readById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item readLatest() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item create(Item t) {
        try {
            DbUtils dbUtils = new DbUtils();
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO ITEMS (Name, Cost) VALUES (?,?)");
            statement.setString(1, t.getCustomerName());
            statement.setDouble(2, t.getItemCost());

            statement.executeUpdate();
            return null;
        } catch (Exception e) {
            LOGGER.debug(e);
            return null;
        }
    }

    @Override
    public Item update(Item t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(Long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
