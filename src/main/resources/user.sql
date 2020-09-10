CREATE TABLE IF NOT EXISTS createUser
(
    id  varchar(36) NOT NULL,
    lastName varchar(60) NOT NULL,
    firstName varchar(60) NOT NULL,
    fathName varchar(60) NOT NULL,
    email varchar(320) not null,
    tasks varchar(1024) not null ,
    PRIMARY KEY (id)

)