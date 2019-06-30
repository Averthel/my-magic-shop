package pl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.magicworkshop.model.Category;
import pl.magicworkshop.model.Customer;
import pl.magicworkshop.model.Device;
import pl.magicworkshop.repository.DeviceRepository;


@SpringBootApplication
public class MyMagicShopApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MyMagicShopApplication.class, args);

        DeviceRepository deviceRepository = ctx.getBean(DeviceRepository.class);

        Device device = new Device();
        device.setName("Wiertarka udarowa");
        device.setDescription("Wiertarka udarowa o dużej mocy 3000W z zestawem wierteł w komplecie");
        device.setPrice(80);
        device.setCount(5);

        Category category = new Category();
        category.setName("Elektronarzędzia");
        category.setDescription("Wiertarki, szlifierki, młoty udarowe i inne elektronarzędzia");

        Customer customer = new Customer();
        customer.setFirstName("Jan");
        customer.setLastName("Kowalski");
        customer.setPesel("90908765123");
        customer.setIdNumber("ABC678123");

        deviceRepository.save(device);

    }

}
