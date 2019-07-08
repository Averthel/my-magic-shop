package pl.magicworkshop;


import org.springframework.stereotype.Controller;
import pl.magicworkshop.controller.CategoryController;
import pl.magicworkshop.controller.CustomerController;
import pl.magicworkshop.controller.DeviceController;
import pl.magicworkshop.controller.RentController;
import pl.magicworkshop.exceptions.InvalidOptionException;


import java.util.Arrays;
import java.util.Scanner;

import static pl.magicworkshop.util.PrintText.println;

@Controller
public class AppController {

    private Scanner scanner;
    private CategoryController categoryController;
    private DeviceController deviceController;
    private CustomerController customerController;
    private RentController rentController;

    public AppController(Scanner scanner, CategoryController categoryController, DeviceController deviceController, RentController rentController, CustomerController customerController){
        this.scanner = scanner;
        this.categoryController = categoryController;
        this.deviceController = deviceController;
        this.rentController = rentController;
        this.customerController = customerController;
    }

   public void mainLoop(){
        Options option;
        do{
            printOptions();
            option = readOption();
            executeOption(option);
        }while(option != Options.EXIT);


   }

    private void printOptions() {
        println("Opcje:");
        Arrays.stream(Options.values()).forEach(System.out::println);
    }

    private Options readOption(){
        boolean isCorrect = false;
        Options option = null;
        while(!isCorrect){
            System.out.println("Podaj numer opcji: ");
            int optionId= scanner.nextInt();
            scanner.nextLine();
            try{
                option = Options.numberToOption(optionId);
                isCorrect = true;
            }catch(InvalidOptionException e){
                System.err.println(e.getMessage());
            }
        }
        return option;
    }

    private void executeOption(Options option){
        switch (option) {
            case ADD_DEVICE:
                deviceController.createDevice();
                break;
            case ADD_CATEGORY:
                categoryController.addCategory();
                break;
            case ADD_CUSTOMER:
                customerController.addCustomer();
                break;
            case RENT:
                rentController.rentDeviceToCustomer();
                break;
            case REMOVE_DIVCE:
                deviceController.deleteDevice();
                break;
            case REMOVE_CATEGORY:
                categoryController.removeCategory();
                break;
            case REMOVE_CUSTOMER:
                customerController.removeCustomer();
                break;
            case EXIT:
                closeApp();
        }

    }

    private void closeApp(){
        scanner.close();
        System.out.println("Bye bye");
    }



}
