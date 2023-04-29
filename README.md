
# Requirements:

Used technical stack:

* Maven 3.9.1
* Java OpenJDK 20
* Docker version 20.10.24

# Building the app:
First of all clone the repository locally:
git clone https://github.com/moncifbf/2023-DEV1-056.git

Open terminal and run: cd 2023-DEV1-056/development-books

Run the following command to build and package our application: mvn clean package

Make sure the target folder is generated, and containing the correct files

Run the following docker command to build our docker image locally: docker build -t developmentbooks .

Run the following command and check if our image was generated correctly: docker images

Run the following command to start our spring-boot application locally: docker run -p 9090:8080 developmentbooks

On the browser go to: http://localhost:9090/

This means that our spring-boot app is up but we can't found any resource on the base path. 

Start Postman and import the collection given on folder Postman. 

Try executing the given requests to test the application.
