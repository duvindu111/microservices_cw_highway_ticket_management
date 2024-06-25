package lk.ijse.gdse66.user_service.repo;

import lk.ijse.gdse66.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, String>{
}
