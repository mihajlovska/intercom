# Intercom Home Test

In this application you can see the list of customers within 100km from Dublin. The list contains objects with userId and name populated for each customer. Additionaly, customers are sorted ascending by userId.

## Prerequisites

In order to run the application, you need to download and install Docker for desktop. Please download it from the following link: `https://www.docker.com/products/docker-desktop`
Additionaly, maybe the docker software will ask you to install WSL2 Linux kernel if it cannot find any on your computer. Please, follow the link which will be shown in the dialog and install it as it is required.

## Getting started

To run the application, you need to follow the next steps:
- Start Docker by double click on the icon or open it via windows explorer. It will show message when the docker is started.
- Open command prompt and navigate to the root of the project repository.
- Execute the following command to build the application: 
	```bash
	docker build -t intercom-app .
	```
- Please wait until the application is built. 
- Execute the following command to run the application:
	```bash
	docker run --rm -p 9000:8080 intercom-app
	```
- Open browser and navigate to loclalhost:9000 .
- You should see the result.

### Testing

Testing is done using JUnit5 platform and Mockito library. To run the application, you need to follow the next commands:
- Start Docker by double click on the icon or open it via windows explorer. It will show message when the docker is started.
- Open command prompt and navigate to the root of the project repository.
- Execute the following command to run the tests: 
	```bash
	docker build -f Dockerfile.test -t intercom-app-test .
	```
- In the command line you can see that the tests are executed. Additionaly, you can see when each test is started and if it is passed or failed.


## Architecture

The application at the moment is designed following n-tier architecture design and Single responsibility pattern.

## Folder Structure

The whole application is located in `src`. In the `src/main/java/intercom/home/test` package you can see  a list of folders:

- **controller** - folder where API endpoints are defined. 
- **model** - folder where all of the models for API request and response are included. 
- **service** - this folder includes the whole application logic. It contains interfaces and their implementations.
- **utils** - the common classes or constants that are used all over the application. They are divided in two subfolders:
	- common - which contains constants like ErrorMessages and other constants that are used in the application.
	- properties - which contains property files for reading values for varibales defined in the `application.properties` file.
	
Tests for the application are located in `src/test/java/intercom/home/test`. In this directory you can see the following folders:
- **common** - common constants that are used for testing purposes.
- **integration** - in this folder you can find the integration tests for the application. Here you can find the tests for the API endpoints which are defined in the application controller folder.
- **unit** - unit tests for all the service methods that are defined in the application service folder.
