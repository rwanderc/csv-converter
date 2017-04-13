package com.wandercosta.csvparser.exception;

import java.util.Collections;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXParseException;

/**
 * Class to represent exceptions.
 *
 * @author Wander Costa (rwander@gmail.com)
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map> process(Exception ex) {
        log.warn("An error has occured: {}", ex);
        return response(500, ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(JSONException.class)
    public ResponseEntity<Map> process(JSONException ex) {
        log.warn("Error while parsing JSON: {}", ex);
        return response(400, "Error while parsing JSON: " + ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map> process(IllegalArgumentException ex) {
        if (ex.getCause() instanceof SAXParseException) {
            log.warn("Error while parsing XML: {}", ex);
            return response(400, "Error while parsing XML: " + ex.getCause().getMessage());
        } else {
            log.warn("Illegal Argument provided: {}", ex);
            return response(400, "Illegal Argument provided: " + ex.getMessage());
        }
    }

    private ResponseEntity<Map> response(int httpCode, String msg) {
        return ResponseEntity.status(httpCode).body(Collections.singletonMap("error", msg));
    }

}
