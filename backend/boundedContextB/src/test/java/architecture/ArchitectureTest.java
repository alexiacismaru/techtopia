package architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "be.kdg.prog6", importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchitectureTest {
    private static final String DOMAIN_LAYER = "be.kdg.prog6.boundedContextB.domain..";
    private static final String ADAPTER_LAYER = "be.kdg.prog6.boundedContextB.adapter..";
    private static final String CORE_LAYER = "be.kdg.prog6.boundedContextB.core..";
    private static final String PORT_LAYER = "be.kdg.prog6.boundedContextB.port..";

    @ArchTest
    static final ArchRule domainShouldNotDependOnAnyOtherLayerRule =
            noClasses().that().resideInAPackage(DOMAIN_LAYER)
                    .should().dependOnClassesThat().resideInAnyPackage(
                            ADAPTER_LAYER,
                            CORE_LAYER,
                            PORT_LAYER
                    ).because("This conflicts with hexagonal architecture: Domain should not depend on another layers.");

    @Test
    void givenApplicationClasses_thenNoLayerViolationsShouldExist() {
        JavaClasses jc = new ClassFileImporter().importPackages("be.kdg.prog6.boundedContextA");
        Architectures.LayeredArchitecture architecture = layeredArchitecture().consideringOnlyDependenciesInLayers()
                .layer("DRIVING_ADAPTERS").definedBy("..adapters.in..")
                .layer("DRIVING_PORTS").definedBy("..ports.in..")
                .layer("core").definedBy("..core..")
                .whereLayer("DRIVING_ADAPTERS").mayNotBeAccessedByAnyLayer()
                .whereLayer("DRIVING_PORTS").mayOnlyBeAccessedByLayers("DRIVING_ADAPTERS", "CORE");
        architecture.check(jc);
    }
}
