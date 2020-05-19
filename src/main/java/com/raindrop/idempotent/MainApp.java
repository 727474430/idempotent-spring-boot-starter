package com.raindrop.idempotent;

import com.raindrop.idempotent.anno.Idempotent;
import com.raindrop.idempotent.util.IdempotentTokenUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    @GetMapping("/token")
    public String token() {
        return IdempotentTokenUtils.tokenGenerate();
    }

    @Idempotent
    @GetMapping("/check")
    public String check() {
        return "ok";
    }
}
