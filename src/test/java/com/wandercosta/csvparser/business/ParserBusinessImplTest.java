package com.wandercosta.csvparser.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.google.common.base.Charsets;
import java.io.File;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

/**
 * Class to test business class.
 *
 * @author Wander Costa (rwander@gmail.com)
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ParserBusinessImplTest {

    private static final String JSON_EXAMPLE_PATH = "jsonExample.json";
    private static final String JSON_EXAMPLE_RESULT_PATH = "jsonExampleResult";
    private static final String XML_EXAMPLE_PATH = "xmlExample.xml";
    private static final String XML_EXAMPLE_RESULT_PATH = "xmlExampleResult";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private ParserBusiness bs;

    @Test
    public void shouldParseJson() {
        String result = bs.parseJson(loadFile(JSON_EXAMPLE_PATH));

        assertEquals(1, StringUtils.countOccurrencesOf(result, "\n"));
        assertEquals(loadFile(JSON_EXAMPLE_RESULT_PATH), result);
    }

    @Test
    public void shouldThrowExceptionOnParseJsonWithNullInput() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Input JSON must not be null.");

        bs.parseJson(null);

        fail(); // Guarantees the test will break at the above line, and will be compared to the expected exception
    }

    @Test
    public void shouldThrowExceptionOnParseJsonWithEmptyInput() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Input JSON must not be empty.");

        bs.parseJson("");

        fail(); // Guarantees the test will break at the above line, and will be compared to the expected exception
    }

    @Test
    public void shouldParseXml() {
        String result = bs.parseXml(loadFile(XML_EXAMPLE_PATH));

        assertEquals(1, StringUtils.countOccurrencesOf(result, "\n"));
        assertEquals(loadFile(XML_EXAMPLE_RESULT_PATH), result);
    }

    @Test
    public void shouldThrowExceptionOnParseXmlWithNullInput() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Input XML must not be null.");

        bs.parseXml(null);

        fail(); // Guarantees the test will break at the above line, and will be compared to the expected exception
    }

    @Test
    public void shouldThrowExceptionOnParseXmlWithEmptyInput() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Input XML must not be empty.");

        bs.parseXml("");

        fail(); // Guarantees the test will break at the above line, and will be compared to the expected exception
    }

    @SneakyThrows
    private String loadFile(String path) {
        File file = new File(getClass().getClassLoader().getResource(path).getFile());
        return FileUtils.readFileToString(file, Charsets.UTF_8);
    }

}
