package pl.magicworkshop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "device")
public class Device {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 2048)
    private String description;
    private int count;
    private double price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToMany
    @JoinTable(name = "device_customers", joinColumns = {@JoinColumn(name = "device_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="customer_id", referencedColumnName="id")})
    private List<Customer> customers = new ArrayList<>();

   public Device(){}

    public void addCustomer(Customer customer) {
        customers.add(customer);
        customer.getRentDevices().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Urządzenie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Device)) return false;

        Device device = (Device) o;

        if (getCount() != device.getCount()) return false;
        if (Double.compare(device.getPrice(), getPrice()) != 0) return false;
        if (getId() != null ? !getId().equals(device.getId()) : device.getId() != null) return false;
        if (getName() != null ? !getName().equals(device.getName()) : device.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(device.getDescription()) : device.getDescription() != null)
            return false;
        if (getCategory() != null ? !getCategory().equals(device.getCategory()) : device.getCategory() != null)
            return false;
        return getCustomers() != null ? getCustomers().equals(device.getCustomers()) : device.getCustomers() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getCount();
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getCustomers() != null ? getCustomers().hashCode() : 0);
        return result;
    }
}
