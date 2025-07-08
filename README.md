# ADL E2E Automation with Playwright

Modern end-to-end test automation framework for the Protective ADL Portal, modernized from Selenium to Playwright.

## Quick Start

1. **Prerequisites**
   - Java 11 or higher
   - Maven 3.6 or higher

2. **Install Dependencies**
   ```bash
   mvn clean install
   ```

3. **Install Playwright Browsers**
   ```bash
   mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
   ```

4. **Run Tests**
   ```bash
   # Run all tests in development environment
   mvn test
   
   # Run tests in production environment
   mvn test -Denv=prod
   
   # Run specific test class
   mvn test -Dtest=LoginTests
   ```

## Features

- **Playwright-powered**: Modern web automation with auto-wait and retry
- **Cross-browser**: Chromium, Firefox, and WebKit support
- **Built-in debugging**: Trace viewer, video recording, screenshots
- **Data-driven**: Excel-based test data management
- **Environment-specific**: Dev/Prod configuration management
- **Business-focused**: Covers all ADL Portal workflows

## Documentation

See [ProjectOverview.md](ProjectOverview.md) for detailed business requirements and technical documentation.

## Project Structure

- `src/main/java/` - Framework core classes and page objects
- `src/test/java/` - Test classes
- `src/test/resources/` - Test configuration and data
- `target/` - Build outputs, reports, and test artifacts

## Modernization Benefits

✅ **Replaced Selenium WebDriver with Playwright**  
✅ **Eliminated explicit waits with auto-wait**  
✅ **Added built-in video/screenshot recording**  
✅ **Improved reliability and reduced flakiness**  
✅ **Multiple browser engine support**  
✅ **Modern web features support**  

For complete business requirements and technical details, see [ProjectOverview.md](ProjectOverview.md).