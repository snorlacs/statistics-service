# statistics-service


## Starting the application

```./gradlew run```

## Endpoints   
The following are the rest endpoints:

| HTTP Verb        | URL           | Description  | Status Codes |
| ------------- |-------------|:-----| ----|
| `POST` | `http://localhost:8080/transactions` | Records a transaction | <ul><li>`201 CREATED` if transaction recorded successfully</li><li>`400 Bad Request` if request body is incorrect </li><li>`500 Internal server error` Any other runtime outage </li></ul> |
| `GET` | `http://localhost:8080/statistics` | Returns the statistics of transactions in past 60 seconds | <ul><li>`200 Ok` if statistics is found</li><li>`204 Not Found` If not found</li></ul> |

  ## Sample request Body for POST transactions
    {
       "amount": 3.44323230011,
       "timestamp": 1525033018016 
    }
  Constraints:
  1. Both the fields cannot be empty 
  2. timestamp less than past 60 seconds are not acceptable     
   
## Some more Gradle commands
    
    To build the application
    `./gradlew build`     
    
    To run all tests
    `./gradlew test`
