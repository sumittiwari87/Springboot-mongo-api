# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/gradle-plugin/reference/html/#build-image)
# Spring boot API with Mongo DB 

This project is a small POC for exposing all REST API for a bookstore for adding/updating/viewing/deleting book details. Mongo DB has been used a database. 

# Features!

  - add a book 
  - update a book 
  - detele a book 
  - get a book details 
  - get all book details
  


> The  goal for this project to create two seperate images using docker IMAGE file.
> Create two seperate container, container one for REST API and second one for mongodb.
> Deploy this project on your local machine by running a docker-compose.yaml file. 
> The docker-compose yaml file has all details to expose mongo db and creating a link as a service.
> Publishable as-is, all the DB configuartions are in application propertiers file. 
> We can put this also in enviornment variable in docker. 
> Later, deploy a ECS cluster with a EC2 instance and add a task-definition to the cluster to run a service.




### Installation
The Spring boot API requires JAVA 8 JRE to run the app. 
To build the docker image, verify that you have docker installed in your machine. 

To run the app simply build the DOCKERFILE first by running below command 

```sh
$ docker build --build-arg JAR_FILE=build/libs/*.jar -t springboot-api .
```
 Now you have to run this image to named springboot-api and link this to mongo db container which we will run directly from `docker hub `

Find the docker-compose file in `src/main/resources` folder 

navidate to this folder and run a docker-compose command 

```sh
$ cd src/main/resources 
$ docker-compose up
```
### Deployement on AWS using ECR, ECS and ALB 
Now you know how to run your application locally, lets move the app running in a ECS, one EC2 cluster. 

##### ECR - pushing your SpringBoot-API IMAGE  
  - Install AWS CLI command line tool  
  - Configure your IAM user with access key in your local machine for CLI commands  
  - Create a ECR registry for the `springboot-api` image.  
  - Follow the steps and push the image on ECR. 
 
##### ECS Cluster and define TASK-DEFINITION 
Not going into more details, hope you will create a Cluster, define a task definition with two containers. 
1. The application container named `springboot-api` and in the `NETWORK SETTINGS` place inside `Links` add this value like `mymongodb:mymongodb`. So the application will check the container which is exposing as `mymongodb`. In the port mapping add Port mappings
`Host port` keep it empty(because we will use alb),  `Container port` 8080. 
2. Now lets create another container in same task-definition named `mymongodb` and in the `NETWORK SETTINGS` place inside `Hostnames` add this value as `mymongodb`, which is the string name for expsoing it service. In the port mapping add Port mappings
`Host port` as 27017,  `Container port` as 27017. 

##### ECS Service 
Craete a ECS service for the newly defined task definition with an application load balancer. 


### Test 
Verify the deployment by navigating to your server address in your preferred browser.

```sh
localhost:8080 # for local deployment 
ALB DNS name # for AWS deployment
```
License
----

www.mycloudknowledge.com


