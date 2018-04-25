package demo.api;

import org.junit.jupiter.api.Test;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * used in stack overflow answer. Change carefully.
 */
class DroolDeclarationTest extends DroolsBaseTest {

    @Test
    void whenRulesFired_thenDeclaredTypeInstanceCreated() {
        //GIVEN
        FactType myType = knowledgeBase.getFactType("mypackage", "MyType");
        //WHEN
        kieSession.fireAllRules();
        //THEN
        verifyInstancePresence(kieSession, myType);
    }

    @Test
    void whenInsertingDeclaredType_thenInsertedInstanceChanges() throws IllegalAccessException, InstantiationException {
        //GIVEN
        FactType myType = knowledgeBase.getFactType("mypackage", "MyType");
        Object typeToInsert = myType.newInstance();
        myType.set(typeToInsert, "myField", "request");
        kieSession.insert(typeToInsert);
        //WHEN
        kieSession.fireAllRules();
        //THEN
        verifyInstancePresence(kieSession, myType);
    }

    private void verifyInstancePresence(KieSession kieSession, FactType myType) {

        Boolean found = false;
        for (Object o : kieSession.getObjects()) {
            if (o.getClass().getName().equals("mypackage.MyType")) {
                if ("response".equals(myType.get(o, "myField"))) {
                    found = true;
                }
            }
        }

        assertThat(found).isTrue();
    }

}
