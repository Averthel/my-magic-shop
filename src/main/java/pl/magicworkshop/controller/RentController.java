package pl.magicworkshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.magicworkshop.exceptions.RentException;
import pl.magicworkshop.model.Customer;
import pl.magicworkshop.model.Device;
import pl.magicworkshop.repository.CustomerRepository;
import pl.magicworkshop.repository.DeviceRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Scanner;

@Service
public class RentController {

    private Scanner scanner;
    private DeviceRepository deviceRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public RentController(Scanner scanner, DeviceRepository deviceRepository, CustomerRepository customerRepository) {
        this.scanner = scanner;
        this.deviceRepository = deviceRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void rentDeviceToCustomer() {
        try {
            rent();
        } catch (RentException e) {
            System.err.println(e.getMessage());
        }
    }


    private void rent() {
        System.out.println("Podaj id klienta: ");
        Optional<Customer> customer = customerRepository.findById(scanner.nextLong());
        System.out.println("Podaj id urządzenia, które chcesz wypożyczyć: ");
        Optional<Device> device = deviceRepository.findById(scanner.nextLong());
        if (customer.isPresent())
            device.ifPresentOrElse(dev -> {
                if (dev.getCount() > dev.getCustomers().size())
                    dev.addCustomer(customer.get());
                else
                    throw new RentException("Brak wolnych urządzeń o wskazanym ID");
            }, () -> {
                throw new RentException("Brak urządzenia o wskazanym ID");
            });

    }
}

