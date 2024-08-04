Based on the structure of your Maven project, here's a `README.md` file that provides instructions on how to run the tests included in the `Automation Testing Task API` project.

```markdown
# Automation Testing Task API

This project contains a series of API tests to validate different aspects of an application. The tests are:

- `Case1Test`
- `Case2Test`
- `SchemaValidationTest`

```
```sh
## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven 3.6.0 or higher

```

## Use Cases
```sh
### Use Case 1

1. Search for a person with the name Vader.
2. Using the previous response, find which film that Darth Vader joined has the least planets and validate the response.
3. Using the previous responses, verify if Vader's starship is in the film with the least planets.
4. Find and verify the oldest person ever played in all Star Wars films with less than 10 requests.

```
```sh
### Use Case 2

1. Create contract test (JSON schema validation) for the `/people` API.

```
## Running Tests

To run the tests, use the following Maven commands.

### Running Case1Test

```sh
mvn test -Dtest=Runner.Case1Test

```

### Running Case2Test

```sh
mvn test -Dtest=Case2Test
```

### Running SchemaValidationTest

```sh
mvn test -Dtest=SchemaValidationTest
```

## Notes

- Ensure that any required resources or configurations are properly set up in the `src/test/resources` directory.
- Adjust the test logic as needed for your specific testing requirements.

## Additional Information

For more details on how to use Maven and JUnit for testing, refer to the following resources:
- [Maven Documentation](https://maven.apache.org/guides/index.html)
- [JUnit 4 Documentation](https://junit.org/junit4/)