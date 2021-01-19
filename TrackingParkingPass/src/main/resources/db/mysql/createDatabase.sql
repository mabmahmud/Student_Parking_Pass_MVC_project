DROP DATABASE if exists cis2232_parking;
CREATE DATABASE cis2232_parking;
USE cis2232_parking;

CREATE TABLE ParkingPass (
  id int(5) NOT NULL,
  firstName varchar(20) DEFAULT NULL COMMENT 'First Name',
  lastName varchar(20) DEFAULT NULL COMMENT 'Last Name',
  licensePlate varchar(06) DEFAULT NULL COMMENT 'license Plate',
  program varchar(140) DEFAULT NULL COMMENT 'Program',
  createdDateTime varchar(20)  DEFAULT current_timestamp() COMMENT 'When record was created. yyyy-MM-dd hh:mm'
);


ALTER TABLE ParkingPass
  ADD PRIMARY KEY (id);

ALTER TABLE ParkingPass
  MODIFY id int(5) NOT NULL AUTO_INCREMENT;

INSERT INTO ParkingPass (firstName, lastName, licensePlate, program) VALUES
('Michael', 'Sawyer', 'BW0632', 'CIS'),
('Parker', 'Gallant', 'C93FA2', 'CNET'),
('Khari', 'Woods', 'V9C6TA', 'BA');