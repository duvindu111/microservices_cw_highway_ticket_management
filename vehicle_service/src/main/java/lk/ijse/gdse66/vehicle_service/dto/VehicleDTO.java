package lk.ijse.gdse66.vehicle_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VehicleDTO {
    @NotBlank(message = "license plate number is mandatory")
    private String licensePlateNum;
    @NotBlank(message = "vehicle type is mandatory")
    private String vehicleType;
    @NotBlank(message = "user id is mandatory")
    private String userEmail;
}
