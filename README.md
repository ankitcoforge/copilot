# ADL Playwright Automation Framework

This is a Playwright-based automation framework for testing the ADL P&C Insurance application, migrated from Selenium WebDriver.

## Features

- **Playwright Integration**: Modern browser automation with Playwright for Java
- **TestNG Framework**: Robust testing framework with parallel execution support
- **Page Object Model**: Clean separation of page elements and actions
- **Cross-browser Testing**: Support for Chromium, Firefox, and WebKit
- **Parallel Execution**: Run multiple tests concurrently
- **Screenshot Capture**: Automatic screenshots on test failures
- **Video Recording**: Optional video recording of test execution
- **Excel Data Provider**: Support for data-driven testing using Excel files
- **Email Testing**: Integration with Mailosaur for email verification
- **PDF Validation**: Utilities for PDF content verification
- **Comprehensive Reporting**: Detailed test execution reports

## Project Structure

```
src/
├── main/java/
│   ├── pageObjects/     # Page object classes with element locators
│   ├── pageActions/     # Page action classes with business logic
│   └── utils/           # Utility classes and framework helpers
├── test/java/
│   └── testsuite/       # Test classes
└── test/resources/      # Test configuration and data files
```

## Setup Instructions

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Chrome, Firefox, or Edge browser

### Installation

1. Clone the repository
2. Install dependencies:
   ```bash
   mvn clean install
   ```

3. Install Playwright browsers:
   ```bash
   mvn exec:java -Dexec.mainClass="com.microsoft.playwright.CLI" -Dexec.args="install"
   ```

### Configuration

Update `src/main/resources/config.properties` with your environment settings:

- Application URL
- Browser preferences
- Test credentials
- Email service configuration

## Running Tests

### Run all tests:
```bash
mvn test
```

### Run tests in parallel:
```bash
mvn test -Pparallel
```

### Run specific test suite:
```bash
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml
```

### Run with specific browser:
```bash
mvn test -Dbrowser=firefox
```

## Key Components

### BaseClass
- Manages Playwright lifecycle
- Handles browser and context creation
- Provides common utility methods

### PlaywrightUtils
- Element interaction methods
- Wait strategies
- Common page operations

### TestListener
- Captures screenshots on failures
- Provides test execution logging
- Generates test reports

### Page Objects
- Element locators for each page
- Converted from Selenium to Playwright locators

### Page Actions
- Business logic implementation
- Page-specific operations
- User workflow methods

## Test Data Management

- Excel-based test data using Apache POI
- Data provider utilities for parameterized testing
- Support for multiple test data sheets

## Parallel Execution

The framework supports parallel execution at multiple levels:
- Method level: Tests within a class run in parallel
- Class level: Different test classes run in parallel
- Configurable thread count for optimal performance

## Reporting

- TestNG HTML reports
- Screenshot capture on failures
- Video recordings (when enabled)
- Playwright traces for debugging

## Migration from Selenium

This framework has been migrated from Selenium WebDriver to Playwright with the following improvements:

1. **Better Performance**: Faster test execution
2. **Auto-waiting**: Built-in smart waiting strategies
3. **Better Debugging**: Enhanced debugging capabilities with traces
4. **Cross-browser Support**: Consistent behavior across browsers
5. **Modern Architecture**: Support for modern web applications

## Browser Support

- **Chromium**: Default browser (includes Chrome, Edge)
- **Firefox**: Mozilla Firefox
- **WebKit**: Safari engine

## Contributing

1. Follow the existing code structure
2. Add appropriate tests for new features
3. Update documentation as needed
4. Ensure all tests pass before submitting

## Troubleshooting

### Common Issues

1. **Browser not found**: Run `mvn exec:java -Dexec.mainClass="com.microsoft.playwright.CLI" -Dexec.args="install"`
2. **Test timeouts**: Increase timeout values in configuration
3. **Element not found**: Verify locators are correct for Playwright syntax

### Debugging

- Enable video recording in configuration
- Use Playwright traces for detailed debugging
- Check screenshot captures in `test-results/screenshots/`

## Contact

For questions or support, please contact the development team.