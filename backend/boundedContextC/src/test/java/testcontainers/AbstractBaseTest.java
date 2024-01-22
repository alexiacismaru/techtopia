package testcontainers;

import be.kdg.prog6.boundedContextC.domain.Gate;
import be.kdg.prog6.boundedContextC.domain.GateAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Testcontainers
@ContextConfiguration(initializers = AbstractBaseTest.DataSourceInitializer.class)
public abstract class AbstractBaseTest {
    @BeforeEach
    void resetDatabase() {
        Gate gate1 = new Gate(GateAction.OUT);
        Gate gate2 = new Gate(GateAction.IN);
    }

    private static final MySQLContainer<?> DATABASE;

    static {
        DATABASE = new MySQLContainer<>("mysql:8.0.30");
        DATABASE.start();
    }

    @Test
    void containerIsRunning() {
        assertThat(DATABASE.isCreated()).isTrue();
        assertThat(DATABASE.isRunning()).isTrue();
    }

    public static class DataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.datasource.url=" + DATABASE.getJdbcUrl(),
                    "spring.datasource.username=" + DATABASE.getUsername(),
                    "spring.datasource.password=" + DATABASE.getPassword()
            );
        }
    }
}

