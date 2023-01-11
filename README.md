
# Product Showcase

## API Documentation


http://{{url}}:5000

#### Product Categories

```http
  GET /product-categories
```
```http
  GET /product-categories/{id}
```
```http
  POST /product-categories
  body: 
    {
	    "category": "Slip On"
	}
```
```http
  PUT /product-categories
  body: 
    {
	    "id": 17
	    "category": "Slip On"
	}
```
```http
  DELETE /product-categories/{id}
```

#### Product

```http
  GET /products
```
```http
  GET /products/{id}
```
```http
  POST /products
  body: 
    {
    	"productName": "Adidas Ultra 4D 5",
	    "categoryId": "1",
	    "price": "68",
    	"description": "Adidas Ultra 4D 5",
    	"tags": "running"
	}
```
```http
  PUT /products
  body: 
    {
        "id": 1
    	"productName": "Adidas Ultra 4D 5",
	    "categoryId": "1",
	    "price": "68",
    	"description": "Adidas Ultra 4D 5",
    	"tags": "running"
	}
```
```http
  DELETE /products/{id}
```

#### Product Galleries

```http
  GET /galleries/{productId}
```
```http
  POST /galleries
  body: 
    {
		"productId": 2,
		"galleries": [
			{
				fileName: "", <-- base64 of file
				fileExtension: "",
			}
		]
	}
```
```http
  DELETE /galleries/{id}
```

#### File

```http
  GET /files/{id}
```



