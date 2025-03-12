# Book Tracker Application

## Description

An application for tracking borrowed books along with their borrow duration and pages read. This application implements part of the Open Library API to facilitate book searching and data collection.

## Stack Information

- Java
- Spring Boot Framework
- MySQL Database

## Features

- Login and registration
- The below features are limited to logged in users:
  - Search for books by name using Open Library Search API
  - Borrow a book, selecting the duration and setting the initial pages read (Create borrowed book object)
  - View all of a user's borrowed books (Read all borrowed book objects)
  - View details on a user's individual borrowed books including their cover image (Read borrowed book object, additional call to Open Library for the book cover)
  - Extend the borrowing duration and adjust the number of pages read (Update borrowed book object)
  - Return the book, removing it from the user's borrowed list (Delete borrowed book object)

## Prerequisites

Before you begin, ensure you have met the following requirements:  

- JDK 11 or higher installed
- Maven for project building and dependencies management
- A MySQL database set up

## Recommendations

- Although this project can be run in different IDEs, the development and testing was conducted in Spring Tool Suite. If you run into difficulties due to using a different IDE, the development team may have a harder time assisting you.
- If you are using a different variant of SQL database, you will need to adjust the database connection via the application.properties file as well as correcting the dependencies in the pom.xml file to your chosen DB.
- If not already installed, MySQL Workbench will make viewing and monitoring the database much more straightforward.

## Usage

To run the Book Tracker App: 
1) Open the project in an IDE
2) If necessary, right click the pom.xml file to set up the backend as a Maven project
3) Double check the application.properties file and see if you need to make any adjustments to the database credentials
4) Run the Application class file
5) If Spring does not launch correctly, be sure your pom.xml file dependencies are up to date
12) You can now use the application

## Contact

If you have any questions, please open an issue or contact the project maintainers.  
Project Link: [Book Tracker](https://github.com/KiyokoNee/BookTrackerApp)  
Primary Contact: k.c.gearing@gmail.com

