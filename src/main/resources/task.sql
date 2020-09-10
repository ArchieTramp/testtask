CREATE TABLE IF NOT EXISTS dailyTask
(
    id UUID NOT NULL ,
    email varchar(320) not null,
    task varchar(1024) NOT NULL ,
    hours int not null  ,
    dateoftask TIMESTAMP(6) not null,
    PRIMARY KEY (id)
)
