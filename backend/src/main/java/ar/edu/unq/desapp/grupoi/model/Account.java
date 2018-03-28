package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.CanNotExtract;

public class Account {
  private Integer credit;

  public Account() {
    this.credit = 0;
  }

  public void addCredit(Integer creditAmount) {
    this.credit += creditAmount;
  }

  public Integer getCredit() {
    return credit;
  }

  public void removeCredit(Integer creditAmount) {
    validateEnoughCredit(creditAmount);
    this.credit -= creditAmount;
  }

  private void validateEnoughCredit(Integer creditAmount) {
    if(this.credit - creditAmount < 0) {
      throw new CanNotExtract(this.credit, creditAmount);
    }
  }
}
