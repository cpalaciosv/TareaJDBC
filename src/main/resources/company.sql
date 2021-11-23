DROP DATABASE IF EXISTS Company;

CREATE DATABASE IF NOT EXISTS Company;
    
USE Company;

CREATE TABLE IF NOT EXISTS Department (
	name VARCHAR (20) PRIMARY KEY,
	description VARCHAR (100) NOT NULL
);


CREATE TABLE IF NOT EXISTS Employee (
	nif VARCHAR(10) PRIMARY KEY,
	name VARCHAR (40) NOT NULL,
	surname VARCHAR (40) NOT NULL,
	department_name VARCHAR (20) NOT NULL,

	CONSTRAINT FK_Employee_Department
	FOREIGN KEY (department_name)
	REFERENCES Department (name)
	ON UPDATE CASCADE
     ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Role (
	nif VARCHAR(10) NOT NULL,
	role VARCHAR (100) NOT NULL,
	PRIMARY KEY(nif, role),
	CONSTRAINT FK_Role_Employee
    	FOREIGN KEY (nif)
    	REFERENCES Employee (nif)
    	ON UPDATE CASCADE
         ON DELETE CASCADE

);

INSERT INTO Department(name, description) VALUES('Marketing', 'Labores de publicidad y gesti�n de clientes');

INSERT INTO Employee(nif, name, surname, role, department_name) VALUES('00000000X','Pepa', 'Pig', 'Jefe de departamento','Marketing');

INSERT INTO Employee(nif, name, surname, role, department_name) VALUES('00000001X','George', 'Pig', 'Publicista','Marketing');

