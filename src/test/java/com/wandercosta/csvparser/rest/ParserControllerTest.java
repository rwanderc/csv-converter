package com.wandercosta.csvparser.rest;

import static org.mockito.Mockito.verify;

import com.wandercosta.csvparser.business.ParserBusiness;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Class to test service controller class.
 *
 * @author Wander Costa (rwander@gmail.com)
 */
@RunWith(SpringRunner.class)
public class ParserControllerTest {

    @Mock
    private ParserBusiness bs;

    @Test
    public void testParseJson() {
        new ParserController(bs).parseJson("input text");
        verify(bs).parseJson("input text");
    }

    @Test
    public void testParseXml() {
        new ParserController(bs).parseXml("input text");
        verify(bs).parseXml("input text");
    }

}
