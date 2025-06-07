<h1>Spring Boot Product RESTful API</h1>

Features 
* CRUD(Create, Read, Update, Delete)
* RESTful API design principles
* Clear Layered Architecture(Controller,Service,Repository)
* Using H2 Database
* Using Lombok
* Using Spring Date JPA for ORM
* Use Optional and ResponseEntity for HTTP status code 
* Dependency Injection

API Endpoints
* GET/product : Retrieve all products
* GET/product/{id}: Retrieve a product by id
* POST/product: Create a new product
* * Headers: Content-Type: application/json
* * Body: { "name": "Name","description": "Desc","price": 10.00}
* PUT /product/{id}: Update a product by id
* * Headers: Content-Type: application/json
* * Body: { "name": "Name updated","description": "Desc updated","price": 10.00}
* DELETE /product/{id}: Delete a product id