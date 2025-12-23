# Agency Accelerator Test Automation Framework

This is a comprehensive, thread-safe test automation solution designed for the **Agency Accelerator** project. It leverages modern technologies to provide robust testing capabilities for both UI and RESTful APIs.

## 🚀 Key Features

- **Multi-Level Testing**: Support for both UI (Playwright) and API (REST) testing in a single framework.
- **Parallel Execution**: Native support for parallel test execution using JUnit 5 Platform and Cucumber.
- **Thread Safety**: Robust ThreadLocal management ensuring isolated browser instances and test contexts across parallel threads.
- **Page Object Model (POM)**: Organized and maintainable UI automation structure.
- **Facade Design Pattern**: Simplified entry points for complex subsystems.
- **Data-Driven Testing**: Externalized configuration and test data using YAML and Properties files.
- **Comprehensive Reporting**: Integrated with both **Allure** and **Extent Reports** for detailed visual insights.
- **CI/CD Ready**: Configured for seamless integration with GitHub Actions or CircleCI.

## 🛠 Tech Stack

- **Core**: Java 21
- **Automation**: Playwright (Java)
- **BDD Framework**: Cucumber 7
- **Test Runner**: JUnit 5 (JUnit Platform Suite)
- **Dependency Management**: Maven
- **Logging**: Log4j2
- **Assertions**: AssertJ & Playwright Assertions
- **Dependency Injection**: PicoContainer
- **Reporting**: Allure, Extent Reports (Spark & Adapter)

## 📂 Project Structure

```text
├── src
│   ├── main/java/com/carehires
│   │   ├── config          # Configuration management (Environment, Browser)
│   │   ├── constants       # Framework and Application constants
│   │   ├── exceptions      # Custom framework exceptions
│   │   ├── factories       # Factory classes for Browsers, API Clients, etc.
│   │   ├── helpers         # Utility helpers (Wait, Screenshot, Data)
│   │   ├── managers        # ThreadLocal and Driver lifecycle management
│   │   ├── models          # Data models and API POJOs
│   │   ├── pages           # Page Object classes (POM)
│   │   └── utils           # Common utilities (File, Reporting)
│   └── test
│       ├── java/com/carehires
│       │   ├── context     # BDD Test Context for sharing state
│       │   ├── hooks       # Cucumber Before/After hooks
│       │   ├── runners     # Generic and specific test runners
│       │   └── steps       # Step Definitions (UI and API)
│       └── resources
│           ├── features    # Cucumber feature files
│           └── config      # YAML/Properties config files
├── pom.xml                 # Maven configuration
└── README.md               # Framework documentation
```

## ⚙️ Configuration

The framework can be configured via `src/test/resources/config/env-{env}.properties` and Maven properties.

### Test Execution Properties
- `env`: Specifies the environment (`dev`, `staging`, `prod`)
- `browser`: Targeted browser (`chromium`, `firefox`, `webkit`)
- `headless`: Toggle headless execution (`true`/`false`)
- `parallel.count`: Number of parallel threads

## 🏃 Running Tests

### Command Line
You can run tests using standard Maven commands:

```bash
# Run all tests in parallel
mvn clean test

# Run a specific runner (e.g., UI only)
mvn test -Dtest=UiTestRunner

# Run with a specific environment and browser
mvn test -Denv=staging -Dbrowser=firefox
```

### IDE
Right-click on any class in `src/test/java/com/carehires/runners` and select **Run**.

## 📊 Reporting

### Allure Reports
1. Run tests: `mvn test`
2. Generate report: `mvn allure:report`
3. Open report: `mvn allure:serve`

### Extent Reports
Reports are automatically generated after execution in `target/extent-reports/`.

## 🛡️ Thread Safety & Parallelism

The framework uses `ThreadLocal` for:
1. `Playwright` instances
2. `Browser` & `BrowserContext`
3. `Page` instances
4. `APIRequestContext`
5. `TestContext` (Scenario-level data passing)

This ensures that even when running multiple scenarios in parallel, there is no state leakage between threads.
