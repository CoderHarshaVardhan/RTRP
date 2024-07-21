
DROP database bookstore;

SHOW databases;

CREATE DATABASE bookstore;

SHOW databases;

USE bookstore;

SELECT database();

CREATE TABLE books(
	ISBN varchar(17),
	Category varchar(20),
	Title varchar(35),
	Author varchar(35),
	Year int,
	Price float
);

SHOW tables;

DESCRIBE books;

INSERT INTO books VALUES('0-06-250217-4', 'Novel', 'The Alchemist', 'Paulo Cohelo', 1988, 180);
INSERT INTO books VALUES('978-0735211292', 'Self Help', 'Atomic Habits', 'James Clear', 2018, 459);
INSERT INTO books VALUES('9780141439976', 'Science Fiction', 'The Time Machine', 'H.G Wells', 1895, 354);

SELECT * FROM books;

SHOW tables;
