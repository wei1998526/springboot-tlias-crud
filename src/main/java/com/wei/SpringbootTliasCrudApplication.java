package com.wei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan  //开启对servlet组件的支持
@SpringBootApplication
public class SpringbootTliasCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTliasCrudApplication.class, args);
    }

}
