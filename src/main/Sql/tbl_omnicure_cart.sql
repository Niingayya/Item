create database Ecommercedb;

create table shopping_cart (
	shopping_cart_id int(11) NOT NULL AUTO_INCREMENT,
	account_key varchar(35) NOT NULL,
	created_time datetime NOT NULL DEFAULT NOW(),
	updated_time datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (shopping_cart_id)
)ENGINE=innodb DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
