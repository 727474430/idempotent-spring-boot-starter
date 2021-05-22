### 一个自定义 spring boot starter 项目

​ 主要提供接口幂等性校验功能，只需要通过一个注解 '@Idempotent' 即可完成校验。

### 如何使用.

1. **git clone <https://github.com/727474430/idempotent-spring-boot-starter.git>**

2. **cd idempotent-spring-boot-starter**

3. **mvn install**

4. **在需要使用的 SpringBoot 项目中引入依赖关系**

   ```xml
   <dependency>
       <groupId>com.raindrop</groupId>
       <artifactId>idempotent-spring-boot-starter</artifactId>
       <version>1.0.RELEASE</version>
   </dependency>
   ```

5. 在**application.properties/application.yml**文件中添加下列属性

    * idempotent.enable=true

      是否开启接口幂等性校验功能，默认为 **"false"** 不开启，选择 **true** 开启。

    * idempotent.tokenStorage=redis

      需要使用的 Token 存储类型，默认使用 **"memory"** 方式，支持 **"redis"** 和 **"memory"** 两种模式。如果使用 redis 模式， 你需要添加 redis 相关配置。

    * idempotent.tokenHeader=token

      通过请求头传递 token 的 key 名，默认 key 为 **"token"**

### 示例.

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

* API 接口

  ```java
  @RestController
  @RequestMapping("/order")
  public class OrderController {
  
    @Idempotent
    @GetMapping("/create")
    public String check() {
        return "ok";
    }
  
    @Idempotent(timeout = 3L, timeUnit = TimeUnit.SECONDS, delKey = false)
    @GetMapping("/update")
    public String update() {
        return "ok";
    }
  
  }
  ```

* 请求接口
  ```bash
  curl http://ip:port/order/create
  ```

### 截图

![get-token](src/main/resources/img/get-token.png)

![token-header](src/main/resources/img/token-header.png)

![repeat-token](src/main/resources/img/repeat-token.png)

![no-token](src/main/resources/img/no-token.png)

[![](https://jitpack.io/v/727474430/idempotent-spring-boot-starter.svg)](https://jitpack.io/#727474430/idempotent-spring-boot-starter)
