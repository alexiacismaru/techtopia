package architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import java.util.Arrays;
import java.util.Collection;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "be.kdg.prog6.boundedContextA")
public class CodeStyleTest {
    @ArchTest
    static final ArchRule weShouldUseListOfRule = noClasses().should().callMethod(Arrays.class, "asList", Object[].class)
            .orShould().callMethod(Collection.class, "singletonList", Object.class)
            .because("List.of() is the better function for creating lists (cfr immutability).");
}
