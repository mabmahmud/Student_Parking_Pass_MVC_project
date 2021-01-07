# Student_Parking_Pass_MVC_project

                  ****MVC Web Application for Student Parking Pass****

Requirenent1:

Instructions 
Background
Parking passes are provided to students each year.  To get a parking pass the student must provide their first name, last name, license plate (ie. AB 234), and their college program (ie. CIS, CNET, Accounting, etc).  You have been asked to create a program which will allow the college to track their parking passes.  

Code Provided
ParkingPassDAO.java
ParkingPass.java
ParkingPassBO.java
createDatabase.sql


Web Application (Spring MVC)
Create a web application to track the parking passes.
Must be a Spring based MVC application. 
The user should have the ability to show and add parking passes to the database.   
The user should also have the ability to see all of the parking passes.  
At a minimum, the user should have the ability to add and show the campers using the system. 
Update Functionality

For full marks the update should allow the user to choose to update a students parking pass information by using a hyperlink from the show option. 
The show would give a list of the parking passes and there would be a link/button available for each row to bring the user to the edit view.  
Additional requirements

The source code should be checked into Bitbucket in your assignments repository.
Application should use Thymeleaf to provide a layout to the application.
DATABASE to be used!
See attached createDatabase.sql file.  This must be used!
Note that a primary key has been added to the table in the script below. 
Starting Code

Requirement 2

Create a REST web service in your application which will allow another application to get a list 
of parking passes from your web application from the previous assignments.  The web application 
should provide a json array String containing the information.

Also submit a java application which invokes the web service.    The java application should then display the names, license plates, and programs for all parking passes to the console.
