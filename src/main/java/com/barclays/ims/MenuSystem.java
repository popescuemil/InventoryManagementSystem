package com.barclays.ims;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.barclays.ims.DAOClasses.CustomerDAO;
import com.barclays.ims.DAOClasses.ItemDAO;
import com.barclays.ims.DAOClasses.OrderDAO;
import com.barclays.ims.Models.Customer;
import com.barclays.ims.Models.Item;
import com.barclays.ims.Models.Order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * Class responsible for the functionality of the menu system.
 * You may build this out in any way you wish, provided that you stick to CLI.
 * GUIs, such as swing, are not permitted.
 */
public class MenuSystem {

    public static final Logger LOGGER = LogManager.getLogger();

    public MenuSystem() {
        init();
    }

    public void init() {
        DbUtils.connect();
        DbUtils dbUtils = new DbUtils();
        dbUtils.executeSQLFile("src\\main\\resources\\sql-schema.sql");
        
        while(true){

            ScannerUtils scanner = new ScannerUtils();

            System.out.println("\nChoose which action you want to perform: \n");
            System.out.println("(1): Add a customer to the system\n" +
            "(2): View all customers in the system\n" +
            "(3): Update a customer in the system\n" +
            "(4): Delete a customer in the system\n" +
            "(5): Add an item to the system\n" +
            "(6): View all items in the system\n" +
            "(7): Update an item in the system\n" +
            "(8): Delete an item in the system\n" +
            "(9): Create an Order in the system\n" +
            "(10): View all Orders in the system\n" +
            "(11): Delete an Order in the system\n" +
            "(12): Add an item to an Order\n" +
            "(13): Calculate a cost for an Order\n" +
            "(14): Delete an item in an Order\n" +
            "(15): Exit\n");

            String option = scanner.getString();

            switch(option){

                case "1": 
                    System.out.println("\nEnter Customer Name: ");
                    String customerName = scanner.getString();
                    addCustomer(customerName);
                    break;

                case "2": 
                    printAllCustomers();
                    System.out.println("\nPress ENTER to go back to menu");
                    scanner.getString();
                    break;

                case "3": 
                    System.out.println("\nEnter the ID of the Customer you want to update: ");
                    Long custId = scanner.getLong();
                    System.out.println("\nEnter the updated Name: ");
                    String custName = scanner.getString();
                    updateCustomer(custId, custName);
                    break;

                case "4": 
                    System.out.println("\nEnter the ID of the Customer you want to delete: ");
                    Long customerId = scanner.getLong();
                    deleteCustomer(customerId);
                    break;

                case "5": 
                    System.out.println("\nEnter Item Name: ");
                    String itemName = scanner.getString();
                    System.out.println("\nEnter Item cost: ");
                    Double itemCost = scanner.getDouble();
                    addItem(itemName, itemCost);
                    break;

                case "6": 
                    printAllItems();
                    System.out.println("\nPress ENTER to go back to menu");
                    scanner.getString();
                    break;

                case "7":
                    System.out.println("\nEnter the ID of the Item you want to update: ");
                    Long itmId = scanner.getLong();
                    System.out.println("\nEnter the updated Item Name: ");
                    String itmName = scanner.getString();
                    System.out.println("\nEnter the updated Item cost: ");
                    Double itmCost = scanner.getDouble();
                    updateItem(itmId, itmName, itmCost);
                    break;

                case "8":
                    System.out.println("\nEnter the ID of the Item you want to delete: ");
                    Long itemId = scanner.getLong();
                    deleteItem(itemId); 
                    break;

                case "9": 
                    break;

                case "10": 
                    printAllOrders();
                    System.out.println("\nPress ENTER to go back to menu");
                    scanner.getString();
                    break;

                case "11": 
                    break;

                case "12": 
                    break;

                case "13": 
                    break;

                case "14": 
                    break;

                case "15": 
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nInvalid selection");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    LOGGER.debug(e);
                }
                    break;
            }
        }
    }

    private void printAllCustomers(){
        CustomerDAO customer = new CustomerDAO();
        try {
            List<Customer> customerList = customer.readAll();

            for (Customer c : customerList) {
                System.out.println(c.toString());
            }
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    private void printAllItems(){
        ItemDAO item = new ItemDAO();
        try {
            List<Item> itemList = item.readAll();

            for (Item i : itemList) {
                System.out.println(i.toString());
            }
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    private void printAllOrders(){
        OrderDAO order = new OrderDAO();
        try {
            List<Order> orderList = order.readAll();

            for (Order o : orderList) {
                System.out.println(o.toString());
            }
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    private void addCustomer(String customerName){
        Customer customer = new Customer(customerName);
        CustomerDAO customerDAO = new CustomerDAO();
        try {
            customerDAO.create(customer);
            
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    private void deleteCustomer(long customerId){
        CustomerDAO customerDAO = new CustomerDAO();
        try {
            customerDAO.delete(customerId);           
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    private void updateCustomer(Long customerID, String customerName){
        Customer customer = new Customer(customerID.intValue(), customerName);
        CustomerDAO customerDAO = new CustomerDAO();
        try {
            customerDAO.update(customer);          
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    private void addItem(String itemName, Double itemCost){
        Item item = new Item(itemName, itemCost);
        ItemDAO itemDAO = new ItemDAO();
        try {
            itemDAO.create(item);          
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    private void updateItem(Long itmId, String itmName, Double itmCost){
        Item item = new Item(itmId.intValue(), itmName, itmCost);
        ItemDAO itemDAO = new ItemDAO();
        try {
            itemDAO.update(item);          
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    private void deleteItem(Long itemId){
        ItemDAO itemDAO = new ItemDAO();
        try {
            itemDAO.delete(itemId);          
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }
}


