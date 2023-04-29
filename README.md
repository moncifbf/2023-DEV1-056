
# Requirements:

Used technical stack:

* Maven 3.9.1

![image](https://user-images.githubusercontent.com/80068219/235328375-7b5bb16d-0590-4cea-8567-a1dc4171674b.png)

* Java OpenJDK 20

![image](https://user-images.githubusercontent.com/80068219/235328385-28931bf0-4e41-45d6-803a-0aa4eaddc8be.png)

* Docker version 20.10.24

![image](https://user-images.githubusercontent.com/80068219/235328400-83622a2f-a65b-4f40-afa7-0f776aff516a.png)

* Postman APP

Download page: https://www.postman.com/downloads/

# Building the app:
First of all clone the repository locally:
* git clone https://github.com/moncifbf/2023-DEV1-056.git

![image](https://user-images.githubusercontent.com/80068219/235328415-bca8678b-c1e8-4b9e-b5c8-5fdb997b9003.png)

* Open terminal and run: cd 2023-DEV1-056/development-books

![image](https://user-images.githubusercontent.com/80068219/235328435-69e39a95-3e18-4394-885f-08f82a0883f9.png)

* Run the following command to build and package our application: mvn clean package
Make sure that all tests passed successfully

![image](https://user-images.githubusercontent.com/80068219/235328458-f21e9329-325a-42e0-9465-f5032af504d6.png)

* Make sure the target folder is generated, and containing the correct files

![image](https://user-images.githubusercontent.com/80068219/235328491-f873cd69-5fdf-4d7d-87d2-aa69a796bf40.png)

* Run the following docker command to build our docker image locally: docker build -t developmentbooks .

![image](https://user-images.githubusercontent.com/80068219/235328516-0a9a8e0b-85c6-4841-ba71-4d3b30311ede.png)

* Run the following command and check if our image was generated correctly: docker images

![image](https://user-images.githubusercontent.com/80068219/235328525-72ddc1fe-9cd1-48b2-9f9b-5cea8b0abb60.png)

# Testing the APP:

* Run the following command to start our spring-boot application locally: docker run -p 9090:8080 developmentbooks

![image](https://user-images.githubusercontent.com/80068219/235328535-5a70ec29-81c1-4e06-875e-01a036a9341c.png)

* On the browser go to: http://localhost:9090/

![image](https://user-images.githubusercontent.com/80068219/235328560-92dbf424-6a86-4b13-84e9-c3d2242d13f8.png)

* This means that our spring-boot app is up but we can't found any resource on the base path. 

* Start Postman and import the collection given on folder Postman. 

![image](https://user-images.githubusercontent.com/80068219/235328583-34f2a9ee-23af-48dc-95d8-cf51a0f4c933.png)

* Try executing the given requests to test the application.

![image](https://user-images.githubusercontent.com/80068219/235328571-4e5b7a3e-a98d-4e75-a132-e316f7446416.png)

