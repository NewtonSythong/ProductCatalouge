-- DROP TABLE IF EXISTS product;
-- DROP TABLE IF EXISTS customer;

CREATE TABLE IF NOT EXISTS PRODUCT (
    productId varchar(20) unique,
    name varchar(50) not null,
    description varchar(100) not null,
    category varchar(50) not null,
    listPrice numeric(6, 2) not null,
    quantityInStock integer not null,
    constraint Product_PK primary key (productId)
);

CREATE TABLE IF NOT EXISTS CUSTOMER (
    customerId integer AUTO_INCREMENT unique,
    username varchar(50) not null unique,
    firstName varchar(50) not null,
    surname varchar(50) not null,
    emailAddress varchar(100) not null,
    shippingAddress varchar(100) not null,
    password varchar(50) not null,
    constraint Customer_PK primary key (customerId)
);

CREATE TABLE IF NOT EXISTS SALE (
    saleId integer not null AUTO_INCREMENT(1000),
    date timestamp not null,
    status varchar(50) not null,
    customerId integer not null,

    constraint Sale_PK primary key (saleId),
    constraint Customer_FK foreign key (customerId) references CUSTOMER
);

CREATE TABLE IF NOT EXISTS SALE_ITEM (
    quantityPurchased integer not null,
    salePrice Decimal(6, 2) not null,
    saleId integer not null,
    productId varchar(20) not null,

    constraint Sale_Item_PK primary key (saleId, productId),
    constraint Sale_FK foreign key (saleId) references SALE,
    constraint Product_FK foreign key (productId) references PRODUCT
);