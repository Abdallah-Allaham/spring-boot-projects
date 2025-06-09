# Order API

---

## 🚀 Project Overview

This project is a backend API for a simple e-commerce order management system, built using the powerful **Spring Boot** framework. It provides core functionalities for managing customers, products, and orders.

---

## ✨ Key Features

* **Customer Management:**
    * Create, read, update, and delete customer.
    * Ensures unique email for customers.
* **Product Management:**
    * Create, read, update, and delete product.
    * Separate endpoint for updating product quantity.
* **Order Management:**
    * Create new orders involving multiple products and link them to customers.
    * Validates stock availability before order creation.
    * Automatically deducts quantities from stock upon order creation.
    * Read individual orders or all orders.
    * Read orders by a specific customer.
    * Update order status (PENDING, COMPLETED, DELIVERED).
    * Delete orders.
* **Order Items:**
    * Managed automatically as part of the Order entity (cascaded operations).
* **RESTful Design:** Utilizes REST principles for a clean and organized API.
* **Relationship Management:** Effectively handles relationships between entities (one customer has many orders, an order has many order items, an order item links to a product).

---

## 🛠️ Technologies Used

* **Spring Boot:** The primary framework for building the application.
* **Spring Data JPA:** For easy and efficient database interaction.
* **Hibernate:** The JPA implementation used by Spring Data JPA.
* **H2 Database:** A lightweight, in-memory database ideal for development and testing purposes.
* **Maven:** A build automation and dependency management tool.
* **Lombok:** To reduce boilerplate code (like Getters/Setters) using annotations.

---

## 📂 Project Structure

The project consists of the following core layers:

* `src/main/java/com/abdallah/order_api/model`: Contains the **Entities** representing the database tables (`Customer`, `Product`, `Order`, `OrderItem`).
* `src/main/java/com/abdallah/order_api/repo`: Houses the **Repositories** that provide the interface for database operations (CRUD operations).
* `src/main/java/com/abdallah/order_api/service`: Contains the **Services** that encapsulate the Business Logic and orchestrate interactions between repositories.
* `src/main/java/com/abdallah/order_api/controller`: Holds the **Controllers** responsible for receiving and handling HTTP requests, then returning responses.
* `src/main/resources/application.properties`: Application and database configuration file.
* `src/main/resources/data.sql`: An SQL file containing initial (dummy) data, loaded automatically when the application starts.
* `src/main/java/com/abdallah/order_api/OrderApiApplication.java`: The main entry point for the Spring Boot application.

---

## 🧪 Testing the APIs with Postman

The API operates on `http://localhost:8080/`. You can use Postman to test the following endpoints:

### **Products (`/product`)**

* `GET /product` : Retrieve all products.
* `GET /product/{id}` : Retrieve a product by its ID.
* `POST /product` : Create a new product.
    * **Body (JSON):** `{"name": "...", "price": ..., "Quantity": ..., "description": "...", "imageUrl": "..."}`
* `PUT /product/{id}` : Update an existing product.
    * **Body (JSON):** `{"id": ..., "name": "...", "price": ..., "quantity": ..., "description": "...", "imageUrl": "..."}`
* `PUT /product/{id}/quantity?newQuantity={value}` : Update only the stock quantity.
* `DELETE /product/{id}` : Delete a product.

### **Customers (`/customer`)**

* `GET /customer` : Retrieve all customers.
* `GET /customer/{id}` : Retrieve a customer by their ID.
* `POST /customer` : Create a new customer.
    * **Body (JSON):** `{"name": "...", "email": "...", "phone": "..."}`
* `PUT /customer/{id}` : Update an existing customer.
    * **Body (JSON):** `{"id": ..., "name": "...", "email": "...", "phone": "..."}`
* `DELETE /customer/{id}` : Delete a customer.

### **Orders (`/order`)**

* `GET /order` : Retrieve all orders (includes order item details).
* `GET /order/{id}` : Retrieve an order by its ID.
* `POST /order` : Create a new order.
    * **Body (JSON):** `{"customerId": ..., "productWithQuantities": {"productId1": quantity1, "productId2": quantity2}}`
    * **Example:** `{"customerId": 1, "productWithQuantities": {"1": 2, "3": 1}}`
* `GET /order/customer/{customerId}` : Retrieve all orders for a specific customer.
* `PUT /order/{id}/status?newStatus={status_value}` : Update the status of an order.
    * **Example:** `PUT /order/1/status?newStatus=DELIVERED`
* `DELETE /order/{id}` : Delete an order.
