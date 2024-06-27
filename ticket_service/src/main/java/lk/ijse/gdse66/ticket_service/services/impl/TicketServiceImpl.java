package lk.ijse.gdse66.ticket_service.services.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse66.ticket_service.dto.TicketDTO;
import lk.ijse.gdse66.ticket_service.entity.TicketEntity;
import lk.ijse.gdse66.ticket_service.entity.VehicleEntity;
import lk.ijse.gdse66.ticket_service.repo.TicketRepo;
import lk.ijse.gdse66.ticket_service.services.TicketService;
import lk.ijse.gdse66.ticket_service.services.exceptions.DuplicateRecordException;
import lk.ijse.gdse66.ticket_service.services.exceptions.NotFoundException;
import lk.ijse.gdse66.ticket_service.util.TicketStatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final ModelMapper mapper;
    private final TicketRepo ticketRepo;

    public TicketServiceImpl(ModelMapper mapper, TicketRepo ticketRepo) {
        this.mapper = mapper;
        this.ticketRepo = ticketRepo;
    }

    @Override
    public String createTicket(TicketDTO ticketDTO) {
        if(ticketRepo.existsById(ticketDTO.getTicketId())){
            throw new DuplicateRecordException("Ticket already exists: " + ticketDTO.getTicketId());
        }else{
            ticketRepo.save(mapper.map(ticketDTO, TicketEntity.class));
            return "Ticket created successfully";
        }
    }

    @Transactional
    @Override
    public void updateTicketStatus(String ticket_id, TicketStatus status) {
        ticketRepo.updateTicketStatus(ticket_id, status);
    }

    @Override
    public TicketDTO getTicketDetails(String ticketId) {
        TicketEntity ticketEntity = ticketRepo.findByTicketId(ticketId);
        if (ticketEntity != null) {
            return mapper.map(ticketEntity, TicketDTO.class);
        } else {
            throw new NotFoundException("No such ticket | Ticket ID: " + ticketId);
        }
    }
}
