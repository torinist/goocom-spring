DROP TABLE circle;
CREATE TABLE IF NOT EXISTS circle (
	id INT PRIMARY KEY auto_increment,
	name VARCHAR(100) NOT NULL,
	writer VARCHAR(100),
	sns_url VARCHAR(200),
	updated_time TIMESTAMP NOT NULL DEFAULT current_timestamp,
	created_time TIMESTAMP NOT NULL DEFAULT current_timestamp,
	version INT NOT NULL DEFAULT 0
);

DROP TABLE genre;
CREATE TABLE IF NOT EXISTS genre (
	id INT PRIMARY KEY auto_increment,
	name VARCHAR(50) NOT NULL,
	updated_time TIMESTAMP NOT NULL DEFAULT current_timestamp,
	created_time TIMESTAMP NOT NULL DEFAULT current_timestamp,
	version INT NOT NULL DEFAULT 0
);

DROP TABLE book;
CREATE TABLE IF NOT EXISTS book (
	id INT PRIMARY KEY auto_increment,
	name VARCHAR(100) NOT NULL,
	file_url VARCHAR(200),
	circle_id INT,
	issue_year INT,
	genre_id INT,
	updated_time TIMESTAMP NOT NULL DEFAULT current_timestamp,
	created_time TIMESTAMP NOT NULL DEFAULT current_timestamp,
	version INT NOT NULL DEFAULT 0,
	FOREIGN KEY(circle_id) REFERENCES circle(id),
	FOREIGN KEY(genre_id) REFERENCES genre(id)
);