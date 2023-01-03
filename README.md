# Flightright Group challenge #1

Write a Java application, that counts users visited our web-page from different sources.  
Mostly filled with duplicates. 
A unique user is identified via unique phone and email combination.

As input you have a csv file, that contains:

```
email,phone,source
test@test.com,123,google.com
test@test.com,,google.com
test1@test.com,321,google.com
```


### The rules:

- Ignore csv entries with any nullable field
- Just print the results to any output
- The application is not a single-use script, so should be designed to be supportable
- The filename should be passed via a REST Endpoint, but should not be hardcoded.


### The bonus:

- solve the same problem in another language like javascript (node), python or go
- create a frontend that displays the result
