package com.barclays.ims;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

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
                    break;
                case "2": 
                    printAllCustomers();
                    break;
                case "3": 
                    break;
                case "4": 
                    break;
                case "5": 
                    break;
                case "6": 
                    printAllItems();
                    break;
                case "7": 
                    break;
                case "8": 
                    break;
                case "9": 
                    break;
                case "10": 
                    printAllOrders();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printAllItems(){
        ItemDAO item = new ItemDAO();
        try {
            List<Item> itemList = item.readAll();

            for (Item i : itemList) {
                System.out.println(i.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printAllOrders(){
        OrderDAO order = new OrderDAO();
        try {
            List<Order> orderList = order.readAll();

            for (Order o : orderList) {
                System.out.println(o.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
