package demo.api;

import demo.model.Loan;
import demo.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonTest extends DroolsBaseTest {

    @Test
    void whenPersonAgeIs17_thenCanCode() {
        //GIVEN
        Person person = new Person();
        person.setAge(17);
        kieSession.insert(person);
        //WHEN
        kieSession.fireAllRules();
        //THEN
        Assertions.assertTrue(person.getCanCode());
    }

}
