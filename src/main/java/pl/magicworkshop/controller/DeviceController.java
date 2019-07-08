package pl.magicworkshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.magicworkshop.exceptions.CategoryNotFoundException;
import pl.magicworkshop.exceptions.DeviceNotFoundException;
import pl.magicworkshop.model.Category;
import pl.magicworkshop.model.Device;
import pl.magicworkshop.repository.CategoryRepository;
import pl.magicworkshop.repository.DeviceRepository;

import static pl.magicworkshop.util.PrintText.println;

import java.beans.Transient;
import java.util.Optional;
import java.util.Scanner;

@Service
public class DeviceController {

    private Scanner scanner;
    private DeviceRepository deviceRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public DeviceController(Scanner scanner, DeviceRepository deviceRepository, CategoryRepository categoryRepository) {
        this.scanner = scanner;
        this.deviceRepository = deviceRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void createDevice(){
        try {
            Device device = readDevice();
            deviceRepository.save(device);
            System.out.println("Dodano urządzenie");
            System.out.println(device);
        } catch (CategoryNotFoundException e) {
            System.err.println("Urządzenia nie dodano. " + e.getMessage());
        }
    }

    private Device readDevice() {
        Device device = new Device();
        println("Nazwa urządzenia: ");
        device.setName(scanner.nextLine());
        println("Opis urządzenia: ");
        device.setDescription(scanner.nextLine());
        println("Ilość urządzeń(sztuk): ");
        device.setCount(scanner.nextInt());
        scanner.nextLine();
        println("Kategoria urządzenia(podaj id):");
        long categoryId = scanner.nextLong();
        Optional<Category> category = categoryRepository.findById(categoryId);
        scanner.nextLine();
        category.ifPresentOrElse(device::setCategory, () -> {
            throw new CategoryNotFoundException("Kategoria o podanym ID nie istnieje");
        });

        return device;
    }


    public void deleteDevice() {
        System.out.println("Podaj id urządzenia do usunięcia: ");
        long id = scanner.nextLong();
        deviceRepository.findById(id).ifPresentOrElse(deviceRepository::delete,
                () -> {
                    throw new DeviceNotFoundException("Nie udało się usunąć urządzenia o podanym id");
                });
    }

    public Device findDeviceById(Long id) {
        Device device = deviceRepository.findById(id).orElseThrow(() -> {
            throw new DeviceNotFoundException("Nie znaleziono urządzenia o wskazanym id");
        });
        return device;
    }

}
