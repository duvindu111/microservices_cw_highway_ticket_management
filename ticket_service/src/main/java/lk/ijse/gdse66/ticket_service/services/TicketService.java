package lk.ijse.gdse66.ticket_service.services;

import lk.ijse.gdse66.ticket_service.dto.TicketDTO;
import lk.ijse.gdse66.ticket_service.entity.TicketEntity;
import lk.ijse.gdse66.ticket_service.util.TicketStatus;

public interface TicketService {

    String createTicket(TicketDTO ticketDTO);

    void updateTicketStatus(String ticket_id, TicketStatus status);

    TicketDTO getTicketDetails(String ticketId);
}
