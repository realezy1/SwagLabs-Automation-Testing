# Swag Labs E-Commerce Website – Automation Testing

## 1. Project Overview

This project is a Web UI Automation Testing project for the Swag Labs demo e-commerce application.

The project was developed to automate functional testing of the main application features using Selenium WebDriver, Java, and TestNG.

In addition to automation testing, Jira was used to organize and manage test cases, track testing activities, and document test execution results.

The testing focuses on login functionality, product catalog, sidebar navigation, product sorting, shopping cart management, and checkout processes.

**Application Under Test:** [Swag Labs](https://www.saucedemo.com/)

## 2. Objective of Testing

The objectives of this project are:

- Verify the functionality and availability of login page elements.
- Validate login behavior using empty, incomplete, and invalid credentials.
- Verify successful authentication using valid credentials.
- Validate product information, including product names, descriptions, images, and prices.
- Verify navigation to additional catalog pages through the sidebar.
- Validate product sorting dropdown options.
- Verify adding and removing products from the shopping cart.
- Validate checkout workflows for single-product and multiple-product transactions.
- Verify order information and the order completion page.
- Organize and document test cases using Jira.
- Track test execution status and testing progress through Jira.

## 3. Tools and Technologies

| Tools / Technologies | Version | Purpose |
|---|---|---|
| Java JDK | 21 | Programming language |
| Selenium WebDriver | 4.51.0 | Browser automation and web UI testing |
| TestNG | 7.12.0 | Test execution and assertions |
| Maven | - | Project and dependency management |
| IntelliJ IDEA | - | Integrated Development Environment |
| Jira | - | Test case management and test execution tracking |
| Google Chrome | - | Browser used for testing |
| ChromeDriver | Compatible version | Browser automation driver |

## 4. Test Management Using Jira

Jira was used to support test management activities throughout the testing process.

The activities included:

- Creating and organizing test cases for login, product catalog, sorting, cart, and checkout functionalities.
- Documenting test steps and expected results.
- Managing test case execution status.
- Tracking testing progress and identifying test cases that required further validation.
- Maintaining test documentation for testing activities.

### Test Case Coverage

The test cases were organized based on the application's main functional areas:

| Test Module | Testing Scope |
|---|---|
| Login | Valid credentials, invalid credentials, and empty input validation |
| Product Catalog | Product information and product availability |
| Sidebar Navigation | Navigation to additional catalog pages |
| Product Sorting | Sorting options and selected values |
| Shopping Cart | Add, remove, and cart validation |
| Checkout | Single-product and multiple-product checkout workflows |

**Jira Project:** [Insert Jira Project Link]

**Jira Evidence:** Add screenshots of the Jira project, test cases, and execution results.

## 5. Test Scenarios

This project contains 15 TestNG test methods organized into two test classes.

### A. LoginPageTest.java

| No. | Test Method | Test Scenario | Expected Result |
|---|---|---|---|
| 1 | checkElement | Verify login page elements | Login logo, username, password, and login button display expected properties. |
| 2 | LoginWithNullValue | Submit login with empty credentials and missing password | The application displays the appropriate required-field error message. |
| 3 | LoginWithWrongValue | Submit incorrect username, incorrect password, and both incorrect credentials | The application displays an invalid credentials error message. |
| 4 | LoginWithCorrectValue | Login using valid credentials | The user is redirected to the inventory page. |

### B. HomePageTest.java

| No. | Test Method | Test Scenario | Expected Result |
|---|---|---|---|
| 1 | checkElement | Verify product catalog elements | Product images, names, descriptions, and prices match expected values. |
| 2 | openSideBar1 | Navigate to the dynamic catalog lazy load page | The application navigates to the expected page. |
| 3 | openSideBar2 | Navigate to the dynamic catalog spinner page | The application navigates to the expected page. |
| 4 | openSideBar3 | Navigate to the dynamic catalog slider page | The application navigates to the expected page. |
| 5 | dropdownTest1 | Verify product sorting dropdown | Dropdown is displayed, enabled, and contains four options with the expected default selection. |
| 6 | dropdownTest2 | Select Name (Z to A) | The selected dropdown option is Name (Z to A). |
| 7 | dropdownTest3 | Select Price (low to high) | The selected dropdown option is Price (low to high). |
| 8 | dropdownTest4 | Select Price (high to low) | The selected dropdown option is Price (high to low). |
| 9 | fakeShopping | Add and remove a product from the cart | Product can be added and the cart page can be opened. |
| 10 | shoppingOneProduct | Complete checkout with one product | Product information, checkout details, order summary, and completion page display expected values. |
| 11 | shoppingMultipleProduct | Complete checkout with multiple products | Cart quantity, product information, product removal, subtotal, and order completion are validated. |

## 6. Test Execution Instructions

### Prerequisites

Make sure the following tools are installed:

- Java Development Kit (JDK) 21
- IntelliJ IDEA
- Google Chrome
- ChromeDriver compatible with the installed Chrome version
- Maven

### Steps to Run

1. Clone this repository:

   ```bash
   git clone https://github.com/realezy1/SwagLabs-Automation-Testing.git
   ```

2. Open the project in IntelliJ IDEA.

3. Reload Maven dependencies.

4. Configure the ChromeDriver path in the test classes:

   ```java
   System.setProperty(
       "webdriver.chrome.driver",
       "D:/WebDriver/chromedriver.exe"
   );
   ```

   Replace the path with the location of ChromeDriver on your computer.

5. For HomePageTest.java, update the Chrome user-data directory if necessary:

   ```java
   options.addArguments("--user-data-dir=D:/SeleniumChromeProfile");
   ```

6. Run the test classes using IntelliJ IDEA.

   Alternatively, if the Maven test configuration is available, execute:

   ```bash
   mvn test
   ```

## 7. Test Results

The test scripts use TestNG assertions, including `Assert.assertEquals()` and `Assert.assertTrue()`, to validate actual application behavior against expected results.

### Test Execution Summary

| Test Class | Total Tests | Passed | Failed | Skipped |
|---|---:|---:|---:|---:|
| LoginPageTest | 4 | 4 | 0 | 0 |
| HomePageTest | 11 | 11 | 0 | 0 |
| **Total** | **15** | **15** | **0** | **0** |

**Overall Pass Rate: 100%**

All 15 test methods passed based on the reported TestNG execution results.

### Test Execution Management

Jira was used to document and monitor manual test case execution and testing progress.

The automation execution results were obtained from TestNG reports.

## 8. Project Structure

```text
SwagLabs-Automation-Testing/
│
├── src/
│   └── test/
│       └── java/
│           └── SwagLabsSauceDemo/
│               ├── LoginPageTest.java
│               └── HomePageTest.java
│
├── pom.xml
├── .gitignore
└── README.md
```

## 9. Screenshots and Testing Evidence

The following screenshots provide evidence of the automation testing activities and execution results.

### A. Automation Test Execution

**LoginPageTest.java**

<img width="1911" height="1077" alt="LoginPageTest execution result" src="https://github.com/user-attachments/assets/d93a89c4-edfc-43fd-894d-92d79b68b9cf" />

**HomePageTest.java**

<img width="1917" height="1077" alt="HomePageTest execution result" src="https://github.com/user-attachments/assets/cc6e35e5-b2ff-46bf-acd6-f35a0dd273af" />

### B. Jira Test Management Evidence

<img width="1917" height="968" alt="image" src="https://github.com/user-attachments/assets/a0cbf5b5-0867-4b4c-a383-a050b9067bd3" />
- Jira project overview or board.
- Created test cases and their test steps.
- Test execution status and results.
- Testing progress and documentation.

## 10. Project Information

| Category | Details |
|---|---|
| Project Type | Web UI Automation Testing |
| Application Under Test | Swag Labs |
| Testing Approach | Functional Testing |
| Automation Tool | Selenium WebDriver |
| Programming Language | Java |
| Test Framework | TestNG |
| Test Management Tool | Jira |
| Build Tool | Maven |
| Browser | Google Chrome |
| Total Automated Test Methods | 15 |
| Overall Pass Rate | 100% |

## 11. Repository

**GitHub Repository:** [SwagLabs-Automation-Testing](https://github.com/realezy1/SwagLabs-Automation-Testing)

**Application Under Test:** [Swag Labs](https://www.saucedemo.com/)

---

*This project was developed as a personal learning project to practice software testing, web UI automation, test management, and quality assurance practices.*
