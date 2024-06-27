package lk.ijse.gdse66.ticket_service.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse66.ticket_service.dto.TicketDTO;
import lk.ijse.gdse66.ticket_service.entity.TicketEntity;
import lk.ijse.gdse66.ticket_service.services.TicketService;
import lk.ijse.gdse66.ticket_service.util.TicketStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/create")
    public String createTicket(@Valid @RequestBody TicketDTO ticketDTO){
        return ticketService.createTicket(ticketDTO);
    }

    @PatchMapping("/update_status/{ticket_id}/{status}")
    public void updateTicketStatus(@PathVariable String ticket_id,@PathVariable TicketStatus status){
        ticketService.updateTicketStatus(ticket_id, status);
    }

    @GetMapping("/get/{ticket_id}")
    public TicketDTO getTicketDetails(@PathVariable("ticket_id") String ticket_id){
        return ticketService.getTicketDetails(ticket_id);
    }
}
