#SauceDemo E-Commerce Test Automation Framework

A Java-based web UI automation framework built using Selenium WebDriver, TestNG, and Maven to validate essential e-commerce workflows on the SauceDemo application.

The framework follows the Page Object Model (POM) and integrates reusable page classes, configurable browser management, data-driven testing utilities, parallel execution, retry handling, logging, and ExtentReports HTML reporting.

##Table of Contents

 - Project Overview
 - Application Under Test
 - Technology Stack
 - Key Features
 - Framework Architecture
 - Project Structure
 - Test Coverage
 - Prerequisites
 - Getting Started
 - Configuration
 - Test Execution
 - Test Reporting
 - Logging
 - Retry Mechanism
 - Parallel Execution
 - Troubleshooting
 - Future Enhancements
 - Author

##Project Overview

This project demonstrates a maintainable and reusable UI test automation framework for validating the core workflows of an e-commerce web application.

It separates test logic from page-specific interactions and centralizes browser management, reporting, logging, and supporting utilities.

The framework is designed to support:

 - Functional testing of essential e-commerce workflows.
 - Reusable Page Object Model classes.
 - Data-driven testing using CSV utilities.
 - Chrome and Microsoft Edge browser execution.
 - Configurable headless browser execution.
 - TestNG groups and suite-based execution.
 - Parallel test execution.
 - Retry handling for failed tests.
 - HTML test reporting and failure screenshots.
 - Centralized logging for debugging and troubleshooting.

##Application Under Test

**Application:** SauceDemo

**URL:** https://www.saucedemo.com/

SauceDemo is a sample e-commerce web application used to demonstrate software testing and automation techniques.

This framework automates key shopping workflows, including authentication, product inventory, shopping cart management, checkout, and menu functionality.

The test scenarios include positive and negative login cases, product sorting, adding and removing products, checkout validation, and logout.

##Technology Stack

**Technology	--> 	Version / Details**

 - Programming Language - Java 21
 - Automation Tool -  Selenium WebDriver 4.35.0
 - Test Framework	-  TestNG 7.11.0
 - Build Tool      -  Maven
 - Design Pattern  -  Page Object Model (POM)
 - Reporting       -  ExtentReports 5.1.2
 - Logging	        -  Log4j 2.25.2
 - Maven Compiler Plugin  -  3.14.0
 - Maven Surefire Plugin	- 3.6.0
 - Browsers  -	Google Chrome and Microsoft Edge
 - IDE	- Eclipse
 - Version Control	- Git and GitHub

The listed versions should match the dependencies and plugins configured in pom.xml.

##Key Features

###Framework Design

 - Page Object Model for separation of test logic and page interactions.
 - Reusable page classes and element utilities.
 - Centralized WebDriver creation and management.
 - Configuration-driven browser settings.
 - Support for Chrome and Microsoft Edge.
 - Optional headless browser execution.
 
###Test Execution

 - TestNG annotations and test groups.
 - Separate smoke and regression test suites.
 - Parallel method execution.
 - Thread-local WebDriver management.
 - CSV-based test data utilities.
 - Configurable retry handling for failed tests.
 - Reporting and Logging
 - ExtentReports HTML test reports.
 - Log4j-based execution logging.
 - TestNG listeners for test life cycle events.
 - Automatic screenshots on test failure, where configured.
 - Diagnostic information for investigating failed tests.
 
###Framework Architecture

The framework follows the Page Object Model and separates test execution, page interactions, browser management, and supporting utilities.

                 TestNG Test Classes
                         |
                         v
                  Page Object Classes
                         |
                         v
                Base Page / Utilities
                         |
                         v
                   DriverFactory
                         |
                         v
                  Selenium WebDriver
                         |
                         v
                  SauceDemo Website

Supporting Components:
- Configuration Management
- CSV Test Data Utilities
- TestNG Listeners and Retry Handling
- ExtentReports
- Log4j Logging
- Failure Screenshots

###Architecture Components

 - **Test classes:** Contain test scenarios and assertions.
 - **Page classes:** Encapsulate locators and page-specific actions.
 - **BasePage:** Provides shared page interaction methods, where implemented.
 - **DriverFactory:** Manages WebDriver creation and configuration.
 - **Configuration utilities:** Supply browser and execution settings.
 - **Test data utilities:** Support reusable test data and data providers.
 - **Listeners and retry components:** Handle test life cycle events and configured retry behavior.
 - **Reporting components:** Manage ExtentReports initialization and test entries.
 - **Logging utilities:** Record execution details and diagnostic information.

##Project Structure

AutomationProject/
 - ├── src/
 - │   ├── main/
 - │   │   ├── java/
 - │   │   │   ├── base/
 - │   │   │   ├── config/
 - │   │   │   ├── drivers/
 - │   │   │   │   └── DriverFactory.java
 - │   │   │   ├── Framework/
 - │   │   │   ├── listeners/
 - │   │   │   │   └── TestListener.java
 - │   │   │   ├── pages/
 - │   │   │   │   ├── BasePage.java
 - │   │   │   │   ├── LoginPage.java
 - │   │   │   │   ├── InventoryPage.java
 - │   │   │   │   ├── CartPage.java
 - │   │   │   │   ├── CheckoutPage.java
 - │   │   │   │   └── MenuPage.java
 - │   │   │   ├── reports/
 - │   │   │   │   ├── ExtentManager.java
 - │   │   │   │   └── ExtentTestManager.java
 - │   │   │   ├── retry/
 - │   │   │   └── utils/
 - │   │   └── resources/
 - │   └── test/
 - │       ├── java/
 - │       │   ├── login/
 - │       │   │   └── LoginTest.java
 - │       │   ├── inventory/
 - │       │   │   └── InventoryTest.java
 - │       │   ├── cart/
 - │       │   │   └── CartTest.java
 - │       │   ├── checkout/
 - │       │   │   └── CheckoutTest.java
 - │       │   ├── menu/
 - │       │   │   └── MenuTest.java
 - │       │   ├── retry/
 - │       │   │   └── RetryTest.java
 - │       │   └── testdata/
 - │       │       ├── LoginData.java
 - │       │       └── LoginDataProvider.java
 - │       └── resources/
 - │           ├── log4j2.xml
 - │           ├── testing.xml
 - │           └── testing_regression.xml
 - │   
 - ├── logs/
 - ├── reports/
 - ├── screenshots/
 - ├── test-output/
 - ├── pom.xml
 - ├── .gitignore
 - └── README.md


##Test Coverage

###1. Login Module

 - Verify login with valid credentials.
 - Verify login with invalid credentials.

###2. Inventory Module

 - Verify that the inventory page is displayed.
 - Verify product information.
 - Verify adding and removing products.
 - Verify product sorting.
 - Add products to the shopping cart.

###3. Shopping Cart Module

 - Add a product to the cart.
 - Remove a product from the cart.
 - Add multiple products.
 - Continue shopping.
 - Verify cart contents.

###4. Checkout Module

 - Complete the checkout workflow.
 - Verify validation when customer information is missing.
 - Cancel the checkout process.

###5. Menu Module

 - Verify menu display.
 - Verify logout functionality.

###6. Retry Module

 - Verify configured retry behavior for failed tests.

The actual test cases, assertions, and execution groups are defined in their respective Java classes and TestNG suite files.

##Prerequisites

Ensure the following software is installed:

 - Java Development Kit (JDK) 21.
 - Apache Maven.
 - Google Chrome and/or Microsoft Edge.
 - Git.
 - Eclipse IDE (optional).

Verify your Java and Maven installations:

java -version
mvn -version


Ensure Maven is using JDK 21.

##Getting Started

###1. Clone the Repository

git clone <https://github.com/Sultana-Raziya/automation_framework>

cd AutomationProject

###2. Import the Project into Eclipse

 1. Open Eclipse.
 2. Select **File → Import.**
 3. Choose **Maven → Existing Maven Projects.**
 4. Browse to the cloned project directory.
 5. Select the project containing pom.xml.
 6. Finish the import and allow Maven to resolve dependencies.
 
###3. Build the Project

Compile the main source code:

mvn clean compile


Compile the test source code as well:

mvn test-compile

###4. Run the Tests

Use the appropriate Maven profile or TestNG suite described in the next section.

##Configuration

The framework's configuration and driver management components control browser initialization and test execution settings.

Depending on the implementation, configurable properties may include:

**Setting -->  Purpose**
 - Application URL -->	Identifies the application under test
 - Browser --> Selects Chrome or Microsoft Edge
 - Headless mode --> Enables or disables headless execution
 - Test data --> Supplies test inputs
 - Wait settings --> Defines wait behavior, if configurable

Example values for illustration only:

browser=chrome
headless=false


For SauceDemo, configure the application URL as:

https://www.saucedemo.com/

##Test Execution

The project contains separate TestNG suite files intended for smoke and regression testing.

###1. Run the Smoke Test Suite

The configured suite file is:

src/test/resources/testing.xml


Run it using the testing Maven profile:

mvn clean test -Ptesting


The supplied configuration is intended to:

 - Select the smoke TestNG group.
 - Include the configured login, inventory, cart, and checkout packages.
 - Enable method-level parallel execution.
 - Set the thread count to three.
 - Register retry.RetryTransformer.

###2. Run the Regression Test Suite

The regression suite file is:

src/test/resources/testing_regression.xml


Run it using:

mvn clean test -Pregression


###3. Run Tests from Eclipse

To execute an individual test class:

 1. Right-click the test class.
 2. Select **Run As → TestNG Test.**

To execute a complete TestNG suite:

 1. Right-click testing.xml or testing_regression.xml.
 2. Select **Run As → TestNG Suite.**

Maven execution is recommended for repeatable runs outside the IDE.

##Test Reporting

###ExtentReports

The framework uses ExtentReports 5.1.2 to generate an HTML test report.

Configured report details:

**Property --> Value**

 - Report file -- reports/AutomationReport.html
 - Document title --  Selenium Automation Report
 - Report name --  E-Commerce Test Automation
 - Framework metadata --  Selenium + TestNG
 - Environment --  Test

Browser and headless metadata may also appear if configured in the report manager.

After execution, open the generated report:

reports/AutomationReport.html


The report will be available only if report generation completes successfully.

##TestNG Reports

TestNG may generate additional reports in the test-output/ directory, depending on the execution method and configuration.

Common report filenames include:

test-output/
   └── Default suite/
   └── ECommerce regression suite/
   └── ECommerce Smoke suite/
   └── Junit reports/
   └── index.html
   └── emailable-report.html

##Logging

The framework uses Log4j API and Core 2.25.2 for logging.

The supplied logging configuration is:

src/test/resources/log4j2.xml

Logs can help investigate:

 - Test execution flow.
 - Page actions.
 - Test failures.
 - Retry attempts.
 - WebDriver initialization and browser issues.

The logs/ directory is intended for log output when configured.

##Retry Mechanism

The framework includes retry-related components:

 - RetryAnalyzer.java
 - RetryListener.java
 - RetryTransformer.java

These components support retrying failed test methods according to the configured retry count and registration mechanism.

Retries can help identify intermittent failures, but they should not hide genuine application defects or unstable test logic.

##Parallel Execution

The supplied smoke suite is configured for method-level parallel execution with a thread count of three.

Thread-local WebDriver management is intended to provide a separate driver instance for each executing thread.

For reliable parallel execution, ensure that:

 - Each test thread uses its own WebDriver instance.
 - Browser sessions are properly closed after execution.
 - Shared test data is managed safely.
 - Reporting utilities handle concurrent test execution correctly.
 - Tests do not depend on execution order or shared browser state.

The actual isolation behavior depends on the driver factory and test life cycle implementation.

##Troubleshooting

###Maven Cannot Find Java

Check the Java installation and ensure that Maven uses JDK 21.

java -version
mvn -version

###Browser Fails to Start

 - Verify that the selected browser is installed.
 - Check browser and driver compatibility.
 - Review the driver initialization code and browser settings.
 - Inspect the logs for startup errors.

###Maven Profile Does Not Run the Expected Suite

 - Verify the profile name in pom.xml.
 - Check the TestNG suite file path.
 - Confirm that the suite file exists.
 - Review the Maven Surefire configuration.

###ExtentReports File Is Missing

 - Verify that the report manager is initialized.
 - Check the report output directory.
 - Confirm that the report is flushed after execution.
 - Review listener registration and error logs.

###Tests Fail Intermittently

 - Review locators and explicit waits.
 - Verify application availability and test data.
 - Inspect failure screenshots and logs.
 - Check WebDriver isolation during parallel execution.
 - Fix unstable tests rather than relying solely on retries.

###Future Enhancements

Potential improvements include:

 - Docker-based test execution.
 - API automation integration.
 - Database validation.
 - Continuous integration using GitHub Actions.
 - Expanded cross-browser execution.
 - Improved test data and environment management.

These are potential future improvements, not claims about existing functionality.

Author

**GitHub Profile:** Sultana-Raziya

**Project Repository:** https://github.com/Sultana-Raziya/automation_framew
