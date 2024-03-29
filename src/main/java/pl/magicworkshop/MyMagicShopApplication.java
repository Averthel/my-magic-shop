package pl.magicworkshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


import java.util.Scanner;


@SpringBootApplication
public class MyMagicShopApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MyMagicShopApplication.class, args);


        AppController appController = ctx.getBean(AppController.class);
        appController.mainLoop();

    }

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

}
