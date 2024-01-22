package testcontainers;

import be.kdg.prog6.boundedcontextB.domain.Attraction;
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
        Attraction attraction = new Attraction("name", "location", "description", "image", "ageGroup", "tags");
        Attraction attraction2 = new Attraction("name2", "location2", "description2", "image2", "ageGroup2", "tags2");
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

