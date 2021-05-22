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
       <artifactId>idempotent-spring-boot-starter</artifactId>
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

* @Idempotent
    - timeout: Idempotent check timeout. default 1 seconds
    - timeUnit: Timeout time unit. default seconds
    - tips: 请求失败，重复请求接口，请稍后再试！
    - delKey: End of execution, delete key, default not delete

* Api interface

  ```java
  @RestController
  @RequestMapping("/order")
  public class OrderController {
  
    @Idempotent
    @GetMapping("/create")
    public String create() {
        return "ok";
    }
  
    @Idempotent(timeout = 3L, timeUnit = TimeUnit.SECONDS, delKey = false)
    @GetMapping("/update")
    public String update() {
        return "ok";
    }
  
  }
  ```

* The request interface

  ```bash
  curl http://ip:port/order/create
  ```

### Screenshots

![get-token](src/main/resources/img/get-token.png)

![token-header](src/main/resources/img/token-header.png)

![repeat-token](src/main/resources/img/repeat-token.png)

![no-token](src/main/resources/img/no-token.png)

[![](https://jitpack.io/v/727474430/idempotent-spring-boot-starter.svg)](https://jitpack.io/#727474430/idempotent-spring-boot-starter)
