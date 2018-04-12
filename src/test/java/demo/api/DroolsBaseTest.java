package demo.api;

import demo.configuration.DroolsConfiguration;
import org.drools.core.impl.InternalKnowledgeBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DroolsConfiguration.class)
public class DroolsBaseTest {

    @Autowired
    InternalKnowledgeBase knowledgeBase;

    KieSession kieSession;

    @BeforeEach
    public void init() {
        kieSession = knowledgeBase.newKieSession();
    }

    @AfterEach
    public void teardown() {
        kieSession.dispose();
    }


}
