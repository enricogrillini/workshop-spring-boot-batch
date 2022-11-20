package it.eg.cookbook.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.eg.cookbook.config.ObjectMapperConfig;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.MessageFormat;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Classe comune per la gestione di Unit e Integration test.
 * Nota:
 * - gli Unit Test attivano il contesto spring per cui tutte le annotazioni Spring (ad es. @Value, @Autowired, etc.) sono attive
 * - gli Intetìgration Test NON attivano il contesto spring: l'applicazione gira su container
 */
@Slf4j
public abstract class AbstractTest {

    private TestInfo testInfo;

    protected static ObjectMapper objectMapper;

    @BeforeEach
    void setup(TestInfo testInfo) throws SQLException {
        this.testInfo = testInfo;

        objectMapper = ObjectMapperConfig.defaultObjectMapper();
    }

    public boolean isIntegrationTest() {
        return testInfo.getTestClass().get().getSimpleName().endsWith("IT");
    }

    public boolean isUnitTest() {
        return !isIntegrationTest();
    }

    public String getTestClass() {
        return testInfo.getTestClass().get().getSimpleName();
    }

    public String getTestClassBaseName() {
        String className = getTestClass();

        return className.endsWith("IT") ? className.substring(0, className.length() - 2) : className.substring(0, className.length() - 4);
    }

    public String getTestMethod() {
        return testInfo.getTestMethod().get().getName();
    }

    protected String readMockFile() {
        return readMockFile("");
    }

    protected String readMockFile(String fileNameSuffix) {
        return readFile("/mock/", fileNameSuffix);
    }

    protected <T> T readMockFile(Class<T> objectClass) throws JsonProcessingException {
        return readMockFile(objectClass, "");
    }

    protected <T> T readMockFile(Class<T> objectClass, String fileNameSuffix) throws JsonProcessingException {
        return objectMapper.readerFor(objectClass).readValue(readMockFile(fileNameSuffix));
    }

    protected String readExpectedFile() {
        return readExpectedFile("");
    }

    protected String readExpectedFile(String fileNameSuffix) {
        return readFile("/expected/", fileNameSuffix);
    }

    public String readFile(String type, String fileNameSuffix) {
        String fileNamePath = "src/test/resources/json/{0}{1}{2}{3}.json";
        File file = new File(MessageFormat.format(fileNamePath, getTestClassBaseName(), type, getTestMethod(), fileNameSuffix));

        return readFile(file);
    }

    public static String readFile(File readFile) {
        if (!readFile.isFile()) {
            Assertions.fail("File " + readFile + " non leggibile");
        }

        if (!readFile.exists()) {
            Assertions.fail("File " + readFile + " non trovato");
        }

        try (Reader reader = new FileReader(readFile, StandardCharsets.UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
            Assertions.fail("File " + readFile + " non leggibile", ex);
            return null;
        }
    }

    void assertJsonEquals(String expectedStr, String actualStr) {
        try {
            try {
                // "STRICT" pro fallimento test in presenza di campi aggiuntivi
                JSONAssert.assertEquals(expectedStr, actualStr, JSONCompareMode.STRICT);
            } catch (AssertionError ex) {
                String fileNamePath = "./target/actual/{0}/{1}.json";
                String className = getTestClass();
                String methodName = getTestMethod();

                try (Writer writer = new FileWriter(MessageFormat.format(fileNamePath, className, methodName), StandardCharsets.UTF_8)) {
                    FileCopyUtils.copy(actualStr, writer);
                }

                fail(ex);
            }
        } catch (JSONException | IOException ex) {
            log.error(ex.getMessage(), ex);
            fail(ex);
        }
    }


}
