# Anime Api
This is the api made for the class dataprocessing.

Credits: _Robbie Eijkelkamp 4572637_

* [User Guide for setup](https://github.com/Madlyaza/anime-java#user-guide-for-setup)
* [User Guide for using](https://github.com/Madlyaza/anime-java#user-guide-for-using)
 
## User Guide for setup
This is the user guide for setting up the API and front end consuming program.
For the purposes of keeping this installation simple we are going to assume you have intellij installed. If not refer to their website for more information and guidance on how to install it.

### 1.1 Cloning the repository
First you must clone or download the repository, you can save the code wherever you would like we suggest you put it in an empty folder to not create unforeseen problems.

### 1.2 Install Java
Make sure that Java is installed on your mahcine and updated since we will need ti for the next step, if not you can find downloads [here](https://www.java.com/nl/download/manual.jsp). 

### 1.3 Install Tomcat
I use [Tomcat 9.0.56](https://tomcat.apache.org/download-90.cgi) as a means of running the API, it is required for running the API.

### 1.4 Change Application.properties
To make sure the database connection is correct you must edit the Application.properties file. This file should be located inside ```src/main/resources```. If it is not here you can manually create it.

* **database.username**: This is the login name for Xampp, you can change this to whatever your Xampp uses.
* **database.password**: Same as with the username, change it or make a new user in Xampp.

```properties
database.username=[Username]
database.password=[Password]
```

### 1.5 Creating the database
We will create the database by using Xampp. Execute the following query inside ```localhost/phpmyadmin```.

```mysql
CREATE DATABASE animedatabase;
```

### 1.6 Running the API
First open intellij and select as your project org. Then build the application to ensure no errors are encountered.

Next navigate to the run anything window in the top right and execute ```mvn clean verify cargo:run```

After at max a few minutes of startup messages it should be running and you can navigate to the application under the normal ```localhost:8080```

### 1.7 Running the front end

## User Guide for using
This part will cover how to use the API.






