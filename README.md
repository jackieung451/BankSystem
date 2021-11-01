BankSystem is a project based on integrating microservices commonly found in banking institutions, such as accounts, cards, and loans services. Microservices provide independent, scalable systems, which highlights accessability and versatility to modify each service. Moreover, each microservice is containerized with Docker, which increases easability in regards to portability and deployment. In addition, this project is structured around the Twelve Factor App principle to build cloud-native applications.

![image](https://user-images.githubusercontent.com/84652073/139151373-0f619865-b41d-4f5e-b329-e5915720093b.png)

In addition, BankSystem stores configuration files separate from the microservices. This is achieved through using Spring Cloud Configuration server, which promotes configuration management. For example, in order to simplify the transition to different configuration environments (development and production), Spring Cloud Configuration server allows us to register different application properties for the corresponding environments which illlustrates the convenience of switching between mulitple environments without having to change the Docker image.

Each microservice connects and registers with Eureka Server (centralized registration service), which allows other microservices to locate one another inside of a network. As a result, newly created instances will be detected and Eureka Server will instantiate heartbeat mechanisms to check the health status of each microservice. This is beneficial because it shows whether or not a microservice is operating effectively and if it needs to be scaled up or down. Through utilizing Feign Client, a cache is created from the user's requests, and will act as a load balancer to redirect requests towards their respective responses.

![Screen Shot 2021-10-28 at 4 12 40 PM](https://user-images.githubusercontent.com/84652073/139631620-8ca62d19-6c14-49d9-9890-6a06e0524f20.png)

![Screen Shot 2021-10-29 at 5 47 14 PM](https://user-images.githubusercontent.com/84652073/139631698-dd2906c4-c4a2-4f10-aa00-1c8d148188c0.png)


Resiliency mechansims are implemented with the Circuit Breaker pattern. For example, if users are trying to access the cards microservices, it must go through the accounts and loans microservices first. However, if the cards microservices is not operating properly, then multiple incoming requests will not be fulfilled. Therefore, in order to mitigate casacading failures, I have implemented fault-tolerance and default-fallback systems to manage incoming requests.

Inbound traffic management is controlled through Spring Cloud Gateway. This microservice acts as a central policy enforcement point to ensure that incoming requests are routed to their appropriate microservices. Furthermore, Spring Cloud Gateway has predicates to check if the incoming requests fulfill a set of conditions. After passing the predicates, the incoming requests will be processed into pre-filters where tracing and logging can be implemented to help with debugging. Furthermore, Zipkin Server is utilized as a central logging location. This is enhanced with asynchronous logs because if the Zipkin Server is ever down, the log statements will remain intact in the queue.


![Screen Shot 2021-10-28 at 2 33 56 PM](https://user-images.githubusercontent.com/84652073/139631333-0c89f492-da35-43ec-a2e2-2776791b43dd.png)

![Screen Shot 2021-10-28 at 2 38 09 PM](https://user-images.githubusercontent.com/84652073/139631462-ba8a0bfc-b4f1-4fb4-8e1d-83049b0ffecf.png)


BankSystem's health and metrics are monitored through Grafana. Grafana's dashboards illustrate each microservice's performance, which can be used to interpolate how effective a microservice is operating and extract business decisions to scale the systems. Therefore, health and metrics can be benchmarked for future optimization of highly, scalable microservices systems.

![Screen Shot 2021-10-28 at 4 10 20 PM](https://user-images.githubusercontent.com/84652073/139631551-d92fc6da-84c2-412b-bd56-4ee45c2c1663.png)



In terms of deployment, I used Amazon Web Services' Elastic BeanStalk, along with AWS RDS (MySQL databse version) for my accounts, loans, and cards databases. I chose a relational database in order to simulate financial instutions' database systems. Generally, financial instituions utilize relational databases to keep track of the customer's account as well as joining queries to find the corresponding cards and loans associated with each account. Therefore, the integration of a cloud database provides flexibility to access information in a "on-demand" basis and can reduce costs of maintaining/updating native databases.

![Screen Shot 2021-10-29 at 7 10 08 PM](https://user-images.githubusercontent.com/84652073/139632082-2d67ec2d-5190-4ff3-bbb9-9d784f0ee967.png)

![Screen Shot 2021-10-29 at 7 10 19 PM](https://user-images.githubusercontent.com/84652073/139632098-1f23e377-cda5-4c71-98cf-8a9fa937c043.png)





