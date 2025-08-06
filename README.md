## API-AUTOMATION
This is api automation framework based on rest-assured </br>
https://rest-assured.io

## Tools & Libraries
The test automation framework is comprised of following tools and libraries: <br />
* Codebase : Java
* Build tool : Maven
* Core lib : REST-assured
* Test framework : TestNG
* Reporting : Extent v5

## How to add a Test Case for a New Service
### Writing test cases
Write you TestNG test cases in java file (preferably in package com.delhivery.tests.modules) with the `@Test` annotation.
```bash
private TestDataFactory testdf = TestDataFactory.getTestDataFactory();
@Test(groups = {"group_name"})
  public void testFunction() throws Exception {
  /* Example of a Test Case:
   * TestUtils.exAndValidateAPICall(testdf.getTestData(id));
   * where id = id of the corresponding test case in postgres table
   */
  }
```
Add this Main Class into pom.xml file.

### TestNG '.xml' suites file
`/suites/file_name.xml` <br />
Configure the suite file (`file_name.xml` in this case) of TestNG by specifying the groups and classes in the suite tag. This will invoke the testing according to the details in file_name.xml file.

### Author
* Akshat Jain
