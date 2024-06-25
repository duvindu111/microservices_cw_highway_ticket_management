package lk.ijse.gdse66.payment_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDTO {
    @NotBlank(message = "Payment ID is mandatory")
    @Pattern(regexp = "^P\\d+$", message = "Invalid Payment ID. format should be P1, P2, P3, ...")
    private String paymentId;
    @NotBlank(message = "Ticket ID is mandatory")
    private String ticketId;
    @NotNull(message = "Date is mandatory")
    private Date date;
    @NotNull(message = "Time is mandatory")
    private Time time;
    @NotBlank(message = "Location is mandatory")
    private String location;
    @NotNull(message = "Amount is mandatory")
    private BigDecimal amount;
    @NotBlank(message = "User email is mandatory")
    private String userEmail;
}
