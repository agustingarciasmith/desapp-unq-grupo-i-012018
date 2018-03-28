package ar.edu.unq.desapp.grupoi.model;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import ar.edu.unq.desapp.grupoi.model.errors.CanNotExtract;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

@RunWith(JavaSpecRunner.class)
public class AccountTests extends JavaSpec<TestContext>{
  @Override
  public void define() {
    it("new account contains 0 credits", () -> {
      Account account = new Account();
      assertThat(account.getCredit()).isEqualTo(0);
    });

    it("add credit to account", () -> {
      Account account = new Account();
      account.addCredit(20);
      assertThat(account.getCredit()).isEqualTo(20);
    });

    it("remove credit from account", () -> {
      Account account = new Account();
      account.addCredit(20);
      account.removeCredit(5);
      assertThat(account.getCredit()).isEqualTo(15);
    });

    it("cant overdraft the account", () -> {
      Account account = new Account();
      account.addCredit(10);
      try {
        account.removeCredit(11);
        failBecauseExceptionWasNotThrown(CanNotExtract.class);
      }catch (CanNotExtract e) {
        assertThat(account.getCredit()).isEqualTo(10);
      }
    });
  }
}
