# phone-booking

### prerequisite

* Maven 3.8
* Java 11

### run the application using `mvn spring-boot:run` from the root directory

### access the application on http://localhost:8080/swagger-ui/index.html

### API

* Get all phones

  > curl -X GET "http://localhost:8080/phones" -H  "accept: */*"

   ```json
          [
    {
      "name": "Samsung Galaxy S9",
      "isAvailable": true,
      "foneInfo": {
        "technology": "NA",
        "bands2g": "NA",
        "bands3g": "NA",
        "bands4g": "NA"
      }
    },
    {
      "name": "Samsung Galaxy S8",
      "isAvailable": true,
      "foneInfo": {
        "technology": "NA",
        "bands2g": "NA",
        "bands3g": "NA",
        "bands4g": "NA"
      }
    },
    {
      "name": "Samsung Galaxy S7",
      "isAvailable": true,
      "foneInfo": {
        "technology": "NA",
        "bands2g": "NA",
        "bands3g": "NA",
        "bands4g": "NA"
      }
    },
    {
      "name": "Motorola Nexus 6",
      "isAvailable": true,
      "foneInfo": {
        "technology": "NA",
        "bands2g": "NA",
        "bands3g": "NA",
        "bands4g": "NA"
      }
    },
    {
      "name": "LG Nexus 5X",
      "isAvailable": true,
      "foneInfo": {
        "technology": "NA",
        "bands2g": "NA",
        "bands3g": "NA",
        "bands4g": "NA"
      }
    },
    {
      "name": "Huawei Honor 7X",
      "isAvailable": true,
      "foneInfo": {
        "technology": "NA",
        "bands2g": "NA",
        "bands3g": "NA",
        "bands4g": "NA"
      }
    },
    {
      "name": "Apple iPhone X",
      "isAvailable": true,
      "foneInfo": {
        "technology": "NA",
        "bands2g": "NA",
        "bands3g": "NA",
        "bands4g": "NA"
      }
    },
    {
      "name": "Apple iPhone 8",
      "isAvailable": true,
      "foneInfo": {
        "technology": "NA",
        "bands2g": "NA",
        "bands3g": "NA",
        "bands4g": "NA"
      }
    },
    {
      "name": "Apple iPhone 4s",
      "isAvailable": true,
      "foneInfo": {
        "technology": "NA",
        "bands2g": "NA",
        "bands3g": "NA",
        "bands4g": "NA"
      }
    },
    {
      "name": "Nokia 3310",
      "isAvailable": true,
      "foneInfo": {
        "technology": "NA",
        "bands2g": "NA",
        "bands3g": "NA",
        "bands4g": "NA"
      }
    }]
  ```
  
* Book the phone
  > curl -X PUT "http://localhost:8080/phones/1/bookThePhone" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"name\":\"John\"}"

    ```json
            {
        "name": "Samsung Galaxy S9",
        "isAvailable": false,
        "bookedBy": "John",
        "bookedOn": "2021-06-12T18:10:34.771+00:00",
        "foneInfo": {
          "technology": "NA",
          "bands2g": "NA",
          "bands3g": "NA",
          "bands4g": "NA"
        }
      }
  ```
  
  * Return the phone
   > curl -X PUT "http://localhost:8080/phones/1/returnThePhone" -H  "accept: */*"
   
  ```json
          {
    "name": "Samsung Galaxy S9",
    "isAvailable": true,
    "foneInfo": {
      "technology": "NA",
      "bands2g": "NA",
      "bands3g": "NA",
      "bands4g": "NA"
    }
  }
  ```
