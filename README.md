BankSystem is a project based on integrating microservices commonly found in banking institutions, such as accounts, cards, and loans services. Microservices provide independent, scalable systems, which highlights accessability and versatility to modify each service. In addition, this project is structured around the Twelve Factor App principle to build cloud-native applications.

![image](https://user-images.githubusercontent.com/84652073/139151373-0f619865-b41d-4f5e-b329-e5915720093b.png)

BankSystem adopts the 1st Factor of "CodeBase" by identifying the amount of microservices to create and adjusting the size per microservices. Specifically, this project divides microservices based on the essential services given in a banking institution. Therefore, services are divided up into appropriate sizes for teams to scale upon each microservice systems.

This project utilizes the 2nd Factor "Dependencies" through implementing microservices. Microservices accomplishes loose-coupling, which allows each service to operate independently from one another. Moreover, each microservice is containerized with Docker, which increases easability in regards to portability and deployment.

In addition, BankSystem follows the 3rd Factor "Configuration" to store configuration files separate from the microservices. This is achieved through using Spring Cloud Configuration server, which promotes configuration management. For example, in order to simplify the transition to different configuration environments (development and production), Spring Cloud Configuration server allows us to register different application properties for the corresponding environments which illlustrates the convenience of switching between mulitple environments without having to change the Docker image.

The 4th Factor "Backing Services" is achieved through using Eureka Server. Each microservice connects and registers with Eureka Server (centralized registration service), which allows other microservices to locate one another inside of a network. As a result, newly created instances will be detected and Eureka Server will instantiate heartbeat mechanisms to check the health status of each microservice. This is beneficial because it shows whether or not a microservice is operating effectively and if it needs to be scaled up or down. Through utilizing Feign Client, a cache is created from the user's requests, and will act as a load balancer to redirect requests towards their respective responses.

The 5th Facotr
