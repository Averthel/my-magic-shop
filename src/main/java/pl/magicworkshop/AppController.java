package pl.magicworkshop;


import org.springframework.stereotype.Controller;
import pl.magicworkshop.controller.DeviceController;
import pl.magicworkshop.model.Device;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static pl.magicworkshop.util.PrintText.println;

@Controller
public class AppController {
    DeviceController dc;
    private Scanner scanner;

    AppController(Scanner scanner, DeviceController deviceController){
        this.scanner = scanner;
        this.dc = deviceController;
    }

    List<Device> devices = new ArrayList<>();

   public void mainLoop(){
       printOptions();

   }


    private void printOptions() {
        println("Opcje:");
        Arrays.stream(Options.values()).forEach(System.out::println);
    }



}
