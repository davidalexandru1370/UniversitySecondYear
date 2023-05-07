create database documents;

use documents;

create table document(
    id int not null primary key AUTO_INCREMENT,
    author varchar(100) not null,
    title varchar(100) not null,
    number_of_pages int not null,
    type varchar(100) not null,
    format varchar(100) not null
);