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
    DbUtils dbUtils = new DbUtils();

    @Override
    public List<Item> readAll() {
        try {
            List<Item> itemList = new ArrayList<>();

            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ITEMS");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int itemId = resultSet.getInt("ItemID");
                String itemName = resultSet.getString("Name");
                double itemCost = resultSet.getDouble("Cost");

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
        try {
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ITEMS WHERE ItemID = ?");
            statement.setInt(1, id.intValue());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String itemName = resultSet.getString("Name");
                double itemCost = resultSet.getDouble("Cost");

                return (new Item(id.intValue(), itemName, itemCost));
            }
        } catch (Exception e) {
            LOGGER.debug(e);
        }
        return null;
    }

    @Override
    public Item readLatest() {
        try {
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ITEMS ORDER BY ItemID DESC LIMIT 1");

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                int itemId = resultSet.getInt("ItemID");
                String itemName = resultSet.getString("Name");
                double itemCost = resultSet.getDouble("Cost");

                return (new Item(itemId, itemName, itemCost));
            }
        } catch (Exception e) {
            LOGGER.debug(e);
        }
        return null;
    }

    @Override
    public Item create(Item t) {
        try {
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO ITEMS (Name, Cost) VALUES (?,?)");
            statement.setString(1, t.getItemName());
            statement.setDouble(2, t.getItemCost());

            statement.executeUpdate();

            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            return null;
        }
    }

    @Override
    public Item update(Item t) {
        try {
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("UPDATE ITEMS SET Name = ?, Cost = ? WHERE ItemID = ?");
            statement.setString(1, t.getItemName());
            statement.setDouble(2, t.getItemCost());
            statement.setInt(3, t.getItemID());

            statement.executeUpdate();

            return readById(Long.valueOf(t.getItemID()));
        } catch (Exception e) {
            LOGGER.debug(e);
            return null;
        }
    }

    @Override
    public int delete(Long id) {
        try {
            Connection connection = dbUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("DELETE FROM ITEMS WHERE ItemID = ?");
            statement.setInt(1, id.intValue());

            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            LOGGER.debug(e);
            return 0;
        }
    }

    @Override
    public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
