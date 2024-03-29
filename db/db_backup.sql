USE [Hotel]
GO
/****** Object:  StoredProcedure [dbo].[ShowStaff]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ShowStaff]
GO
/****** Object:  StoredProcedure [dbo].[ShowRooms]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ShowRooms]
GO
/****** Object:  StoredProcedure [dbo].[ShowClientBooking]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ShowClientBooking]
GO
/****** Object:  StoredProcedure [dbo].[ShowClient]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ShowClient]
GO
/****** Object:  StoredProcedure [dbo].[ShowClassifications]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ShowClassifications]
GO
/****** Object:  StoredProcedure [dbo].[ShowBookingID]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ShowBookingID]
GO
/****** Object:  StoredProcedure [dbo].[ShowBooking]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ShowBooking]
GO
/****** Object:  StoredProcedure [dbo].[ShowAvailableRooms]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ShowAvailableRooms]
GO
/****** Object:  StoredProcedure [dbo].[ShowAdditionalServicesByNumberOfBooking]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ShowAdditionalServicesByNumberOfBooking]
GO
/****** Object:  StoredProcedure [dbo].[ShowAdditionalServices]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ShowAdditionalServices]
GO
/****** Object:  StoredProcedure [dbo].[ChangeStaffStatus]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ChangeStaffStatus]
GO
/****** Object:  StoredProcedure [dbo].[ChangeServices]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ChangeServices]
GO
/****** Object:  StoredProcedure [dbo].[ChangeRooms]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ChangeRooms]
GO
/****** Object:  StoredProcedure [dbo].[ChangePassword]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ChangePassword]
GO
/****** Object:  StoredProcedure [dbo].[ChangeDatesInBooking]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ChangeDatesInBooking]
GO
/****** Object:  StoredProcedure [dbo].[ChangeDataСustomer]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ChangeDataСustomer]
GO
/****** Object:  StoredProcedure [dbo].[ChangeDataStaff]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ChangeDataStaff]
GO
/****** Object:  StoredProcedure [dbo].[ChangeCustomerStatus]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[ChangeCustomerStatus]
GO
/****** Object:  StoredProcedure [dbo].[BuyService]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[BuyService]
GO
/****** Object:  StoredProcedure [dbo].[AddStaff]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[AddStaff]
GO
/****** Object:  StoredProcedure [dbo].[AddServices]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[AddServices]
GO
/****** Object:  StoredProcedure [dbo].[AddRoomClassification]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[AddRoomClassification]
GO
/****** Object:  StoredProcedure [dbo].[AddRoom]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[AddRoom]
GO
/****** Object:  StoredProcedure [dbo].[AddCustomer]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[AddCustomer]
GO
/****** Object:  StoredProcedure [dbo].[AddBooking]    Script Date: 10.02.2023 13:52:16 ******/
DROP PROCEDURE IF EXISTS [dbo].[AddBooking]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[booking]') AND type in (N'U'))
ALTER TABLE [dbo].[booking] DROP CONSTRAINT IF EXISTS [CK_Dates]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[staff]') AND type in (N'U'))
ALTER TABLE [dbo].[staff] DROP CONSTRAINT IF EXISTS [FK_staff_users]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[service_util]') AND type in (N'U'))
ALTER TABLE [dbo].[service_util] DROP CONSTRAINT IF EXISTS [FK_service_util_services]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[service_util]') AND type in (N'U'))
ALTER TABLE [dbo].[service_util] DROP CONSTRAINT IF EXISTS [FK_service_util_booking]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[rooms]') AND type in (N'U'))
ALTER TABLE [dbo].[rooms] DROP CONSTRAINT IF EXISTS [FK_rooms_classification_rooms]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[customers]') AND type in (N'U'))
ALTER TABLE [dbo].[customers] DROP CONSTRAINT IF EXISTS [FK_customers_users]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[booking]') AND type in (N'U'))
ALTER TABLE [dbo].[booking] DROP CONSTRAINT IF EXISTS [FK_booking_rooms]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[booking]') AND type in (N'U'))
ALTER TABLE [dbo].[booking] DROP CONSTRAINT IF EXISTS [FK_booking_customers]
GO
/****** Object:  Table [dbo].[users]    Script Date: 10.02.2023 13:52:16 ******/
DROP TABLE IF EXISTS [dbo].[users]
GO
/****** Object:  Table [dbo].[services_util]    Script Date: 10.02.2023 13:52:16 ******/
DROP TABLE IF EXISTS [dbo].[services_util]
GO
/****** Object:  Table [dbo].[services]    Script Date: 10.02.2023 13:52:16 ******/
DROP TABLE IF EXISTS [dbo].[services]
GO
/****** Object:  Table [dbo].[service_util]    Script Date: 10.02.2023 13:52:16 ******/
DROP TABLE IF EXISTS [dbo].[service_util]
GO
/****** Object:  Table [dbo].[booking]    Script Date: 10.02.2023 13:52:16 ******/
DROP TABLE IF EXISTS [dbo].[booking]
GO
/****** Object:  View [dbo].[ListRoomsWithClassifications]    Script Date: 10.02.2023 13:52:16 ******/
DROP VIEW IF EXISTS [dbo].[ListRoomsWithClassifications]
GO
/****** Object:  Table [dbo].[rooms]    Script Date: 10.02.2023 13:52:16 ******/
DROP TABLE IF EXISTS [dbo].[rooms]
GO
/****** Object:  Table [dbo].[classification_rooms]    Script Date: 10.02.2023 13:52:16 ******/
DROP TABLE IF EXISTS [dbo].[classification_rooms]
GO
/****** Object:  View [dbo].[ListOfDataStaff]    Script Date: 10.02.2023 13:52:16 ******/
DROP VIEW IF EXISTS [dbo].[ListOfDataStaff]
GO
/****** Object:  Table [dbo].[staff]    Script Date: 10.02.2023 13:52:16 ******/
DROP TABLE IF EXISTS [dbo].[staff]
GO
/****** Object:  View [dbo].[ListOfDataCustomers]    Script Date: 10.02.2023 13:52:16 ******/
DROP VIEW IF EXISTS [dbo].[ListOfDataCustomers]
GO
/****** Object:  Table [dbo].[customers]    Script Date: 10.02.2023 13:52:16 ******/
DROP TABLE IF EXISTS [dbo].[customers]
GO
USE [master]
GO
/****** Object:  Database [Hotel]    Script Date: 10.02.2023 13:52:16 ******/
DROP DATABASE IF EXISTS [Hotel]
GO
/****** Object:  Database [Hotel]    Script Date: 10.02.2023 13:52:16 ******/
CREATE DATABASE [Hotel]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Hotel', FILENAME = N'D:\MS_SQL\MSSQL15.MSSQLSERVER\MSSQL\DATA\Hotel.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Hotel_log', FILENAME = N'D:\MS_SQL\MSSQL15.MSSQLSERVER\MSSQL\DATA\Hotel_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Hotel] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Hotel].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Hotel] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Hotel] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Hotel] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Hotel] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Hotel] SET ARITHABORT OFF 
GO
ALTER DATABASE [Hotel] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Hotel] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Hotel] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Hotel] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Hotel] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Hotel] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Hotel] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Hotel] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Hotel] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Hotel] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Hotel] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Hotel] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Hotel] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Hotel] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Hotel] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Hotel] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Hotel] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Hotel] SET RECOVERY FULL 
GO
ALTER DATABASE [Hotel] SET  MULTI_USER 
GO
ALTER DATABASE [Hotel] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Hotel] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Hotel] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Hotel] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Hotel] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Hotel] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Hotel', N'ON'
GO
ALTER DATABASE [Hotel] SET QUERY_STORE = OFF
GO
USE [Hotel]
GO
/****** Object:  Table [dbo].[customers]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customers](
	[id_customer] [int] IDENTITY(1,1) NOT NULL,
	[id_user] [int] NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[surname] [nvarchar](50) NOT NULL,
	[patronymic] [nvarchar](50) NULL,
	[date_of_birth] [date] NOT NULL,
	[phone] [varchar](20) NULL,
	[passport] [varchar](10) NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_Customers] PRIMARY KEY CLUSTERED 
(
	[id_customer] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[ListOfDataCustomers]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[ListOfDataCustomers](Names, Surnames, Pantronymics, Passports, Phones, Statuses) AS
	SELECT customers.name, customers.surname, customers.patronymic, customers.passport, customers.phone, customers.status 
	FROM customers
	GROUP BY customers.name, customers.surname, customers.patronymic, customers.passport, customers.phone, customers.status
GO
/****** Object:  Table [dbo].[staff]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[staff](
	[id_employee] [int] IDENTITY(1,1) NOT NULL,
	[id_user] [int] NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[surname] [nvarchar](50) NOT NULL,
	[patronymic] [nvarchar](50) NULL,
	[date_of_birth] [date] NOT NULL,
	[post] [nvarchar](50) NOT NULL,
	[phone] [varchar](20) NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_staff_1] PRIMARY KEY CLUSTERED 
(
	[id_employee] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[ListOfDataStaff]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[ListOfDataStaff](Names, Surnames, Pantronymics, Birthdate, Phones, Post, Statuses) AS
	SELECT staff.name, staff.surname, staff.patronymic, staff.date_of_birth, staff.phone, staff.post, staff.status 
	FROM staff
	GROUP BY staff.name, staff.surname, staff.patronymic, staff.date_of_birth, staff.phone, staff.post, staff.status
GO
/****** Object:  Table [dbo].[classification_rooms]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[classification_rooms](
	[id_classification] [int] IDENTITY(1,1) NOT NULL,
	[classification_name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Classification_rooms] PRIMARY KEY CLUSTERED 
(
	[id_classification] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[rooms]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[rooms](
	[id_room] [int] IDENTITY(1,1) NOT NULL,
	[id_classification] [int] NOT NULL,
	[cost] [money] NOT NULL,
	[number_of_seats] [int] NOT NULL,
 CONSTRAINT [PK_rooms_1] PRIMARY KEY CLUSTERED 
(
	[id_room] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[ListRoomsWithClassifications]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[ListRoomsWithClassifications] (Cost, Number_of_seats, Classification_name) AS
	SELECT rooms.cost, rooms.number_of_seats, classification_name FROM rooms
	JOIN classification_rooms ON rooms.id_room = classification_rooms.id_classification
	GROUP BY rooms.cost, rooms.number_of_seats, classification_name 
GO
/****** Object:  Table [dbo].[booking]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[booking](
	[id_booking] [int] IDENTITY(1,1) NOT NULL,
	[id_customer] [int] NOT NULL,
	[check_in] [date] NOT NULL,
	[check_out] [date] NOT NULL,
	[id_room] [int] NOT NULL,
	[total] [money] NOT NULL,
 CONSTRAINT [PK_service] PRIMARY KEY CLUSTERED 
(
	[id_booking] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[service_util]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[service_util](
	[id_booking] [int] NOT NULL,
	[id_service] [int] NOT NULL,
	[cost] [money] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[services]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[services](
	[id_service] [int] IDENTITY(1,1) NOT NULL,
	[service_name] [nvarchar](50) NOT NULL,
	[cost] [money] NOT NULL,
	[description] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_additional_services] PRIMARY KEY CLUSTERED 
(
	[id_service] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[services_util]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[services_util](
	[id_booking] [int] NOT NULL,
	[id_service] [int] NOT NULL,
	[cost] [money] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id_user] [int] IDENTITY(1,1) NOT NULL,
	[login] [varchar](50) NOT NULL,
	[password] [varchar](255) NOT NULL,
 CONSTRAINT [PK_users_1] PRIMARY KEY CLUSTERED 
(
	[id_user] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[classification_rooms] ON 

INSERT [dbo].[classification_rooms] ([id_classification], [classification_name]) VALUES (1, N'Luxury')
INSERT [dbo].[classification_rooms] ([id_classification], [classification_name]) VALUES (2, N'Economy')
INSERT [dbo].[classification_rooms] ([id_classification], [classification_name]) VALUES (3, N'For two')
INSERT [dbo].[classification_rooms] ([id_classification], [classification_name]) VALUES (4, N'Presidential')
INSERT [dbo].[classification_rooms] ([id_classification], [classification_name]) VALUES (5, N'Одиночный')
INSERT [dbo].[classification_rooms] ([id_classification], [classification_name]) VALUES (6, N'супер пупер')
INSERT [dbo].[classification_rooms] ([id_classification], [classification_name]) VALUES (7, N'Президентский номер')
SET IDENTITY_INSERT [dbo].[classification_rooms] OFF
GO
SET IDENTITY_INSERT [dbo].[customers] ON 

INSERT [dbo].[customers] ([id_customer], [id_user], [name], [surname], [patronymic], [date_of_birth], [phone], [passport], [status]) VALUES (1, 1, N'vlad', N'krod', NULL, CAST(N'1995-12-05' AS Date), N'+79212193982', N'2121322', 1)
INSERT [dbo].[customers] ([id_customer], [id_user], [name], [surname], [patronymic], [date_of_birth], [phone], [passport], [status]) VALUES (2, 3, N'andrew', N'reerl', N'erma', CAST(N'1995-12-05' AS Date), N'+79212193982', N'5549054', 1)
INSERT [dbo].[customers] ([id_customer], [id_user], [name], [surname], [patronymic], [date_of_birth], [phone], [passport], [status]) VALUES (3, 17, N'petya', N'rrrtl', N'ddsdsdsa', CAST(N'1995-12-05' AS Date), N'+79212193982', N'543334', 1)
INSERT [dbo].[customers] ([id_customer], [id_user], [name], [surname], [patronymic], [date_of_birth], [phone], [passport], [status]) VALUES (4, 2, N'stas', N'assa', NULL, CAST(N'1995-12-05' AS Date), N'+79212193982', N'266992', 0)
INSERT [dbo].[customers] ([id_customer], [id_user], [name], [surname], [patronymic], [date_of_birth], [phone], [passport], [status]) VALUES (5, 20, N'Kuk', N'Lol', N'Denis', CAST(N'1990-02-04' AS Date), N'+79932937777', N'7303023221', 1)
SET IDENTITY_INSERT [dbo].[customers] OFF
GO
SET IDENTITY_INSERT [dbo].[rooms] ON 

INSERT [dbo].[rooms] ([id_room], [id_classification], [cost], [number_of_seats]) VALUES (1, 1, 3000.0000, 4)
INSERT [dbo].[rooms] ([id_room], [id_classification], [cost], [number_of_seats]) VALUES (2, 3, 4000.0000, 2)
INSERT [dbo].[rooms] ([id_room], [id_classification], [cost], [number_of_seats]) VALUES (3, 2, 1000.0000, 1)
INSERT [dbo].[rooms] ([id_room], [id_classification], [cost], [number_of_seats]) VALUES (4, 4, 7000.0000, 2)
INSERT [dbo].[rooms] ([id_room], [id_classification], [cost], [number_of_seats]) VALUES (5, 4, 7000.0000, 2)
INSERT [dbo].[rooms] ([id_room], [id_classification], [cost], [number_of_seats]) VALUES (7, 5, 10000.0000, 6)
INSERT [dbo].[rooms] ([id_room], [id_classification], [cost], [number_of_seats]) VALUES (8, 1, 10000.0000, 6)
INSERT [dbo].[rooms] ([id_room], [id_classification], [cost], [number_of_seats]) VALUES (9, 2, 1000.0000, 3)
SET IDENTITY_INSERT [dbo].[rooms] OFF
GO
SET IDENTITY_INSERT [dbo].[staff] ON 

INSERT [dbo].[staff] ([id_employee], [id_user], [name], [surname], [patronymic], [date_of_birth], [post], [phone], [status]) VALUES (1, 5, N'dmitry', N'ffdggf', NULL, CAST(N'1995-12-05' AS Date), N'ceo', N'2121322', 1)
INSERT [dbo].[staff] ([id_employee], [id_user], [name], [surname], [patronymic], [date_of_birth], [post], [phone], [status]) VALUES (2, 6, N'max', N'ruur', N'ryuyta', CAST(N'1965-11-22' AS Date), N'manager', N'5235490549', 0)
INSERT [dbo].[staff] ([id_employee], [id_user], [name], [surname], [patronymic], [date_of_birth], [post], [phone], [status]) VALUES (3, 7, N'andrew', N'troport', N'ddsdsdsa', CAST(N'1966-05-01' AS Date), N'programmer', N'509304304', 1)
INSERT [dbo].[staff] ([id_employee], [id_user], [name], [surname], [patronymic], [date_of_birth], [post], [phone], [status]) VALUES (4, 8, N'salman', N'hgooe', NULL, CAST(N'1966-05-01' AS Date), N'Admin', N'+29112232', 1)
INSERT [dbo].[staff] ([id_employee], [id_user], [name], [surname], [patronymic], [date_of_birth], [post], [phone], [status]) VALUES (5, 11, N'sdd', N'sdds', N'ssdds', CAST(N'2022-12-11' AS Date), N'Программист', N'+78382783223', 1)
INSERT [dbo].[staff] ([id_employee], [id_user], [name], [surname], [patronymic], [date_of_birth], [post], [phone], [status]) VALUES (8, 15, N'vlad', N'korzh', N'dsdlssd', CAST(N'2013-12-06' AS Date), N'Proggramer', N'+79213223322', 1)
INSERT [dbo].[staff] ([id_employee], [id_user], [name], [surname], [patronymic], [date_of_birth], [post], [phone], [status]) VALUES (9, 16, N'Dmitry', N'Kol', N'Denisovich', CAST(N'1995-02-27' AS Date), N'cleaner', N'+79233232323', 1)
INSERT [dbo].[staff] ([id_employee], [id_user], [name], [surname], [patronymic], [date_of_birth], [post], [phone], [status]) VALUES (10, 18, N'ddssdsdsd', N'dsdssd', N'sdsdsd', CAST(N'2010-12-10' AS Date), N'sdsddssd', N'+73293982933', 1)
SET IDENTITY_INSERT [dbo].[staff] OFF
GO
SET IDENTITY_INSERT [dbo].[users] ON 

INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (1, N'sol', N'2')
INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (2, N'soup', N'4994934')
INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (3, N'dslds', N'21212121')
INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (4, N'rirei', N'44930934')
INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (5, N'asd', N'540-056')
INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (6, N'topodf', N'881323')
INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (7, N'erlkler', N'4334')
INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (8, N'admin', N'$2a$10$sTAM90svibSS0n3Y0zBk4uF/BTDAd5BG60aiAHvtbSNolKKPE/TuC')
INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (9, N'[er[p', N'434')
INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (10, N'qwqwq', N'3221')
INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (11, N'asosdlsd', N'1234')
INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (12, N'asosdlsd', N'1234')
INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (13, N'lol', N'35554')
INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (15, N'cool', N'$2a$10$rMyEfsydAbfgJ5NZ4L8cweW7O9VhDzR49lCLUK6FOSanqzhuEBId6')
INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (16, N'sold', N'$2a$10$5/SfKEHeJ4.pr8KQZFgVDOxUFAL5aGLWAAaZ5zMXJR88skYYvtcGS')
INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (17, N'cust', N'$2a$10$.ALnXIocp1ktgs0XnyDd4eC2aPPEPIelkddKvEtybT5.1hOa5qkn6')
INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (18, N'sddsdssdsd', N'$2a$10$g4hUUGdP/5WaMo0l7hrJTO74pkIlmaLv2/dGG3fFdkttJasRxCukK')
INSERT [dbo].[users] ([id_user], [login], [password]) VALUES (20, N'kuk', N'$2a$10$3VtRDD510itu/IZZeTrKJO2ycAmwW0tmKkDL7MzpGhFMwNBJVweri')
SET IDENTITY_INSERT [dbo].[users] OFF
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
ALTER TABLE [dbo].[customers]  WITH CHECK ADD  CONSTRAINT [FK_customers_users] FOREIGN KEY([id_user])
REFERENCES [dbo].[users] ([id_user])
GO
ALTER TABLE [dbo].[customers] CHECK CONSTRAINT [FK_customers_users]
GO
ALTER TABLE [dbo].[rooms]  WITH CHECK ADD  CONSTRAINT [FK_rooms_classification_rooms] FOREIGN KEY([id_classification])
REFERENCES [dbo].[classification_rooms] ([id_classification])
GO
ALTER TABLE [dbo].[rooms] CHECK CONSTRAINT [FK_rooms_classification_rooms]
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
ALTER TABLE [dbo].[staff]  WITH CHECK ADD  CONSTRAINT [FK_staff_users] FOREIGN KEY([id_user])
REFERENCES [dbo].[users] ([id_user])
GO
ALTER TABLE [dbo].[staff] CHECK CONSTRAINT [FK_staff_users]
GO
ALTER TABLE [dbo].[booking]  WITH CHECK ADD  CONSTRAINT [CK_Dates] CHECK  (([booking].[check_out]>[booking].[check_in]))
GO
ALTER TABLE [dbo].[booking] CHECK CONSTRAINT [CK_Dates]
GO
/****** Object:  StoredProcedure [dbo].[AddBooking]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[AddBooking] (@ID_CUSTOMER as int, @IN as date, @OUT as date, @ID_ROOM as int)
AS
INSERT  booking(id_customer, check_in, check_out, id_room, total)
	VALUES (@ID_CUSTOMER, @IN, @OUT, @ID_ROOM, DATEDIFF(DAY, @IN, @OUT) * (SELECT rooms.cost FROM rooms
															   WHERE rooms.id_room = @ID_ROOM))
GO
/****** Object:  StoredProcedure [dbo].[AddCustomer]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[AddCustomer] (@Login as varchar(50), @Password as varchar(255), @Name as nvarchar(50), @Surname as nvarchar(50), @Pantronymic as nvarchar(50), 
						  @Birhdate as date, @Passport as varchar(10), @Phone varchar(20), @Status as bit)
AS
INSERT users(login, password) 
	VALUES (@Login, @Password)

INSERT customers(id_user, name, surname, patronymic, date_of_birth, phone, passport, status)
	VALUES ((SELECT users.id_user FROM users
			WHERE users.login = @Login AND users.password = @Password), @Name, @Surname, @Pantronymic, @Birhdate, @Phone, @Passport, 1)
GO
/****** Object:  StoredProcedure [dbo].[AddRoom]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[AddRoom] (@ID_CLASSIFICATION as int, @Cost as money, @NumSeats as int)
AS
INSERT rooms(id_classification, cost, number_of_seats)
	VALUES (@ID_CLASSIFICATION, @Cost, @NumSeats)
GO
/****** Object:  StoredProcedure [dbo].[AddRoomClassification]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[AddRoomClassification] (@NameClassification as nvarchar(50))
AS
INSERT classification_rooms(classification_name) 
	VALUES (@NameClassification)
GO
/****** Object:  StoredProcedure [dbo].[AddServices]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[AddServices] (@Name as nvarchar(50), @Cost as money, @Description as nvarchar(100))
AS
INSERT services(service_name, cost, description)
	VALUES (@Name, @Cost, @Description)
GO
/****** Object:  StoredProcedure [dbo].[AddStaff]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[AddStaff] (@Login as varchar(50), @Password as varchar(255), @Name as nvarchar(50), @Surname as nvarchar(50), @Pantronymic as nvarchar(50), 
						   @Birhdate as date, @Post as nvarchar(50), @Phone varchar(20), @Status as bit)
AS
INSERT users(login, password) 
	VALUES (@Login, @Password)

INSERT staff(id_user, name, surname, patronymic, date_of_birth, post, phone, status)
	VALUES ((SELECT users.id_user FROM users
			WHERE users.login = @Login AND users.password = @Password), @Name, @Surname, @Pantronymic, @Birhdate, @Post, @Phone, 1)
GO
/****** Object:  StoredProcedure [dbo].[BuyService]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[BuyService](@ID_BOOKING as int, @ID_SERVICE as int)
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
/****** Object:  StoredProcedure [dbo].[ChangeCustomerStatus]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ChangeCustomerStatus] (@ID as int, @Status as bit)
AS
UPDATE customers set status = @Status
WHERE customers.id_customer = @ID
GO
/****** Object:  StoredProcedure [dbo].[ChangeDataStaff]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ChangeDataStaff](@ID_STAFF as int, @Name as nvarchar(50), @Surname as nvarchar(50), @Pantronymic as nvarchar(50), 
						  @Birthdate as date, @Phone as varchar(20))
AS
UPDATE staff set staff.name = @Name, staff.surname = @Surname, staff.patronymic = @Pantronymic, staff.date_of_birth = @Birthdate, staff.phone = @Phone
WHERE staff.id_employee = @ID_STAFF
GO
/****** Object:  StoredProcedure [dbo].[ChangeDataСustomer]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ChangeDataСustomer](@ID_CUSTOMER as int, @Name as nvarchar(50), @Surname as nvarchar(50), @Pantronymic as nvarchar(50), 
						  @Birthdate as date, @Phone as varchar(20), @Passport as varchar(10))
AS
UPDATE customers set customers.name = @Name, customers.surname = @Surname, customers.patronymic = @Pantronymic, customers.date_of_birth = @Birthdate,
									  customers.passport = @Passport, customers.phone = @Phone
WHERE customers.id_customer = @ID_CUSTOMER
GO
/****** Object:  StoredProcedure [dbo].[ChangeDatesInBooking]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ChangeDatesInBooking] (@ID_BOOKING as int, @IN as date, @OUT as date)
AS
UPDATE booking set check_in = @IN, check_out = @OUT, total = DATEDIFF(DAY, @IN, @OUT) * (SELECT rooms.cost FROM rooms
															   WHERE rooms.id_room = booking.id_room) 
WHERE booking.id_booking = @ID_BOOKING
GO
/****** Object:  StoredProcedure [dbo].[ChangePassword]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ChangePassword] (@Login as varchar(50), @Password as varchar(255))
AS
UPDATE users set users.password = @Password
WHERE users.login = @Login
GO
/****** Object:  StoredProcedure [dbo].[ChangeRooms]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ChangeRooms] (@ID_ROOM as int, @ID_CLASSIFICATION as int, @Cost as money, @NumSeats as int)
AS
UPDATE rooms set id_classification = @ID_CLASSIFICATION, cost = @Cost, number_of_seats = @NumSeats
WHERE rooms.id_room = @ID_ROOM
GO
/****** Object:  StoredProcedure [dbo].[ChangeServices]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ChangeServices] (@ID_SERVICE as int, @Name as nvarchar(50), @Cost as money, @Description as nvarchar(50))
AS
UPDATE services set service_name = @Name, cost = @Cost, description = @Description 
WHERE services.id_service = @ID_SERVICE
GO
/****** Object:  StoredProcedure [dbo].[ChangeStaffStatus]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ChangeStaffStatus] (@ID as int, @Status as bit)
AS
UPDATE staff set status = @Status
WHERE staff.id_employee = @ID
GO
/****** Object:  StoredProcedure [dbo].[ShowAdditionalServices]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ShowAdditionalServices]
AS
SELECT services.service_name AS Name, services.cost AS Cost, services.description AS Description FROM services
GO
/****** Object:  StoredProcedure [dbo].[ShowAdditionalServicesByNumberOfBooking]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ShowAdditionalServicesByNumberOfBooking] (@ID_BOOKING as int)
AS
SELECT service_name AS Name, service_util.cost AS Cost, services.description AS description FROM services
JOIN service_util ON services.id_service = service_util.id_service
WHERE service_util.id_booking = @ID_BOOKING
GO
/****** Object:  StoredProcedure [dbo].[ShowAvailableRooms]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ShowAvailableRooms] (@DATE1 as date, @DATE2 as date)
AS
SELECT *
FROM rooms r
WHERE r.id_room NOT IN 
      (SELECT b.id_room
       FROM booking as b
       INNER JOIN booking on b.id_booking = r.id_room
       WHERE b.id_room = 2
       AND ((b.check_in >= @DATE1 AND b.check_in < @DATE2) OR (b.check_out > @DATE1 AND b.check_out < @DATE2) OR (b.check_in < @DATE1 AND b.check_out > @DATE2)))
GO
/****** Object:  StoredProcedure [dbo].[ShowBooking]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ShowBooking]
AS
SELECT booking.check_in AS 'IN', booking.check_out AS OUT, booking.total AS Total FROM booking
GO
/****** Object:  StoredProcedure [dbo].[ShowBookingID]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ShowBookingID] (@ID_BOOKING as int)
AS
SELECT booking.check_in AS 'IN', booking.check_out AS OUT, booking.total AS Total FROM booking
WHERE booking.id_booking = @ID_BOOKING
GO
/****** Object:  StoredProcedure [dbo].[ShowClassifications]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ShowClassifications]
AS
SELECT classification_rooms.classification_name AS Name FROM classification_rooms
GO
/****** Object:  StoredProcedure [dbo].[ShowClient]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ShowClient] (@Login as varchar(10))
AS
SELECT customers.name, customers.surname, customers.patronymic, customers.passport, customers.phone FROM customers
JOIN users ON users.id_user = customers.id_user
WHERE users.login = @Login
GO
/****** Object:  StoredProcedure [dbo].[ShowClientBooking]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ShowClientBooking] (@ID_CUSTOMER as int)
AS
SELECT booking.check_in AS 'IN', booking.check_out AS OUT, booking.total AS Total FROM booking
JOIN customers ON booking.id_customer = customers.id_customer
WHERE customers.id_customer = @ID_CUSTOMER
GO
/****** Object:  StoredProcedure [dbo].[ShowRooms]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ShowRooms]
AS
SELECT rooms.cost as Cost, rooms.number_of_seats as Number_Of_Seats FROM rooms
GO
/****** Object:  StoredProcedure [dbo].[ShowStaff]    Script Date: 10.02.2023 13:52:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ShowStaff] (@Login as varchar(50))
AS
SELECT staff.name, staff.surname, staff.patronymic, staff.date_of_birth, staff.phone, staff.post FROM staff
JOIN users ON users.id_user = staff.id_user
WHERE users.login = @Login
GO
USE [master]
GO
ALTER DATABASE [Hotel] SET  READ_WRITE 
GO
