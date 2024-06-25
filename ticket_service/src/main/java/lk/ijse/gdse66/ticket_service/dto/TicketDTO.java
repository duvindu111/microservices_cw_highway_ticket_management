package lk.ijse.gdse66.ticket_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lk.ijse.gdse66.ticket_service.util.TicketStatus;
import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TicketDTO {
    @NotBlank(message = "Ticket ID is mandatory")
    @Pattern(regexp = "^T\\d+$", message = "Invalid Ticket ID. format should be T1, T2, T3, ...")
    private String ticketId;
    @NotBlank(message = "User email is mandatory")
    @Email(message = "Invalid email address")
    private String userEmail;
    @NotNull(message = "Date is mandatory")
    private Date date;
    @NotNull(message = "Time is mandatory")
    private Time time;
    @NotBlank(message = "Vehicle ID is mandatory")
    private String vehicleId;
    @NotNull(message = "Status is mandatory")
    private TicketStatus status;
    @NotBlank(message = "Location is mandatory")
    private String location;
}
