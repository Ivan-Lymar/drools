package demo.api;

import demo.model.Person;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
        assertThat(person.getCanCode()).isTrue();
    }

}
