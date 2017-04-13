package com.wandercosta.csvparser.exception;


import static org.junit.Assert.assertEquals;

import java.util.Map;
import org.json.JSONException;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.Locator2Impl;

/**
 * Class to test exception handling.
 *
 * @author Wander Costa (rwander@gmail.com)
 */
public class GlobalExceptionHandlerTest {

    @Test
    public void shouldProcessException() {
        ResponseEntity<Map> resp = new GlobalExceptionHandler().process(new Exception("mocked exception"));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resp.getStatusCode());
        assertEquals("mocked exception", resp.getBody().get("error"));
    }

    @Test
    public void shouldProcessJSONException() {
        ResponseEntity<Map> resp = new GlobalExceptionHandler().process(new JSONException("mocked exception"));

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        assertEquals("Error while parsing JSON: mocked exception", resp.getBody().get("error"));
    }

    @Test
    public void shouldProcessXMLException() {
        SAXParseException exXML = new SAXParseException("mocked exception", new Locator2Impl());
        IllegalArgumentException exIAE = new IllegalArgumentException("mocked exception", exXML);
        ResponseEntity<Map> resp = new GlobalExceptionHandler().process(exIAE);

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        assertEquals("Error while parsing XML: mocked exception", resp.getBody().get("error"));
    }

    @Test
    public void shouldProcessIllegalArgumentException() {
        IllegalArgumentException ex = new IllegalArgumentException("mocked exception");
        ResponseEntity<Map> resp = new GlobalExceptionHandler().process(ex);

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        assertEquals("Illegal Argument provided: mocked exception", resp.getBody().get("error"));
    }

}
