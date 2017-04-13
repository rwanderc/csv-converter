package com.wandercosta.csvparser.business;

/**
 * Interface to represent Parser service.
 *
 * @author Wander Costa (rwander@gmail.com)
 */
public interface ParserBusiness {

    /**
     * Parses a JSON input into a CSV output.
     *
     * @param json the input in JSON format.
     * @return the output in CSV format.
     */
    String parseJson(String json);

    /**
     * Parses a XML input into a CSV output.
     *
     * @param xml the input in XML format.
     * @return the output in CSV format.
     */
    String parseXml(String xml);

}
