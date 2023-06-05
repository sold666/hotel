# Course project <img src="https://github.com/devicons/devicon/blob/master/icons/microsoftsqlserver/microsoftsqlserver-plain-wordmark.svg" title="MS SQL" alt="MS SQL" width="70" height="70"/>

## Client application "Hotel"

- [Task](#Task)
- [Database diagram](#Database-diagram)
- [Database utils](#Database-utils)
- [How it works?](#How-it-works)
    - [Roles](#Roles)
    - [Functions](#Functions)
    - [Screenshots](#Screenshots)
- [Technologies](#Technologies)
- [Languages](#Languages)

### Task

Development of a multi-user automated organization management system. The object of automation is a "Hotel".

### Database diagram

![Diagram](https://user-images.githubusercontent.com/61206345/218069089-a4b49b73-6301-4acf-94d9-8c18a8f33d01.png)

### Database utils

The `db` folder stores all the necessary scripts to recreate the database

### How it works?

The program is a simple client application capable of accessing a database based on MS SQL Server.

#### Roles

There are 3 main roles:

- Hotel administrator
- Hotel employee
- Client

#### Functions

All the features of the listed roles are described below

**Views**

- List of numbers with classification (multi-table)
- List of customer and employee data (single-table)

**Stored procedures**

**To output:**

- Viewing numbers (single-table)
- View the classification of rooms (single-table)
- View ordered additional services by booking number (multi-tabular)
- Viewing additional services (single-table)
- View all bookings (single table)
- View all customer bookings (only your own) (multi-tabular)
- View information about a specific customer booking (single-tabular)
- Viewing personal data (client) (multi-tabular)
- Viewing personal data (employee) (multi-tabular)

**To add:**

- Adding numbers (multi-table)
- Adding additional services (single-table)
- Adding room classification (single-table)
- Adding booking record (multi-table)
- Registration of a new hotel employee (multi-table)
- Client registration (multi-tabular)
- Ordering an additional service (multi-table)

**To change:**

- Change of numbers (single-table)
- Change of additional services (single-table)
- Changing the arrival and departure dates of the client (multi-table)
- Change your password (single-table)
- Changing your personal data (single-table)

**For deletion (activation, deactivation):**

- Activation and deactivation of an employee without the ability to log in in the future (single-table)
- Activation and deactivation of the client without the possibility of authorization in the future (single-table)

#### Screenshots

Some screenshots from the app

![Main screen](https://github.com/sold666/hotel/assets/61206345/9a6c065e-94dd-485c-9ca0-5e568e4ab3bf)

![Admin panel](https://github.com/sold666/hotel/assets/61206345/f8ed08c2-3850-4dcd-b575-4b82df03fb60)

![Staff panel](https://github.com/sold666/hotel/assets/61206345/1520a305-af0d-4f83-a6af-099ae5ecf03b)

![Client panel](https://github.com/sold666/hotel/assets/61206345/b8125650-1d3f-4239-91bd-003d9a6060f1)

![Client registration](https://github.com/sold666/hotel/assets/61206345/988354d3-fada-41e4-ab6a-022fc5ad7107)

![Profile](https://github.com/sold666/hotel/assets/61206345/be1a1499-e0a8-476f-b977-a56d8cdeff20)

### Technologies

Technologies involved in the project

- JavaFX
- Log4j
- Spring Security
- Microsoft SQL Server
- JDBC - jTDC

### Languages

- Java 19.0.1
- CSS
- SQL

`Assembly Tool: Maven`
