package lk.ijse.gdse66.vehicle_service.repo;

import lk.ijse.gdse66.vehicle_service.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleRepo extends JpaRepository<VehicleEntity,String> {

    boolean existsByLicensePlateNum(String licensePlateNum);

    @Query(value = "SELECT vehicle_id FROM vehicle WHERE license_plate_num=?1",nativeQuery = true)
    int findIdByLicensePlateNum(String licensePlateNum);

    VehicleEntity findByLicensePlateNum(String licensePlateNum);
}
