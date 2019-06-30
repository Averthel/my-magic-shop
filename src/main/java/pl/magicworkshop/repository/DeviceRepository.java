package pl.magicworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.magicworkshop.model.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}