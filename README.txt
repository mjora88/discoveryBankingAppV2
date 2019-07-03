

****************************************************************************************************************
################################################# HOW TO RUN ###################################################
****************************************************************************************************************

This is a Spring Boot application, which uses default configurations. This executes on the Console. So you need not launch the browser.
Data is persisted using H2 in memory database, which resets each time you restart the application.

Assumption made is that input would always take numerical values.

This needs Java 1.8.


TO BUILD, exec the following:

>mvn clean install

TO RUN, exec the following:

>mvn spring-boot:run

These commands are to be executed inside the root directory where there is pom.xml

TO run the sql files , 424.sql and 425.sql you need to go to  http://localhost:8080/h2-console/
and on the JDBC URL please add the URL "jdbc:h2:mem:banksystem" that you can find on appplication.properties