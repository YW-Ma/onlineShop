# An Online Shop Website

You can register/login and add product in cart.
In cart, you can check out.

## System Design
![](https://github.com/YW-Ma/onlineShop/blob/main/System.png)

## Database Design
![](https://github.com/YW-Ma/onlineShop/blob/main/DB.png)

## API
| URL                                    | Request method | JSP             | Method to handle | purpose                                                |
| -------------------------------------- | -------------- | --------------- | ---------------- | ------------------------------------------------------ |
| /get**AllProducts**                    | GET            | navbar.jsp      | getAllProducts() | Get all products from DB                               |
| /get**AllProducts**                    | GET            | productList.jsp | getProductById() | Get specific product from DB based on primary key      |
| /admin/**product**/addProduct          | GET            | narbar.jsp      | getProductForm() | Get **a form to let admin add a product** to system    |
| /admin/**product**/addProduct          | POST           | addProduct.jsp  | addProduct()     | **Save a product to DB**                               |
| /admin/**delete/{productId}**          | DELETE         | productList.jsp | deleteProduct()  | Delete a product                                       |
| /admin/product/editProduct/{productId} | GET            | productList.jsp | getEditForm()    | **Get a form to let admin update an existing product** |
| /admin/product/editProduct/{productId} | POST           | editProduct.jsp | editProduct()    | **Save updated product to DB**                         |
