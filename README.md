project1

1. Database queries are in the resources package
2. Use Spring Data Jpa (create interface to implement JpaRepository)
3. basic work flow: repository communicate with database,
                    service class use repository interface to retrieve/save entities and convert to/from pojo
                    controller use service class to process request and response
4. /student endpoint: GET, POST, PUT, DELETE operations
5. /registration endpoint: GET, POST operations
6. exception handled in controller


project 2
1. RestTemplate Bean defined in the config class
2. use restTemplate.getForObject() to fetch data from other website and map data to object
                    