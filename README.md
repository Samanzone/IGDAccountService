# IGreenData Account Service
This Service exposed two RESTful API.

## Build
Build the jar and docker image.
```
igd-account-service $ ./gradleW clean build  
igd-account-service $  docker build -t saman/igd-account-service .   
```

## Run
There is a Docker environment included with this project to run the API with a Postgres DB initialized. Use the below commands to bring up the Docker environment.
```
igd-account-service/docker-env $ docker-compose run start_db
igd-account-service/docker-env $ docker-compose up -d
```


## Use
Use the following cURL command to call the  API.
```
Spring-WebFlux-Example $ curl localhost:8090/api/animal -i
HTTP/1.1 200 OK
transfer-encoding: chunked
Content-Type: application/stream+json

{"id":"61de7d7e-d5d5-4772-9c56-0115381e8205","name":"Cat","kingdom":"Vertebrate"}
{"id":"61a55942-aa86-402c-a3be-d4ccd18ae2ff","name":"Jellyfish","kingdom":"Invertebrate"}
{"id":"c6aa1de4-81fd-4336-a69a-0bf9400e633f","name":"Cat","kingdom":"Vertebrate"}
{"id":"2c643272-83c8-4a7b-a65f-413f7bb27b1b","name":"Jellyfish","kingdom":"Invertebrate"}
{"id":"4beb08f6-2583-41d5-a394-3d87276df7eb","name":"Jellyfish","kingdom":"Invertebrate"}
{"id":"3ea559d8-3c1b-4c8c-81a4-52cd04152116","name":"Cat","kingdom":"Vertebrate"}
{"id":"34d913cf-124e-4ceb-ab39-0fa2a303c782","name":"Worm","kingdom":"Invertebrate"}
{"id":"aefb3f7e-f2da-493b-a303-e1e42840623a","name":"Jellyfish","kingdom":"Invertebrate"}
{"id":"b66d2000-60fc-4a80-93c5-5b669596f36f","name":"Cat","kingdom":"Vertebrate"}
{"id":"02c327a4-6f14-49bb-b850-9a2cd3a8586d","name":"Cat","kingdom":"Vertebrate"}
```
 