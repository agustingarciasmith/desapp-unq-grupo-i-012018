package ar.edu.unq.desapp.grupoi.services.reservation;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.repositories.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    public ReservationRepository repository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ReservationServiceImpl(){}

    @Override
    public void create(Reservation reservation){
        repository.save(reservation); }

    @Override
    public List<Reservation> getAllAsOwner(Long ownerId) {
        return repository.getAllAsOwner(ownerId);
    }

    @Override
    public List<Reservation> getAllAsClient(Long clientId) {
        return repository.getAllAsClient(clientId);
    }

    @Override
    public Reservation getById(Long id) { return repository.load(id); }
}
