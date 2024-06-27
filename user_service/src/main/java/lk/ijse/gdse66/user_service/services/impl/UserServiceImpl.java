package lk.ijse.gdse66.user_service.services.impl;

import lk.ijse.gdse66.user_service.dto.CredentialDTO;
import lk.ijse.gdse66.user_service.dto.UserDTO;
import lk.ijse.gdse66.user_service.entity.UserEntity;
import lk.ijse.gdse66.user_service.repo.UserRepo;
import lk.ijse.gdse66.user_service.services.UserService;
import lk.ijse.gdse66.user_service.services.exceptions.DuplicateRecordException;
import lk.ijse.gdse66.user_service.services.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepo userRepo, ModelMapper mapper) {
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    @Override
    public String registerUser(UserDTO userDTO) {
        if(userRepo.existsById(userDTO.getEmail())){
            throw new DuplicateRecordException("User already exists: " + userDTO.getEmail());
        }else{
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepo.save(mapper.map(userDTO, UserEntity.class));
            return "user registered successfully";
        }
    }

    @Override
    public String updateUser(UserDTO userDTO) {
        if(userRepo.existsById(userDTO.getEmail())){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepo.save(mapper.map(userDTO, UserEntity.class));
            return "user details updated successfully";
        }else{
            throw new NotFoundException("No such user to update | User Email: " + userDTO.getEmail());
        }
    }

    @Override
    public boolean checkCredentials(CredentialDTO credentialDTO) {
        UserEntity userEntity = userRepo.findById(credentialDTO.getEmail()).orElseThrow(() ->
                new NotFoundException("No such user | User Email: " + credentialDTO.getEmail()));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(credentialDTO.getPassword(), userEntity.getPassword());
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        return mapper.map(userRepo.findById(email) , UserDTO.class);
    }

}
