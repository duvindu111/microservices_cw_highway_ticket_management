package lk.ijse.gdse66.vehicle_service.repo;

import lk.ijse.gdse66.vehicle_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, String>{

    UserEntity findByEmail(String email);
}
