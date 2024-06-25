package lk.ijse.gdse66.vehicle_service.entity;

import jakarta.persistence.*;
import lk.ijse.gdse66.vehicle_service.util.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private int ticketId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issued_user_email", referencedColumnName = "email")
    private UserEntity user;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private Time time;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")
    private VehicleEntity vehicle;
    @Column(nullable = false)
    private TicketStatus status;
    @Column(nullable = false)
    private String location;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ticket")
    private List<PaymentEntity> payments = new ArrayList<>();

}
