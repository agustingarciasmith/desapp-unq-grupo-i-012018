package ar.edu.unq.desapp.grupoi.model.errors.model;

public class CanNotExtract extends RuntimeException {
  public CanNotExtract(Integer actualAmmount, Integer extractingAmount) {
    super(
        String.format(
            "Can't extract credit. Overdraft by $s",
            Integer.toString(extractingAmount - actualAmmount))
    );
  }
}
