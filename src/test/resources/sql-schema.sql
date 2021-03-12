--Schema file for the testing database.
--This should be picked up by the embedded H2 when running your tests.

DROP SCHEMA `ims`;
CREATE SCHEMA IF NOT EXISTS `ims`;
USE `ims`;

CREATE TABLE IF NOT EXISTS CUSTOMERS (CustomerID SMALLINT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(40) NOT NULL);

CREATE TABLE IF NOT EXISTS ITEMS (ItemID SMALLINT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(40) NOT NULL, Cost DOUBLE NOT NULL);

CREATE TABLE IF NOT EXISTS ORDERS (NoRecords SMALLINT AUTO_INCREMENT PRIMARY KEY, OrderID SMALLINT NOT NULL, CustomerId SMALLINT NOT NULL, ItemId SMALLINT NOT NULL, FOREIGN KEY (ItemId) REFERENCES ITEMS(ItemID) ON UPDATE CASCADE ON DELETE CASCADE, FOREIGN KEY (CustomerId) REFERENCES CUSTOMERS(CustomerID) ON UPDATE CASCADE ON DELETE CASCADE);


INSERT INTO CUSTOMERS (Name) VALUES ('Lily'), ('Jake'), ('Mark'), ('Andrew'), ('Anna'), ('Phil'), ('Anthony');

INSERT INTO ITEMS (Name, Cost) VALUES ('Processor', 200), ('Motherboard', 150), ('Power Supply', 100), ('Case', 125), ('Ram', 85), ('Fan', 10);

INSERT INTO ORDERS (OrderID, CustomerId, ItemId) VALUES (1, 2, 1), (1, 2, 2), (1, 2, 3), (2, 5, 5), (2, 5, 6), (3, 1, 3), (3, 1, 6);
