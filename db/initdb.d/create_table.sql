create table user(
                     id INT NOT NULL AUTO_INCREMENT,
                     userId varchar(25),
                     userName varchar(25),
                     password varchar(25),
                     address varchar(25),
                     addressDetail varchar(25),
                     createdAt date,
                     primary key (id)
);