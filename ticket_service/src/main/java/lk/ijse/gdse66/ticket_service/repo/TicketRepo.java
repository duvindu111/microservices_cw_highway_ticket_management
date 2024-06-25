package lk.ijse.gdse66.ticket_service.repo;

import lk.ijse.gdse66.ticket_service.entity.TicketEntity;
import lk.ijse.gdse66.ticket_service.util.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepo extends JpaRepository<TicketEntity,String> {

    @Modifying
    @Query(value = "UPDATE ticket SET status=?2 WHERE ticket_id=?1",nativeQuery = true)
    void updateTicketStatus(String ticketId, TicketStatus status);
}
