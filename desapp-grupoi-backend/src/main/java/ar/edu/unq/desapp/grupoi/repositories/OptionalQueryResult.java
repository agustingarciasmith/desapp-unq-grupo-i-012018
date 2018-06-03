package ar.edu.unq.desapp.grupoi.repositories;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Optional;

public class OptionalQueryResult {

  public static <T> Optional<T> of(Query query) {
    try {
      return Optional.of((T) query.getSingleResult());
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

}
