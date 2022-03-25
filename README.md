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
Make sure that Java is installed on your machine and updated since we will need it for the next steps, if not you can find downloads [here](https://www.java.com/nl/download/manual.jsp). 

### 1.3 Install Xampp
Make sure that Xampp is installed on your machine and updated sinve we will need it to run the database.

### 1.4 Install Tomcat
I use [Tomcat 9.0.56](https://tomcat.apache.org/download-90.cgi) as a means of running the API, it is required for running the API.

### 1.5 Change Application.properties
To make sure the database connection is correct you must edit the Application.properties file. This file should be located inside ```src/main/resources```. If it is not here you can manually create it.

* **database.username**: This is the login name for Xampp, you can change this to whatever your Xampp uses.
* **database.password**: Same as with the username, change it or make a new user in Xampp.

```properties
database.username=[Username]
database.password=[Password]
```

### 1.6 Creating the database
We will create the database by using Xampp. Execute the following query inside ```localhost/phpmyadmin```.

```mysql
CREATE DATABASE animedatabase;
```

### 1.7 Running the API
First open intellij and select as your project org. Then build the application to ensure no errors are encountered.

Next navigate to the run anything window in the top right and execute ```mvn clean verify cargo:run```

After at max a few minutes of startup messages it should be running and you can navigate to the application under the normal ```localhost:8080```

### 1.8 Running the consumer website
Place the file called ```index.php``` into your Xampp htdocs folder. I advise you make a new folder inside the htdocs folder to keep it organized and then place the index.php in this new folder.

### 1.9 (OPTIONAL) Adding data to the database
If you want to populate the tables with some data you can follow this step.

First make sure you did step 1.6. If you have not launched the application then there database tables have not been created yet and thus the tables cannot be filled with data.

If you would like to add data to the database you can navigate to ```localhost/phpmyadmin``` select your database and click import at the top of the page.

Now select the file called ```animedatabase.sql``` which is located inside the SQL folder. Press go and the tables should be filled with some data.

## User Guide for using
This part will cover how to use the API.

### 2.1 Endpoints
The API has 4 different endpoints which all implement the normal CRUD operations.

The endpoints are:
* localhost:8080/animes
* localhost:8080/actors
* localhost:8080/studios
* localhost:8080/featured

These endpoints all implement these types of calls.
* GET ALL: localhost:8080/{endpoint}
* GET BY ID: localhost:8080/{endpoint}/{id}
* POST: localhost:8080/{endpoint}
* PUT: localhost:8080/{endpoint}/{id}
* DELETE: localhost:8080/{endpoint}/{id}

### 2.2 Headers

The required headers for the calls are:

* Accept: application/json
* Content-Type: application/json

OR

* Accept: application/xml
* Content-Type: application/xml

### 2.3 Example Posts and Puts

Below are example posts/puts for every endpoint for JSON and XML.

#### Anime
json
```json
{
    "studio": {
        "id": 1
    },
    "name": "A cool name",
    "critic_score": 1,
    "release_date": "01-01-2000"
}
```

xml
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<anime>
    <studio>
        <id>1</id>
    </studio>
    <name>A Cool Name</name>
    <critic_score>10</critic_score>
    <release_date>01-01-2000</release_date>
</anime>
```

#### Actor
json
```json
{
    "name": "Actor Name",
    "birth_date": "12-12-2000",
    "birth_place": "Emmen",
    "age": 20
}
```

xml
```xml
<actor>
    <name>Actor Name</name>
    <birth_date>12-12-2000</birth_date>
    <birth_place>Emmen</birth_place>
    <age>20</age>
</actor>
```

#### Studio
json
```json
{
    "name": "Studio Name",
    "founded": "12-12-2000",
    "headquarters": "Emmen",
    "type": "public"
}
```

xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<studio>
  <name>Studio Name</name>
  <founded>12-12-2000</founded>
  <headquarters>Emmen</headquarters>
  <type>private</type>
</studio>
```

#### Featured
json
```json
 {
    "anime": {
        "id": 1
    },
    "actor": {
        "id": 1
    }
}
```

xml
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<featuredIn>
    <actor>
        <id>1</id>
    </actor>
    <anime>
        <id>1</id>
    </anime>
</featuredIn>
```



