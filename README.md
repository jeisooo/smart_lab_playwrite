# Test framework for smart-lab.ru

## Overview
My Automation Framework is a Java-based framework designed for automating web applications using Playwright and JUnit5. It provides a set of reusable modules and utilities to make writing and executing automated tests easier. Also, I tryed to use PageObject template.

## Installation
1. Clone the repository: `git clone https://github.com/jeisooo/smart_lab_playwrite.git`
2. Install dependencies: `mvn clean install`

## Configuration
The framework can be configured using the `config.json` file located in the `src/test/config` directory. You can specify the browser, URL, and other settings in this file.

## Usage
1. Create a new Playwright test class and extend the `BasePage` class.
2. Write your test cases using the provided utility methods and annotations.
3. Run the tests using Maven or your favorite IDE.

## Allure reports
1. You can use allure reports system here. Annotate your test by JUnit descriptions. 
2. Run **allure serve allure-results** in terminal inside working directory


## Test Examples
```java
@Test
@Story("Main page: authorize")
@Description("Authorize with firefox")
   // @ParameterizedTest
    ///@EnumSource(arr)
    void testAuthorization(){
        firstPage.authorize();
    }