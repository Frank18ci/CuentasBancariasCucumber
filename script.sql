create database cuentas_bancarias_test;
create database cuentas_bancarias;

use cuentas_bancarias;

CREATE TABLE IF NOT EXISTS Account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client VARCHAR(50) NOT NULL,
    balance DOUBLE NOT NULL
);

INSERT INTO Account(client,balance) VALUES('Jose',1600.00);
INSERT INTO Account(client,balance) VALUES('Miguel',2600.00);
INSERT INTO Account(client,balance) VALUES('Maria',1990.00);