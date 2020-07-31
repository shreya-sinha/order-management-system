# order-management-system
SpringBoot Microservices demo using Feign Client

A simple order management system. Consists of Order Service is responsible for creating new orders, retrieving existing order info. 
Similarly, Item Service is responsible for creating order items, retrieving order items.

## Storage
Each service uses h2 database. Item service stores:
```
Item:
  productCode
  productName
  quantity
```
Order service stores:
```
OrderEntity:
  orderId
  customerName
  orderDate
  shippingAddress
  orderItems
  total
```
```
OrderItems: (Maps items in an order)
  id
  orderId
  productCode
```

## Features
  
For creation of order, order service queries item service for items in catalog. If an item is not in catalog order cannot be placed.
Order service interacts with Item service using Feign Client.

Fields for creation of order are validated.

## Usage
### Create Item Request
```
curl -L -X POST 'localhost:9091/item' \
-H 'Content-Type: application/json' \
--data-raw '{
	
	"productName":"MacBook",
	"quantity":1
}'
```
### Get Item Request
```
curl -L -X GET 'localhost:9091/item' 
```
Response to Get All Items
```
[
    {
        "productCode": 1,
        "productName": "iPad",
        "quantity": 3
    },
    {
        "productCode": 2,
        "productName": "iPhone",
        "quantity": 7
    },
    {
        "productCode": 3,
        "productName": "MacBook",
        "quantity": 1
    }
]
```
### Create Order Request
```
curl -L -X POST 'localhost:8080/order' \
-H 'Content-Type: application/json' \
--data-raw '{
	"customerName":"Bob",
	"shippingAddress":"New York",
	"orderItemIds":[1,2,3],
	"totalCost":"$ 100000"
}'
```
Create Order Response
```
{
    "id": 1,
    "customerName": "Bob",
    "orderDate": "2020-07-31T13:46:26.815+00:00",
    "shippingAddress": "New York",
    "totalCost": "$ 4500",
    "orderItems": [
        {
            "productCode": 1,
            "productName": "iPad",
            "quantity": 3
        },
        {
            "productCode": 2,
            "productName": "iPhone",
            "quantity": 7
        },
        {
            "productCode": 3,
            "productName": "MacBook",
            "quantity": 1
        }
    ]
}
```
