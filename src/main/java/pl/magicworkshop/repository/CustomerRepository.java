package pl.magicworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.magicworkshop.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
