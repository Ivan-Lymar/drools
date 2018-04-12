package demo.api;

import demo.model.Bank;
import demo.model.Loan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertTrue(loan.getAllowed());
        Assertions.assertEquals(bank.getProcessed().get(0).getAmount(), 999);
        Assertions.assertTrue(bank.getRequired().isEmpty());
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
        Assertions.assertFalse(loan.getAllowed());
    }

}
