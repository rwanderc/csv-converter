package com.wandercosta.csvparser.rest;

import com.wandercosta.csvparser.business.ParserBusiness;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST resource for Parser endpoint.
 *
 * @author Wander Costa (rwander@gmail.com)
 */
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("parser")
public class ParserController {

    private final ParserBusiness bs;

    /**
     * Parses a JSON input into a CSV output.
     *
     * @param json the input in JSON format.
     * @return the output in CSV format.
     */
    @PostMapping(value = "json", consumes = "application/json")
    public String parseJson(@RequestBody String json) {
        return bs.parseJson(json);
    }

    /**
     * Parses a JSON input into a CSV output.
     *
     * @param json the input in JSON format.
     * @return the output in CSV format as a file for download.
     */
    @PostMapping(value = "json/download", consumes = "application/json", produces = "text/csv")
    public ResponseEntity<String> parseJsonAndDownload(@RequestBody String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment; filename=\"data.csv\"");
        return new ResponseEntity<>(parseJson(json), headers, HttpStatus.OK);
    }

    /**
     * Parses a XML input into a CSV output.
     *
     * @param xml the input in XML format.
     * @return the output in CSV format.
     */
    @PostMapping(value = "xml", consumes = "application/xml")
    public String parseXml(@RequestBody String xml) {
        return bs.parseXml(xml);
    }

    /**
     * Parses a XML input into a CSV output.
     *
     * @param xml the input in XML format.
     * @return the output in CSV format.
     */
    @PostMapping(value = "xml/download", consumes = "application/xml", produces = "text/csv")
    public ResponseEntity<String> parseXmlAndDownload(@RequestBody String xml) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment; filename=\"data.csv\"");
        return new ResponseEntity<>(parseXml(xml), headers, HttpStatus.OK);
    }

}
