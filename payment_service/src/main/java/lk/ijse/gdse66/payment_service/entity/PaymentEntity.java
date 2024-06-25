package lk.ijse.gdse66.payment_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @Column(name = "payment_id")
    private String paymentId;
    @OneToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "ticket_id")
    private TicketEntity ticket;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private Time time;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private double amount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private UserEntity user;


}
