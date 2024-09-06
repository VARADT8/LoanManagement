# Loan Management System

This is a console-based Loan Management System implemented in Java using JDBC for MySQL database interactions. It allows users to manage loans and payments through a simple command-line interface.

## Features

- Loan Management:
  - Add new loan accounts
  - View loan details
  - Update loan information
  - Remove loan accounts
- Payment Management:
  - Record loan payments
  - Calculate interest
  - View payment history for a loan

## Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven 3.6 or higher
- MySQL Server 8.0 or higher

## Project Structure

```
loan-management-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── loanmanagement/
│   │   │           ├── dao/
│   │   │           ├── model/
│   │   │           ├── service/
│   │   │           ├── ui/
│   │   │           └── LoanManagementApp.java
│   │   └── resources/
│   │       └── loan_management_schema.sql
│   └── test/
│       └── java/
│           └── com/
│               └── loanmanagement/
│                   └── test/
└── pom.xml
```

## Setup

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/loan-management-system.git
   cd loan-management-system
   ```

2. Set up the MySQL database:
   - Log in to MySQL as a user with sufficient privileges
   - Create a new database named `loan_management`:
     ```sql
     CREATE DATABASE loan_management;
     ```
   - Use the newly created database:
     ```sql
     USE loan_management;
     ```
   - Run the SQL script in `src/main/resources/loan_management_schema.sql` to create the necessary tables

3. Update the database connection details:
   - Open `src/main/java/com/loanmanagement/dao/DatabaseConnection.java`
   - Update the `URL`, `USER`, and `PASSWORD` constants with your MySQL connection details:
     ```java
     private static final String URL = "jdbc:mysql://localhost:3306/loan_management";
     private static final String USER = "your_username";
     private static final String PASSWORD = "your_password";
     ```

4. Build the project:
   ```
   mvn clean package
   ```

## Running the Application

After building the project, you can run the application using the following command:

```
java -cp target/loan-management-system-1.0-SNAPSHOT.jar com.loanmanagement.LoanManagementApp
```

## Usage

Upon starting the application, you'll be presented with a main menu:

```
--- Loan Management System ---
1. Loan Management
2. Payment Management
3. Exit
```

### Loan Management

Selecting option 1 will bring you to the Loan Management menu:

```
--- Loan Management ---
1. Add new loan account
2. View loan details
3. Update loan information
4. Remove loan account
5. Back to main menu
```

- To add a new loan, choose option 1 and follow the prompts to enter loan details.
- To view loan details, choose option 2 and enter the loan ID when prompted.
- To update loan information, choose option 3, enter the loan ID, and then provide the new information.
- To remove a loan account, choose option 4 and enter the loan ID of the account you wish to remove.

### Payment Management

Selecting option 2 from the main menu will bring you to the Payment Management menu:

```
--- Payment Management ---
1. Record loan payment
2. Calculate interest
3. View payment history
4. Back to main menu
```

- To record a payment, choose option 1 and follow the prompts to enter payment details.
- To calculate interest for a loan, choose option 2 and enter the loan ID when prompted.
- To view payment history for a loan, choose option 3 and enter the loan ID.

## Contributing

Contributions to improve the Loan Management System are welcome. Please follow these steps to contribute:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/AmazingFeature`)
3. Make your changes
4. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
5. Push to the branch (`git push origin feature/AmazingFeature`)
6. Open a Pull Request

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Contact

If you have any questions or suggestions, please open an issue on the GitHub repository.
