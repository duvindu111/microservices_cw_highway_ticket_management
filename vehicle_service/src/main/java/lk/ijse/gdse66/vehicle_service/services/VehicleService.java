package lk.ijse.gdse66.vehicle_service.services;

import lk.ijse.gdse66.vehicle_service.dto.VehicleDTO;
import lk.ijse.gdse66.vehicle_service.entity.VehicleEntity;

import java.util.List;

public interface VehicleService {

    String registerVehicle(VehicleDTO vehicleDTO);

    String updateVehicle(VehicleDTO vehicleDTO);

    VehicleDTO getVehicle(String licensePlateNum);

    List<VehicleDTO> getAllVehicleDetails();
}
