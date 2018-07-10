package ar.edu.unq.desapp.grupoi.jobs;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CleanExpiredReserves extends JobDefinition {

  public static final String JOB_NAME = "CLEAN_EXIPIRED_RESERVES";
  private final ReservationRepository repository;

  @Autowired
  public CleanExpiredReserves(ReservationRepository reservationRepository, TaskScheduler scheduler) {
    super(scheduler);
    this.repository = reservationRepository;
  }

  @Override
  public String jobName() {
    return JOB_NAME;
  }

  @Override
  public Runnable task() {
    LocalDateTime halfAndHourAgo = LocalDateTime.now().minusMinutes(30);
    return () -> {
      this.repository.findReservationsWithClientRecivedCarSate().stream()
          .filter(reservation -> reservation.getLastUpdate().isBefore(halfAndHourAgo))
          .forEach(Reservation::expire);
    };
  }
}
