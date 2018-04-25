package demo.api;

import demo.model.Bank;
import demo.model.Loan;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LoanRuleTest extends DroolsBaseTest {

    @Test
    void whenLoanIsValid_thenAllowAndProcess() {
        //GIVEN
        Loan loan = new Loan();
        loan.setAmount(999);
        Bank bank = new Bank();
        kieSession.insert(bank);
        kieSession.insert(loan);
        //WHEN
        kieSession.fireAllRules();
        //THEN
        assertThat(loan.getAllowed()).isTrue();
        assertThat(bank.getProcessed()).extracting("amount").containsExactly(999);
        assertThat(bank.getRequired()).isEmpty();
    }

    @Test
    void whenLoanIsInvalid_thenRefuse() {
        //GIVEN
        Loan loan = new Loan();
        loan.setAmount(1001);
        kieSession.insert(loan);
        //WHEN
        kieSession.fireAllRules();
        //THEN
        assertThat(loan.getAllowed()).isFalse();
    }

}
