# PaymentGateway_ApI-02
# E-commerce Checkout & Payment API

A backend REST API for handling the checkout and payment process of an e-commerce application.

This project is built using Spring Boot and provides APIs for placing orders, creating Razorpay payment orders, verifying payments, storing customer and shipping information, and retrieving customer orders.

The application follows a layered architecture with separate Controller, Service, Repository, DTO, and Entity layers.

---

## Features

- Place e-commerce orders
- Store customer information
- Store shipping address
- Store order and order-item details
- Create Razorpay payment orders
- Verify Razorpay payment signatures
- Save orders after successful payment verification
- Maintain payment status
- Store Razorpay Order ID and Payment ID
- Retrieve customer order history
- RESTful API endpoints
- MySQL database integration
- Request validation support
- Transaction management using `@Transactional`

---

## Technologies Used

| Technology | Purpose |

| Java 17 | Application development |
| Spring Boot 3.2.0 | Backend framework |
| Spring Web | REST API development |
| Spring Data JPA | Database interaction |
| Hibernate / JPA | ORM and entity mapping |
| MySQL | Relational database |
| Razorpay Java SDK | Payment gateway integration |
| JSON | JSON request/response handling |
| Bean Validation | Request validation |
| Lombok | Reducing boilerplate code |
| Maven | Dependency management and build |

---

## Project Structure

src
└── main
    ├── java
    │   └── com.satendrait.ecommerce_checkout
    │       │
    │       ├── config
    │       │   └── RazorpayConfig.java
    │       │
    │       ├── controller
    │       │   ├── CheckoutController.java
    │       │   ├── PaymentController.java
    │       │   ├── OrderQueryController.java
    │       │   └── HelloController.java
    │       │
    │       ├── dto
    │       │   ├── PurchaseDTO.java
    │       │   ├── PurchaseResponse.java
    │       │   ├── PaymentCallbackDTO.java
    │       │   └── PaymentOrderResponse.java
    │       │
    │       ├── entity
    │       │   ├── Customer.java
    │       │   ├── Address.java
    │       │   ├── Order.java
    │       │   └── OrderItem.java
    │       │
    │       ├── repository
    │       │   ├── CustomerRepository.java
    │       │   ├── AddressRepository.java
    │       │   ├── OrderRepository.java
    │       │   └── OrderItemRepository.java
    │       │
    │       ├── service
    │       │   ├── CheckoutService.java
    │       │   ├── CheckoutServiceImpl.java
    │       │   ├── OrderService.java
    │       │   └── OrderServiceImpl.java
    │       │
    │       ├── EcommerceCheckoutApplication.java
    │       └── PaymentStatus.java
    │
    └── resources
        └── application.properties

---

## Main Components

### CheckoutController

Provides the checkout endpoint used to place an order.

Endpoint:

POST /api/checkout/purchase

It receives the purchase information and delegates the order processing to the service layer.

---

### PaymentController

Handles Razorpay payment operations.

Create Razorpay order:

POST /api/payment/create-order

This endpoint creates a Razorpay order using the purchase amount.

Verify payment:

POST /api/payment/verify-payment

This endpoint verifies the Razorpay payment signature and saves the order information after successful payment verification.

---

### OrderQueryController

Provides an API to retrieve orders belonging to a particular customer.

Endpoint:

GET /api/orders/{customerId}

Example:

GET /api/orders/16



## REST API Endpoints

* **GET** `/hello`
  Used to check if the backend is running properly.

* **POST** `/api/checkout/purchase`
  Used to place a new order.

* **POST** `/api/payment/create-order`
  Used to create a Razorpay payment order.

* **POST** `/api/payment/verify-payment`
  Used to verify the payment and save the order details.

* **GET** `/api/orders/{customerId}`
  Used to get all orders of a customer.




## Application URL

http://localhost:9099

---

## Checkout

The checkout API accepts purchase information containing:

- Customer details
- Shipping address
- Order information
- Order items

Endpoint:

POST /api/checkout/purchase

The checkout service processes the purchase information and delegates order persistence to the order service.

---

## Payment Integration

This project integrates the Razorpay payment gateway using the Razorpay Java SDK.

The payment functionality includes:

- Razorpay order creation
- Amount conversion to paise
- INR currency support
- Razorpay Order ID generation
- Razorpay Payment ID handling
- Payment signature verification
- Payment status storage

The application uses HMAC-SHA256 to generate the payment signature used during Razorpay payment verification.

---

## Order Management

Orders are persisted using Spring Data JPA and MySQL.

The Order entity stores information such as:

- Order ID
- Total quantity
- Total price
- Payment status
- Razorpay Order ID
- Razorpay Payment ID
- Customer
- Shipping address
- Order items

Payment status is represented using the `PaymentStatus` enum.

---

## Customer and Address Management

Customer information is stored separately from orders.

When an order is placed, the application checks whether a customer already exists using the customer's email address.

If the customer already exists, the existing customer record is reused.

The shipping address is associated with the customer and the order.

The main relationships are:

Customer → Orders

Customer → Address

Order → Order Items

Order → Customer

Order → Shipping Address

---

## Database

The application uses MySQL as the relational database.

JPA and Hibernate are used for:

- Entity mapping
- Relationship management
- CRUD operations
- Transaction management
- Database persistence

---

## Transaction Management

The order persistence logic uses `@Transactional`.

This ensures that related database operations are handled within a transaction.

The order persistence process includes:

- Customer
- Address
- Order
- Order Items

---

## DTOs

DTOs are used to transfer data between the client and backend.

Important DTOs include:

- `PurchaseDTO`
- `PurchaseResponse`
- `PaymentCallbackDTO`
- `PaymentOrderResponse`

Using DTOs keeps API request and response data separate from the persistence entities.

---

## Repository Layer

The project uses Spring Data JPA repositories for database operations.

Repositories include:

- `CustomerRepository`
- `AddressRepository`
- `OrderRepository`
- `OrderItemRepository`

Spring Data JPA provides the required database operations without writing manual SQL for standard CRUD operations.

---

## Configuration

Razorpay configuration is handled through:

RazorpayConfig.java

The application requires Razorpay credentials to communicate with the Razorpay API.

Sensitive credentials should not be committed to GitHub.

Environment variables can be used for local configuration.

Example:

razorpay.key.id=${RAZORPAY_KEY_ID}
razorpay.key.secret=${RAZORPAY_KEY_SECRET}

---

## Requirements

Before running the project, make sure the following are installed:

- Java 17
- Maven
- MySQL
- Razorpay account and API credentials

---

## Database Setup

Create a MySQL database:

CREATE DATABASE ecommerce_checkout;

Configure the database connection in:

src/main/resources/application.properties

Example configuration:

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_checkout
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Update the username and password according to your local MySQL configuration.

---

## Razorpay Configuration

Configure your Razorpay credentials using environment variables or application configuration.

Example:

razorpay.key.id=${RAZORPAY_KEY_ID}
razorpay.key.secret=${RAZORPAY_KEY_SECRET}

Never expose the Razorpay secret key in source code or commit it to the repository.

For development and testing, Razorpay test credentials should be used.

---

## Running the Application

Clone the repository:

git clone https://github.com/Satendraxx1707/Ecommerce_Checkout_API.git

Navigate to the project:

cd Ecommerce_Checkout_API

Run the application using Maven:

./mvnw spring-boot:run

On Windows:

mvnw.cmd spring-boot:run

The application will be available at:

http://localhost:9099

---

## Testing the API

The APIs can be tested using:

- Postman
- Frontend application
- Any REST API client

Backend health check:

GET http://localhost:9099/hello

Checkout:

POST http://localhost:9099/api/checkout/purchase

Create Razorpay order:

POST http://localhost:9099/api/payment/create-order

Verify payment:

POST http://localhost:9099/api/payment/verify-payment

Get customer orders:

GET http://localhost:9099/api/orders/{customerId}

---

## Architecture

The application is organized into separate layers:

Controller ---->Service ---->Repository  ---->Database
    ↓


### Controller Layer

Handles HTTP requests and responses.

### Service Layer

Contains application and business logic.

### Repository Layer

Handles database communication using Spring Data JPA.

### Entity Layer

Represents database tables and their relationships.

### DTO Layer

Handles API request and response data.

### Config Layer

Contains application-specific configuration such as Razorpay configuration.

---

## Error Handling

The application uses Spring Boot's REST infrastructure to handle API requests and exceptions.

Payment verification fails when the generated Razorpay signature does not match the signature received from the payment response.

---

## CORS

The backend is configured to allow requests from the local frontend application running on:

http://localhost:4200

This allows the backend to communicate with an Angular frontend during local development.

---

## Key Backend Concepts Demonstrated

- RESTful API development
- Spring Boot
- Dependency Injection
- Constructor Injection
- Service Layer Pattern
- Repository Pattern
- Spring Data JPA
- Hibernate ORM
- Entity Relationships
- DTO Pattern
- Transaction Management
- MySQL Integration
- Payment Gateway Integration
- Razorpay Payment Verification
- HMAC-SHA256 Signature Verification
- Enum-based Payment Status
- Maven Dependency Management
- CORS Configuration

---

## Future Improvements

The project can be extended with additional e-commerce features such as:

- JWT-based authentication
- User registration and login
- Product management
- Product search and filtering
- Shopping cart management
- Inventory management
- Order cancellation
- Refund handling
- Global exception handling
- Swagger/OpenAPI documentation
- Role-based authorization
- Payment webhook handling
- Docker support

---

## Project Purpose

The main purpose of this project is to build a backend service for an e-commerce checkout system with payment gateway integration.

The project focuses on checkout processing, customer and address persistence, order management, Razorpay payment integration, payment verification, and customer order retrieval using a structured Spring Boot backend.

---

## Author

Satendra Singh

GitHub:

https://github.com/Satendraxx1707

---
