USE Hotel
GO

DBCC CHECKIDENT ('classification_rooms', RESEED, 0);
GO

DBCC CHECKIDENT ('rooms', RESEED, 0);
GO

DBCC CHECKIDENT ('users', RESEED, 0);
GO

DBCC CHECKIDENT ('customers', RESEED, 0);
GO

DBCC CHECKIDENT ('staff', RESEED, 0);
GO

DBCC CHECKIDENT ('booking', RESEED, 0);
GO

DBCC CHECKIDENT ('services', RESEED, 0);
GO

INSERT INTO booking(id_customer, check_in, check_out, id_room, total) VALUES 
	(2, '2022-11-20', '2022-11-30', 2, 2000),
	(1, '2022-10-25', '2022-10-27', 3, 4000);
GO

INSERT INTO services(service_name, cost, description) VALUES 
	('Snacks', 1000, 'We will bring snacks to the room'),
	('Bar', 2000, 'We will bring alcohol to the room'),
	('Teapot', 500, 'We will bring taste tea to the room'),
	('Cleaning', 500, 'Clean room');
GO

INSERT INTO service_util(id_booking, id_service, cost) VALUES 
	(1, 1, 1000),
	(2, 4, 500);
GO

INSERT INTO users(login, password) VALUES 
	('sol', '1221323'),
	('soup', '4994934'),
	('dslds', '21212121'),
	('rirei', '44930934'),
	('asd', '540-056'),
	--staff
	('topodf', '881323'),
	('erlkler', '4334'),
	(';l;lsd', '22121'),
	('[er[p', '434'),
	('qwqwq', '3221');
GO

INSERT INTO customers(id_user, name, surname, patronymic, date_of_birth, phone, passport, status) VALUES 
	(1,'vlad', 'krod', null, '1995-12-05', '+79212193982', '2121322', 1),
	(3, 'andrew', 'reerl', 'erma', '1995-12-05', '+79212193982','5549054',  1),
	(4, 'petya', 'rrrtl', 'ddsdsdsa', '1995-12-05', '+79212193982','543334',  1),
	(2, 'stas', 'assa', null, '1995-12-05', '+79212193982','266992',  0);
GO

INSERT INTO staff(id_user, name, surname, patronymic, date_of_birth, phone, post, status) VALUES 
	(5,'dmitry', 'ffdggf', null, '1995-12-05' , '23909023', 'ceo', 1),
	(6,'max', 'ruur', 'ryuyta', '1965-11-22', '5235490549', 'manager', 1),
	(7,'andrew', 'troport', 'ddsdsdsa', '1966-05-01', '509304304', 'programmer', 1),
	(8,'salman', 'hgooe', null, '1966-05-01', '+29112232', 'admin', 0);
GO

INSERT INTO classification_rooms(classification_name) VALUES 
	('Luxury'),
	('Economy'),
	('For two');
GO

INSERT INTO rooms(id_classification, cost, number_of_seats) VALUES 
    (1, 3000, 4), 
    (3, 2000, 2),
    (2, 1000, 1);
GO

DELETE FROM service_util
DELETE FROM services
DELETE FROM booking
DELETE FROM customers
DELETE FROM staff
DELETE FROM users
DELETE FROM rooms
DELETE FROM classification_rooms

SELECT * FROM service_util
SELECT * FROM services
SELECT * FROM booking
SELECT * FROM customers
SELECT * FROM staff
SELECT * FROM users
SELECT * FROM rooms 
SELECT * FROM classification_rooms
