package pl.magicworkshop.controller;

import org.springframework.stereotype.Controller;
import pl.magicworkshop.Options;
import pl.magicworkshop.model.Customer;
import pl.magicworkshop.repository.CustomerRepository;

import java.util.Optional;
import java.util.Scanner;

@Controller
public class CustomerController {

    private Scanner scanner;
    private CustomerRepository customerRepository;

    public CustomerController(Scanner scanner, CustomerRepository customerRepository){
        this.scanner = scanner;
        this.customerRepository = customerRepository;
    }

    public void addCustomer(){
        Customer customer = readCustomer();
        customerRepository.save(customer);
        System.out.println("Dodano klienta");
        System.out.println(customer);
    }

    private Customer readCustomer(){
        Customer customer = new Customer();
        System.out.println("Podaj imię: ");
        customer.setFirstName(scanner.nextLine());
        System.out.println("Podaj nazwisko: ");
        customer.setLastName(scanner.nextLine());
        System.out.println("Podaj numer pesel:");
        customer.setPesel(scanner.nextLine());
        System.out.println("Podaj numer dowodu: ");
        customer.setIdNumber(scanner.nextLine());
        return customer;
    }

    public void removeCustomer(){
        System.out.println("Podaj id klienta, którego chcesz usunąć:");
        long customerToDelete = scanner.nextLong();
        Optional<Customer> customer = customerRepository.findById(customerToDelete);
        customer.ifPresentOrElse(customerRepository::delete, () -> System.out.println("Nie udało się usunąć klienta o podanym id"));
    }

    public Customer findCustomerByName(){
        Customer customer = new Customer();

        return customer;
    }
}
