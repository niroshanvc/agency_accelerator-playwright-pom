# Agency Accelerator Automation Framework

## Tech Stack
- **Language**: Java 21
- **Driver**: Playwright
- **BDD**: Cucumber 7
- **Assertions**: AssertJ & Playwright Assertions
- **Reporting**: Allure & Extent Reports
- **Build**: Maven

## Folder Structure
Follows standard POM with `src/main/java` for core framework and `src/test/java` for test definitions.

## How to Run
### CLI
```bash
mvn test
```

### Run Specific Tag
```bash
mvn test -Dcucumber.filter.tags="@API"
```

### Environment Config
Default is `DEV`. To switch:
```bash
mvn test -Denv=STAGING
```

## Reports
Allure results are generated in `target/allure-results`.
Run `allure serve target/allure-results` to view.

## Parallel Execution
Configured via JUnit Platform in `junit-platform.properties` (to be added) or Surefire config.
