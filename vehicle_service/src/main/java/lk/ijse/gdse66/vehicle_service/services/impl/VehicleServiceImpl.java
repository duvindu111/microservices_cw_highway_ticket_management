package lk.ijse.gdse66.vehicle_service.services.impl;

import lk.ijse.gdse66.vehicle_service.dto.VehicleDTO;
import lk.ijse.gdse66.vehicle_service.entity.UserEntity;
import lk.ijse.gdse66.vehicle_service.entity.VehicleEntity;
import lk.ijse.gdse66.vehicle_service.repo.VehicleRepo;
import lk.ijse.gdse66.vehicle_service.services.VehicleService;
import lk.ijse.gdse66.vehicle_service.services.exceptions.DuplicateRecordException;
import lk.ijse.gdse66.vehicle_service.services.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepo vehicleRepo;
    private final ModelMapper mapper;
    private final RestTemplate restTemplate;

    public VehicleServiceImpl(VehicleRepo vehicleRepo, ModelMapper mapper, RestTemplate restTemplate) {
        this.vehicleRepo = vehicleRepo;
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    @Override
    public String registerVehicle(VehicleDTO vehicleDTO) {
        if(vehicleRepo.existsByLicensePlateNum(vehicleDTO.getLicensePlateNum())){
            throw new DuplicateRecordException("Vehicle already exists - Registration number: " + vehicleDTO.getLicensePlateNum());
        }else{
            VehicleEntity vehicleEntity = mapper.map(vehicleDTO, VehicleEntity.class);
            UserEntity userEntity = restTemplate.getForObject("http://USER-SERVICE/api/v1/user/find_by_email/" + vehicleDTO.getUserEmail(), UserEntity.class);
            if (userEntity == null) {
                throw new NotFoundException("User not found with email: " + vehicleDTO.getUserEmail());
            }
            vehicleEntity.setUser(userEntity);
            vehicleRepo.save(vehicleEntity);
            return "vehicle registered successfully";
        }
    }

    @Override
    public String updateVehicle(VehicleDTO vehicleDTO) {
        if(vehicleRepo.existsByLicensePlateNum(vehicleDTO.getLicensePlateNum())){
            VehicleEntity vehicleEntity = mapper.map(vehicleDTO, VehicleEntity.class);
            UserEntity userEntity = restTemplate.getForObject("http://USER-SERVICE/api/v1/user/find_by_email/" + vehicleDTO.getUserEmail(), UserEntity.class);
            if (userEntity == null) {
                throw new NotFoundException("User not found with email: " + vehicleDTO.getUserEmail());
            }
            vehicleEntity.setUser(userEntity);

            int vehicle_id = vehicleRepo.findIdByLicensePlateNum(vehicleDTO.getLicensePlateNum());
            vehicleEntity.setVehicleId(vehicle_id);

            vehicleRepo.save(vehicleEntity);
            return "vehicle details updated successfully";
        }else{
            throw new NotFoundException("No such vehicle to update | Registration number: " + vehicleDTO.getLicensePlateNum());
        }
    }

    @Override
    public VehicleDTO getVehicle(String licensePlateNum) {
        VehicleEntity vehicleEntity = vehicleRepo.findByLicensePlateNum(licensePlateNum);
        if (vehicleEntity != null) {
            return mapper.map(vehicleEntity, VehicleDTO.class);
        } else {
            throw new NotFoundException("No such vehicle | Registration number: " + licensePlateNum);
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicleDetails() {
        return vehicleRepo.findAll().stream().map(vehicle -> mapper.map(vehicle, VehicleDTO.class)).toList();
    }
}
