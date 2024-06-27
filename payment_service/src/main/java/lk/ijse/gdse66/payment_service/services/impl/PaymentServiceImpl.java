package lk.ijse.gdse66.payment_service.services.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse66.payment_service.dto.PaymentDTO;
import lk.ijse.gdse66.payment_service.entity.PaymentEntity;
import lk.ijse.gdse66.payment_service.entity.TicketEntity;
import lk.ijse.gdse66.payment_service.entity.UserEntity;
import lk.ijse.gdse66.payment_service.repo.PaymentRepo;
import lk.ijse.gdse66.payment_service.repo.UserRepo;
import lk.ijse.gdse66.payment_service.services.PaymentService;
import lk.ijse.gdse66.payment_service.services.exceptions.DuplicateRecordException;
import lk.ijse.gdse66.payment_service.services.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;
    private final ModelMapper mapper;
    private final UserRepo userRepo;
    private RestTemplate restTemplate;

    public PaymentServiceImpl(PaymentRepo paymentRepo, ModelMapper mapper, UserRepo userRepo, RestTemplate restTemplate) {
        this.paymentRepo = paymentRepo;
        this.mapper = mapper;
        this.userRepo = userRepo;
        this.restTemplate = restTemplate;
    }

    @Transactional
    @Override
    public String payForTicket(PaymentDTO paymentDTO) {
        if(paymentRepo.existsById(paymentDTO.getPaymentId())){
            throw new DuplicateRecordException("Duplicate payment ID: " + paymentDTO.getPaymentId());
        }else{
            PaymentEntity paymentEntity = mapper.map(paymentDTO, PaymentEntity.class);

            try{
                TicketEntity ticketEntity = restTemplate.getForObject("http://TICKET-SERVICE/api/v1/ticket/get/" +
                        paymentDTO.getTicketId(), TicketEntity.class);
                if (ticketEntity == null) {
                    throw new NotFoundException("No such ticket found with ID: " + paymentDTO.getTicketId());
                }else{
                    paymentEntity.setTicket(ticketEntity);
                }
            }catch (Exception e){
                throw new NotFoundException("No such ticket found with ID: " + paymentDTO.getTicketId());
            }

            UserEntity userEntity = restTemplate.getForObject("http://USER-SERVICE/api/v1/user/find_by_email/" + paymentDTO.getUserEmail(), UserEntity.class);
            if (userEntity == null) {
                throw new NotFoundException("User not found with email: " + paymentDTO.getUserEmail());
            }else{
                paymentEntity.setUser(userEntity);
            }

            //1st part of the payment
            paymentRepo.save(paymentEntity);

            //2nd part of the payment
            restTemplate.patchForObject("http://TICKET-SERVICE/api/v1/ticket/update_status/"+
                    paymentDTO.getTicketId() +"/PAID", null , String.class);

            return "Payment successful";
        }
    }
}
