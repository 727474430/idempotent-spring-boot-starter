### A custom spring boot starter project.

  Mainly provides interface idempotent check function, A simple annotation '@Idempotent' can complete the verification.

  [中文注释](./README_CN.md) 


### How to use

1. **git clone https://github.com/727474430/idempotent-spring-boot-starter.git**

2. **cd idempotent-spring-boot-starter**

3. **mvn install**

4. **Introducing dependencies in spring boot project that need to be used**

   ```xml
   <dependency>
       <groupId>com.raindrop</groupId>
       <artifactId>idempotent-logging-spring-boot-starter</artifactId>
       <version>1.0.RELEASE</version>
   </dependency>
   ```

5. **Add following attributes in application.properties/application.yml**

   * idempotent.enable=true

     Whether to open the interface idempotent check function. default is **"false"** not open. select true is open.

   * idempotent.tokenStorage=redis

     Need to using storage type for token. default is **"memory"**. support redis and memory storage type. If you use 
     redis, you need to add redis related configuration.

   * idempotent.tokenHeader=token

     Pass the key of the Token request header. default key is **"token"**.



### Example

* application.properties

  ```properties
  idempotent.enable=true
  idempotent.tokenStorage=redis
  idempotent.tokenHeader=token
  spring.redis.host=localhost
  spring.redis.port=6379
  spring.redis.password=
  spring.redis.database=0
  ```

* application.yml

  ```yaml
  idempotent:
    enable: true
    token-storage: redis
    token-header: token
  spring:
    redis:
      host: localhost
      port: 6379
      password:
      database: 0
  ```


### Screenshots

![](src/main/resources/img/properties.png)

![](src/main/resources/img/anno.png)

![](src/main/resources/img/logging.png)


[![](https://jitpack.io/v/727474430/idempotent-spring-boot-starter.svg)](https://jitpack.io/#727474430/idempotent-spring-boot-starter)
