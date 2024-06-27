package lk.ijse.gdse66.user_service.services;

import lk.ijse.gdse66.user_service.dto.CredentialDTO;
import lk.ijse.gdse66.user_service.dto.UserDTO;

public interface UserService {

    String registerUser(UserDTO userDTO);

    String updateUser(UserDTO userDTO);

    boolean checkCredentials(CredentialDTO credentialDTO);

    UserDTO findUserByEmail(String email);
}
