package lk.ijse.gdse66.vehicle_service.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse66.vehicle_service.dto.VehicleDTO;
import lk.ijse.gdse66.vehicle_service.services.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/register")
    public String registerVehicle(@Valid @RequestBody VehicleDTO vehicleDTO){
        return vehicleService.registerVehicle(vehicleDTO);
    }

    @PatchMapping("/update")
    public String updateUserDetails(@Valid @RequestBody VehicleDTO vehicleDTO){
        return vehicleService.updateVehicle(vehicleDTO);
    }

    @GetMapping("/get/{lp_num}")
    public VehicleDTO getVehicleDetails(@PathVariable("lp_num") String lp_num){
        return vehicleService.getVehicle(lp_num);
    }

    @GetMapping("/getAll")
    public List<VehicleDTO> getAllVehicleDetails(){
        return vehicleService.getAllVehicleDetails();
    }

}
