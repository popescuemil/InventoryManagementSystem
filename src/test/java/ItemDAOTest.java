import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.barclays.ims.DbUtils;
import com.barclays.ims.DAOClasses.ItemDAO;
import com.barclays.ims.Models.Item;

import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestItemDAO {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void createItem() {
        Item testItem = new Item("HDD", 135);
        ItemDAO itemDAO = new ItemDAO();
        Item resultItem = itemDAO.create(testItem);
        assertEquals(testItem.getItemName(), resultItem.getItemName());
    }

    @Test
    public void updateItem() {
        ItemDAO itemDAO = new ItemDAO();
        Item resultItem = itemDAO.update(new Item(6, "Fans", 10));
        assertEquals("Fans", resultItem.getItemName());
    }

    @Test
    public void deleteItem() {
        int itemId = 3;
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.delete(Long.valueOf(itemId));
        assertEquals(null, itemDAO.readById(Long.valueOf(itemId)));
    }

    @Test
    public void readLatestItem() {
        DbUtils dbUtils = new DbUtils();
        ItemDAO itemDAO = new ItemDAO();
        try {
            Connection connection = dbUtils.getConnection();
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO IMS.ITEMS (Name, Cost) VALUES (?,?)");
            statement.setString(1, "RGB Strips");
            statement.setDouble(2, 25.3);
            statement.executeUpdate();
            assertEquals("RGB Strips", itemDAO.readLatest().getItemName());
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    @Test
    public void readItemById() {
        DbUtils dbUtils = new DbUtils();
        ItemDAO itemDAO = new ItemDAO();
        try {
            Connection connection = dbUtils.getConnection();
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO IMS.ITEMS (Name, Cost) VALUES (?,?)");
            statement.setString(1, "RGB Strip");
            statement.setDouble(2, 15.1);
            statement.executeUpdate();

            assertEquals("RGB Strip", itemDAO.readById(Long.valueOf(itemDAO.readLatest().getItemID())).getItemName());
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }
}
