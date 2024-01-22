package testcontainers;

import be.kdg.prog6.boundedContextA.domain.Guest;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Testcontainers
@ContextConfiguration(initializers = AbstractBaseTest.DataSourceInitializer.class)
public abstract class AbstractBaseTest {
    @BeforeEach
    void resetDatabase() {
        Guest john = new Guest("John", "Smith", "john.smith@gmail.com", "0183891382", 12);
        Guest jessy = new Guest("Jessy", "Smith", "jess@yahoo.com", "02847298234", 35);
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

