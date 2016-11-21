Create Database empmgmt;

CREATE TABLE Employee (
    id INT NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    email VARCHAR(255),
    age INT,
    salary DOUBLE,
    fulltime BOOLEAN,
    joindate DATE,
    department INT,
    PRIMARY KEY (id)
);


CREATE TABLE Department (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
    );
    
    
    
ALTER TABLE Employee 
ADD FOREIGN KEY (department)
        REFERENCES Department (id);

