# camunda-rnd-repo
RnD repo

## Running the application locally

```java
http://localhost:8080/order/2
```
Execute the below GET from browser/postman.This will gracefully execute and below is the response which is correct

```json
{
    "orderId": 2,
    "message": "SUCCESS"
}
```

Pass negative Orderid

```java
http://localhost:8080/order/-1 
```
Execute the above GET from browser/postman.This should execute till only Step1Delegate and should not go to Step2.Expected response is below

```json
{
    "orderId": -1,
    "message": "FAILED DUE TO INVALID ORDER ID "
}
```

<img src="https://i.postimg.cc/fyXPXbds/tody.jpg" style=" width:1000px ; height:1000px " />

