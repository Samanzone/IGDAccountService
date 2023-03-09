# IGreenData Account Service
This Service exposed two RESTful API.Which is build using Java 17

## Build
Build the jar and Testing.
```
igd-account-service/docker-db $  docker-compose up -d   
igd-account-service/docker-db $  cd ..
igd-account-service $ ./gradlew clean  
igd-account-service $ ./gradlew test
igd-account-service $ ./gradlew build
igd-account-service $ cd docker-db
igd-account-service/docker-db $  docker-compose down
```
## Test Coverage and Test Report
```
Test Report --> build/reports/tests/test/index.html 

Test Coverage --> build/jacocoHtml/index.html

```

https://github.com/Samanzone/igd-account-service/blob/main/img/test-coverage.png

https://github.com/Samanzone/igd-account-service/blob/main/img/test-case-summary.png

## Run
There is a Docker environment included with this project to run the API with a Postgres DB initialized. Use the below commands to bring up the Docker environment.
```
igd-account-service $  docker build -t saman/igd-account-service .   
igd-account-service/docker-app $ docker-compose up -d
```

## Swagger 
```
http://localhost:8081/swagger-ui/index.html

Account API 

User Id = TOM123
sort By accountNumber

Transaction API
Account Num =232323232
sort By valueDate
```
## Use
Use the following cURL command to call the  API.
```
curl -X 'GET' \
'http://localhost:8081/api/v1/user-id/TOM123/accounts?page=1&size=1&sort=accountNumber' \
-H 'accept: application/json'

curl -X 'GET' \
'http://localhost:8081/api/v1/accounts/account-num/232323232/transhistory?page=0&size=14&sort=valueDate' \
-H 'accept: application/json'
```
 
