
-- using H2 Database, so it should automatically create a database, when the app is started, however here is
--
--CREATE DATABASE debtDebtorDatabase;

--GO

--CREATE TABLE IF NOT EXISTS CUSTOMER(
--`customer_id` INT(12) NOT NULL auto_increment,
--`first_name` VARCHAR(200) collate latin1_general_ci NOT NULL,
--`last_name` VARCHAR(200) collate latin1_general_ci NOT NULL,
--`country` VARCHAR(63) collate latin1_general_ci NOT NULL,
--`email` VARCHAR() NOT NULl,
--`password` VARCHAR(58) NOT NULL,
-- PRIMARY KEY  (`customer_id`)
--) DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci

--GO

--insert into CUSTOMER(`customer_id`, `first_name`, `last_name`, `country` , `email`, `password`)
--values ('1', 'Tim', 'Tomlin', 'Irland', 't.alternative@irish.com', `sertedeErd1`);
--insert into CUSTOMER(`customer_id`, `first_name`, `last_name`, `country` , `email`, `password`)
--values ('2', 'Robert', 'Robin', 'Trinidad and Tobago', 'rascal26@wartherdfb.com', `wonderWorld`);
--insert into CUSTOMER(`customer_id`, `first_name`, `last_name`, `country` , `email`, `password`)
--values ('3', 'Pjotr', 'Plazeck', 'Poland', 'traffefd123@rerere.pl', `qwertgfd`);
--insert into CUSTOMER(`customer_id`, `first_name`, `last_name`, `country` , `email`, `password`)
--values ('4', 'Zonman', 'Dragostan', 'Serbia', 'dragostan.z@milcheck.com', `susususuereer`);
--insert into CUSTOMER(`customer_id`, `first_name`, `last_name`, `country` , `email`, `password`)
--values ('5', 'Lint', 'Lomnack', 'Lichtenstain', 'freemanLint@yourcastle.com', `3456tavr!`);
--insert into CUSTOMER(`customer_id`, `first_name`, `last_name`, `country` , `email`, `password`)
--values ('6', 'Miika', 'Karlson', 'Finnland', 'submerger@aquaparkK.fi', `sregrtjlghht`);

--GO

--CREATE TABLE IF NOT EXISTS DEBT_CASE(
--`caseId` INT(12) NOT NULL auto_increment,
--`customer_id` INT(12) NOT NULL,
--`amount` BIGINT(63) collate latin1_general_ci NOT NULL,
--`currency` VARCHAR(200) collate latin1_general_ci NOT NULL,
--`due_date` DATE NOT NULl,
-- PRIMARY KEY  (`caseId`),
-- FOREIGN KEY (customer_id) REFERENCES CUSTOMER(caseId)
--) DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci

--GO

--insert into DEBT_CASE(`caseId`, `customer_id`, `amount`, `currency` , `due_date`)
--values (1, 1, 23000.12, 'USD', '2022-10-02 01:17:34');
--insert into DEBT_CASE(`caseId`, `customer_id`, `amount`, `currency` , `due_date`)
--values (2, 1, 23000.12, 'USD', '2022-03-16 14:13:75');
--insert into DEBT_CASE(`caseId`, `customer_id`, `amount`, `currency` , `due_date`)
--values (3, 1, 23000.12, 'USD', '2022-07-14 23:13:75');
--insert into DEBT_CASE(`caseId`, `customer_id`, `amount`, `currency` , `due_date`)
--values (4, 6, 2300, 'JPY', '2022-12-20 17:13:75');
--insert into DEBT_CASE(`caseId`, `customer_id`, `amount`, `currency` , `due_date`)
--values (5, 1, 1.12, 'EUR', '2022-12-16 17:13:75');
