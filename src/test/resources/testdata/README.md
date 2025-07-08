# Sample Test Data for ADL Automation

This directory contains Excel files with test data for various test scenarios.

## Files

- `contract_test_data.xlsx` - Test data for contract creation and management tests
- `login_test_data.xlsx` - Test data for login scenarios  
- `user_management_test_data.xlsx` - Test data for user management tests

## Excel File Structure

Each Excel file follows this structure:
- First row contains column headers
- Subsequent rows contain test data
- Each row represents one test scenario

## Usage

Test classes use DataProvider classes to read this data and execute data-driven tests.

Example:
```java
@Test(dataProvider = "contractTestData", dataProviderClass = ContractDataProvider.class)
public void testContractCreation(Map<String, String> testData) {
    // Test implementation using testData
}
```