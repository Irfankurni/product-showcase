
# Product Showcase

## API Documentation

```
http://{{url}}:5000
```

#### Database
```
https://gist.github.com/Irfankurni/d1fc997eeb637db5544957f3e57cacdb
```

#### Product Categories

```
  GET /product-categories
```
```
  GET /product-categories/{id}
```
```
  POST /product-categories
  body: 
    {
	"category": "Slip On"
    }
```
```
  PUT /product-categories
  body: 
    {
	"id": 17
	"category": "Slip On"
    }
```
```
  DELETE /product-categories/{id}
```

#### Product

```
  GET /products
```
```
  GET /products/{id}
```
```
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
```
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
```
  DELETE /products/{id}
```

#### Product Galleries

```
  GET /galleries/{productId}
```
```
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
```
  DELETE /galleries/{id}
```

#### File

```
  GET /files/{id}
```



