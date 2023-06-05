USE Hotel
GO
--СОЗДАНИЕ ПРЕДСТАВЛЕНИЙ
/* Список номеров с классификацией */
CREATE VIEW ListRoomsWithClassifications (Cost, Number_of_seats, Classification_name) AS
	SELECT rooms.cost, rooms.number_of_seats, classification_name FROM rooms
	JOIN classification_rooms ON rooms.id_room = classification_rooms.id_classification
	GROUP BY rooms.cost, rooms.number_of_seats, classification_name 
GO

SELECT * FROM ListRoomsWithClassifications
GO

/* Список данных клиентов и сотрудников */
CREATE VIEW ListOfDataCustomers(Names, Surnames, Pantronymics, Passports, Phones, Statuses) AS
	SELECT customers.name, customers.surname, customers.patronymic, customers.passport, customers.phone, customers.status 
	FROM customers
	GROUP BY customers.name, customers.surname, customers.patronymic, customers.passport, customers.phone, customers.status
GO

CREATE VIEW ListOfDataStaff(Names, Surnames, Pantronymics, Birthdate, Phones, Post, Statuses) AS
	SELECT staff.name, staff.surname, staff.patronymic, staff.date_of_birth, staff.phone, staff.post, staff.status 
	FROM staff
	GROUP BY staff.name, staff.surname, staff.patronymic, staff.date_of_birth, staff.phone, staff.post, staff.status
GO

SELECT * FROM ListOfDataCustomers
SELECT * FROM ListOfDataStaff
GO

DECLARE @ArrivalDate AS DATETIME
DECLARE @DepartureDate AS DATETIME

--СОЗДАНИЕ ПРОЦЕДУР
--ПРОСМОТР
-- Просмотр номеров
CREATE PROCEDURE ShowRooms
AS
SELECT rooms.cost AS Cost, rooms.number_of_seats AS Number_of_seats FROM rooms
GO

EXEC ShowRooms
GO


-- Просмотр классификации номеров
CREATE PROCEDURE ShowClassifications
AS
SELECT classification_rooms.classification_name AS Name FROM classification_rooms
GO

EXEC ShowClassifications
GO

-- Просмотр дополнительных услуг
CREATE PROCEDURE ShowAdditionalServices
AS
SELECT services.service_name AS Name, services.cost AS Cost, services.description AS Description FROM services
GO

EXEC ShowAdditionalServices
GO

-- Просмотр заказанных дополнительных услуг по номеру брони
CREATE PROCEDURE ShowAdditionalServicesByNumberOfBooking (@ID_BOOKING as int)
AS
SELECT service_name AS Name, service_util.cost AS Cost, services.description AS Description FROM services
JOIN service_util ON services.id_service = service_util.id_service
WHERE service_util.id_booking = @ID_BOOKING
GO

EXEC ShowAdditionalServicesByNumberOfBooking 2
GO

-- Просмотр всех бронирований 
CREATE PROCEDURE ShowBooking
AS
SELECT booking.check_in AS 'IN', booking.check_out AS OUT, booking.total AS Total FROM booking
GO

EXEC ShowBooking
GO

-- Просмотр всех бронирований клиента 
CREATE PROCEDURE ShowClientBooking (@ID_CUSTOMER as int)
AS
SELECT booking.check_in AS 'IN', booking.check_out AS OUT, booking.total AS Total FROM booking
JOIN customers ON booking.id_customer = customers.id_customer
WHERE customers.id_customer = @ID_CUSTOMER
GO

EXEC ShowClientBooking 2
GO


-- Просмотр личных данных клиента 
CREATE PROCEDURE ShowClient (@Login as varchar(10))
AS
SELECT customers.name, customers.surname, customers.patronymic, customers.passport, customers.phone FROM customers
JOIN users ON users.id_user = customers.id_user
WHERE users.login = @Login
GO

EXEC ShowClient 'sol'
GO

-- Просмотр личных данных сотрудника
CREATE PROCEDURE ShowStaff (@Login as varchar(50))
AS
SELECT staff.name, staff.surname, staff.patronymic, staff.date_of_birth, staff.phone, staff.post FROM staff
JOIN users ON users.id_user = staff.id_user
WHERE users.login = @Login
GO

EXEC ShowStaff 'asd'
GO

--ДОБАВЛЕНИЕ
--Зарегистрировать нового сотрудника
CREATE PROCEDURE AddStaff (@Login as varchar(50), @Password as varchar(255), @Name as nvarchar(50), @Surname as nvarchar(50), @Pantronymic as nvarchar(50), 
						   @Birhdate as date, @Post as nvarchar(50), @Phone varchar(20), @Status as bit)
AS
INSERT users(login, password) 
	VALUES (@Login, @Password)

INSERT staff(id_user, name, surname, patronymic, date_of_birth, post, phone, status)
	VALUES ((SELECT users.id_user FROM users
			WHERE users.login = @Login AND users.password = @Password), @Name, @Surname, @Pantronymic, @Birhdate, @Post, @Phone, 1)
GO

EXEC AddStaff 'asosdlsd', '1234', Андрей, Ветров, Борисович, '1999-05-05', Программист, 791294335, 1 
GO

--Зарегистрировать клиента
ALTER PROCEDURE AddCustomer (@Login as varchar(50), @Password as varchar(255), @Name as nvarchar(50), @Surname as nvarchar(50), @Pantronymic as nvarchar(50), 
						  @Birhdate as date, @Passport as varchar(10), @Phone varchar(20), @Status as bit)
AS
INSERT users(login, password) 
	VALUES (@Login, @Password)

INSERT customers(id_user, name, surname, patronymic, date_of_birth, phone, passport, status)
	VALUES ((SELECT users.id_user FROM users
			WHERE users.login = @Login AND users.password = @Password), @Name, @Surname, @Pantronymic, @Birhdate, @Phone, @Passport, 1)
GO

EXEC AddCustomer 'lol', '35554', Василий, Кор, Петров, '1999-05-05', '5549054', 74544212839, 1 
GO

--Добавление номеров
CREATE PROCEDURE AddRoom (@ID_CLASSIFICATION as int, @Cost as money, @NumSeats as int)
AS
INSERT rooms(id_classification, cost, number_of_seats)
	VALUES (@ID_CLASSIFICATION, @Cost, @NumSeats)
GO

EXEC AddRoom 4, 1000, 1
GO

SELECT id_classification FROM classification_rooms WHERE classification_name LIKE 'Luxury'

--Добавление дополнительных услуг
CREATE PROCEDURE AddServices (@Name as nvarchar(50), @Cost as money, @Description as nvarchar(100))
AS
INSERT services(service_name, cost, description)
	VALUES (@Name, @Cost, @Description)
GO

EXEC AddServices 'чайник', 500, 'Предоставим чайник.'
GO

--Добавление классификации номеров
CREATE PROCEDURE AddRoomClassification (@NameClassification as nvarchar(50))
AS
INSERT classification_rooms(classification_name) 
	VALUES (@NameClassification)
GO

EXEC AddRoomClassification 'Президентский номер'
GO

--Добавление записи бронирования
CREATE PROCEDURE AddBooking (@ID_CUSTOMER as int, @IN as date, @OUT as date, @ID_ROOM as int)
AS
INSERT  booking(id_customer, check_in, check_out, id_room, total)
	VALUES (@ID_CUSTOMER, @IN, @OUT, @ID_ROOM, DATEDIFF(DAY, @IN, @OUT) * (SELECT rooms.cost FROM rooms
															   WHERE rooms.id_room = @ID_ROOM))
GO

EXEC AddBooking 2, '2022-11-05', '2022-11-07', 3
GO

--Заказ дополнительной услуги 
CREATE PROCEDURE BuyService(@ID_BOOKING as int, @ID_SERVICE as int)
AS
INSERT service_util(id_booking, id_service, cost)
	VALUES (@ID_BOOKING, @ID_SERVICE, (SELECT cost FROM services
										WHERE services.id_service = @ID_SERVICE))
UPDATE booking set total = DATEDIFF(DAY, booking.check_in, booking.check_out) * (SELECT rooms.cost FROM rooms
															   WHERE rooms.id_room = booking.id_room) + 
															   (SELECT service_util.cost FROM service_util
															   WHERE service_util.id_booking = @ID_BOOKING AND
															   service_util.id_service = @ID_SERVICE)
GO

EXEC BuyService 4, 3
GO

--ИЗМЕНЕНИЕ
--Изменение номеров
CREATE PROCEDURE ChangeRooms (@ID_ROOM as int, @ID_CLASSIFICATION as int, @Cost as money, @NumSeats as int)
AS
UPDATE rooms set id_classification = @ID_CLASSIFICATION, cost = @Cost, number_of_seats = @NumSeats
WHERE rooms.id_room = @ID_ROOM

EXEC ChangeRooms 1, 4, 6000, 5
GO

--Изменение своего пароля 
CREATE PROCEDURE ChangePassword (@Login as varchar(50), @Password as varchar(255))
AS
UPDATE users set users.password = @Password
WHERE users.login = @Login

EXEC ChangePassword 'admin', 'admin'
GO

--Изменение дополнительных услуг
CREATE PROCEDURE ChangeServices (@ID_SERVICE as int, @Name as nvarchar(50), @Cost as money, @Description as nvarchar(50))
AS
UPDATE services set service_name = @Name, cost = @Cost, description = @Description 
WHERE services.id_service = @ID_SERVICE

EXEC ChangeServices 5, 'бутылка воды', 20, 'Предоставим бутылку воды.'
GO

--Изменение дат заезда и выезда клиента
CREATE PROCEDURE ChangeDatesInBooking (@ID_BOOKING as int, @IN as date, @OUT as date)
AS
UPDATE booking set check_in = @IN, check_out = @OUT, total = DATEDIFF(DAY, @IN, @OUT) * (SELECT rooms.cost FROM rooms
															   WHERE rooms.id_room = booking.id_room) 
WHERE booking.id_booking = @ID_BOOKING

EXEC ChangeDatesInBooking 4, '2022-11-01', '2022-11-05'
GO

--Изменение своих личных данных клиента
ALTER PROCEDURE ChangeDataСustomer(@ID_CUSTOMER as int, @Name as nvarchar(50), @Surname as nvarchar(50), @Pantronymic as nvarchar(50), 
						  @Birthdate as date, @Phone as varchar(20), @Passport as varchar(10))
AS
UPDATE customers set customers.name = @Name, customers.surname = @Surname, customers.patronymic = @Pantronymic, customers.date_of_birth = @Birthdate,
									  customers.passport = @Passport, customers.phone = @Phone
WHERE customers.id_customer = @ID_CUSTOMER

EXEC ChangeDataСustomer 1, 'vlad', 'krod', 'denisovich', '2022-11-05', '+79212193982', '2121322'
GO

--Изменение своих личных данных сотрудника
CREATE PROCEDURE ChangeDataStaff(@ID_STAFF as int, @Name as nvarchar(50), @Surname as nvarchar(50), @Pantronymic as nvarchar(50), 
						  @Birthdate as date, @Phone as varchar(20))
AS
UPDATE staff set staff.name = @Name, staff.surname = @Surname, staff.patronymic = @Pantronymic, staff.date_of_birth = @Birthdate, staff.phone = @Phone
WHERE staff.id_employee = @ID_STAFF

EXEC ChangeDataStaff 1, 'dmitry', 'ffdggf', null, '1995-12-05', '2121322'
GO

--Удаление (активация, деактивация): 
--Активация и деактивация сотрудника без возможности авторизовываться в дальнейшем
CREATE PROCEDURE ChangeStaffStatus (@ID as int, @Status as bit)
AS
UPDATE staff set status = @Status
WHERE staff.id_employee = @ID
GO

SELECT id_employee FROM staff WHERE staff.id_user LIKE 6
--Активация и деактивация клиента без возможности авторизовываться в дальнейшем
CREATE PROCEDURE ChangeCustomerStatus (@ID as int, @Status as bit)
AS
UPDATE customers set status = @Status
WHERE customers.id_customer = @ID
GO

--СОЗДАНИЕ ТАБЛИЦ
/* Object:  Table users */

CREATE TABLE users (
id_user INT IDENTITY(1,1) NOT NULL,
login VARCHAR(50) UNIQUE NOT NULL,
password VARCHAR(50) NOT NULL,
);
GO

/* Object:  Table staff */

CREATE TABLE staff(
	id_employee INT IDENTITY(1,1) NOT NULL,
	id_user INT NOT NULL,
	name NVARCHAR(50) NOT NULL,
	surname NVARCHAR(50) NOT NULL,
	patronymic NVARCHAR(50) NULL,
	date_of_birth DATE NOT NULL,
	post NVARCHAR(50) NOT NULL,
	phone VARCHAR(20) NULL,
	status BIT NOT NULL,
);
GO

ALTER TABLE [dbo].[staff]  WITH CHECK ADD  CONSTRAINT [FK_staff_users] FOREIGN KEY([id_user])
REFERENCES [dbo].[users] ([id_user])
GO

ALTER TABLE [dbo].[staff] CHECK CONSTRAINT [FK_staff_users]
GO


/* Object:  Table services */

CREATE TABLE services(
	id_service INT IDENTITY(1,1) NOT NULL,
	service_name NVARCHAR(50) NOT NULL,
	cost MONEY NOT NULL,
	description NVARCHAR(100) NOT NULL,
);
GO

/* Object:  Table services_util */

CREATE TABLE services_util(
	id_booking INT NOT NULL,
	id_service INT NOT NULL,
	cost MONEY NOT NULL
);
GO

ALTER TABLE [dbo].[service_util]  WITH CHECK ADD  CONSTRAINT [FK_service_util_booking] FOREIGN KEY([id_booking])
REFERENCES [dbo].[booking] ([id_booking])
GO

ALTER TABLE [dbo].[service_util] CHECK CONSTRAINT [FK_service_util_booking]
GO

ALTER TABLE [dbo].[service_util]  WITH CHECK ADD  CONSTRAINT [FK_service_util_services] FOREIGN KEY([id_service])
REFERENCES [dbo].[services] ([id_service])
GO

ALTER TABLE [dbo].[service_util] CHECK CONSTRAINT [FK_service_util_services]
GO

/* Object:  Table rooms */
CREATE TABLE rooms(
	id_room INT IDENTITY(1,1) NOT NULL,
	id_classification INT NOT NULL,
	cost MONEY NOT NULL,
	number_of_seats INT NOT NULL,
);

ALTER TABLE [dbo].[rooms]  WITH CHECK ADD  CONSTRAINT [FK_rooms_classification_rooms] FOREIGN KEY([id_classification])
REFERENCES [dbo].[classification_rooms] ([id_classification])
GO

ALTER TABLE [dbo].[rooms] CHECK CONSTRAINT [FK_rooms_classification_rooms]
GO

/* Object:  Table customers */

CREATE TABLE customers(
	id_customer INT IDENTITY(1,1) NOT NULL,
	id_user INT NOT NULL,
	name NVARCHAR(50) NOT NULL,
	surname NVARCHAR(50) NOT NULL,
	patronymic NVARCHAR(50) NULL,
	date_of_birth DATE NOT NULL,
	phone VARCHAR(20) NULL,
	passport VARCHAR(10) UNIQUE NOT NULL,
	status BIT NOT NULL,
);

ALTER TABLE [dbo].[customers]  WITH CHECK ADD  CONSTRAINT [FK_customers_users] FOREIGN KEY([id_user])
REFERENCES [dbo].[users] ([id_user])
GO

ALTER TABLE [dbo].[customers] CHECK CONSTRAINT [FK_customers_users]
GO

/* Object:  Table classification_rooms */

CREATE TABLE classification_rooms(
	id_classification INT IDENTITY(1,1) NOT NULL,
	classification_name NVARCHAR(50) NOT NULL,
);
GO

/* Object:  Table booking */

CREATE TABLE booking(
	id_booking INT IDENTITY(1,1) NOT NULL,
	id_customer INT NOT NULL,
	check_in DATE NOT NULL,
	check_out DATE NOT NULL,
	id_room INT NOT NULL,
	total MONEY NOT NULL,
);
GO

ALTER TABLE booking ADD CONSTRAINT CK_Dates
CHECK (booking.check_out > booking.check_in)
GO

ALTER TABLE [dbo].[booking]  WITH CHECK ADD  CONSTRAINT [FK_booking_customers] FOREIGN KEY([id_customer])
REFERENCES [dbo].[customers] ([id_customer])
GO

ALTER TABLE [dbo].[booking] CHECK CONSTRAINT [FK_booking_customers]
GO

ALTER TABLE [dbo].[booking]  WITH CHECK ADD  CONSTRAINT [FK_booking_rooms] FOREIGN KEY([id_room])
REFERENCES [dbo].[rooms] ([id_room])
GO

ALTER TABLE [dbo].[booking] CHECK CONSTRAINT [FK_booking_rooms]
GO
