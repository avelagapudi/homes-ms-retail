CREATE TABLE stores(
	store_id BIGINT NOT NULL AUTO_INCREMENT,
	store_name VARCHAR(50) NOT NULL,
	is_deleted TINYINT(1) NOT NULL DEFAULT 0,
	created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(store_id)
);

CREATE TABLE products(
	product_id BIGINT NOT NULL AUTO_INCREMENT,
	store_id BIGINT NOT NULL,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(100),
	sku VARCHAR(10) NOT NULL,
	price DECIMAL(10,2) NOT NULL DEFAULT 0,
	is_deleted TINYINT(1) NOT NULL DEFAULT 0,
	created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(product_id)
);

CREATE TABLE stock(
    stock_id BIGINT NOT NULL AUTO_INCREMENT,
	product_id BIGINT NOT NULL,
	store_id BIGINT NOT NULL,
	count INT(11) NOT NULL DEFAULT 0,
	created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(stock_id)
);

CREATE TABLE orders(
	order_id BIGINT NOT NULL AUTO_INCREMENT,
	order_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	store_id BIGINT NOT NULL,
	status INT NOT NULL DEFAULT 1,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	email VARCHAR(100) NOT NULL,
	phone VARCHAR(10),
	created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(order_id)
);

CREATE TABLE product_order(
    product_order_id BIGINT NOT NULL AUTO_INCREMENT,
	order_id BIGINT NOT NULL,
	product_id BIGINT NOT NULL,
	count INT NOT NULL,
	created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(product_order_id)
);

INSERT INTO stores (store_id, store_name) VALUES
(1, "test store one"),
(2,"test store two");

INSERT INTO products(product_id, store_id, name, description, sku, price) VALUES
(1, 1, "test product one","description of product one","LK1234",2500.00),
(2,2,"test product two", "description of product two","MH4536",1500.00);

INSERT INTO stock(stock_id,product_id,store_id,count) VALUES
(1,1,1,20),
(2,2,2,15);
