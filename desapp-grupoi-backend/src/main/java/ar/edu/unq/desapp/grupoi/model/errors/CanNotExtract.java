package ar.edu.unq.desapp.grupoi.model.errors;

public class CanNotExtract extends RuntimeException {
  public CanNotExtract(Integer actualAmmount, Integer extractingAmount) {
    super(
        String.format(
            "Can't extract credit. Overdraft by $s",
            Integer.toString(extractingAmount - actualAmmount))
    );
  }
}
