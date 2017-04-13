package com.wandercosta.csvparser.business;

import com.github.underscore.lodash.$;
import java.util.Map;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Class to return implementation class.
 *
 * @author Wander Costa (rwander@gmail.com)
 */
@Slf4j
@Service
@AllArgsConstructor
public class ParserBusinessImpl implements ParserBusiness {

    private final MapFlattener mapFlattener;
    private final MapToCsvConverter mapToCsvConverter;

    /**
     * {@inheritDoc}
     */
    @Override
    public String parseJson(String json) {
        Objects.requireNonNull(json, "Input JSON must not be null.");
        requireNonEmpty(json, "Input JSON must not be empty.");

        return mapToCsvConverter.convert(mapFlattener.convert(new JSONObject(json).toMap()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String parseXml(String xml) {
        Objects.requireNonNull(xml, "Input XML must not be null.");
        requireNonEmpty(xml, "Input XML must not be empty.");

        Map<String, Object> xmlMap = (Map<String, Object>) $.fromXml(xml);
        if (xmlMap.get("soap:Body") != null) {
            xmlMap = (Map<String, Object>) xmlMap.get("soap:Body");
        }
        if (xmlMap.get("ProcessRequestXMLResponse") != null) {
            xmlMap = (Map<String, Object>) xmlMap.get("ProcessRequestXMLResponse");
        }
        if (xmlMap.get("ProcessRequestXMLResult") != null) {
            xmlMap = (Map<String, Object>) xmlMap.get("ProcessRequestXMLResult");
        }
        return mapToCsvConverter.convert(mapFlattener.convert(xmlMap));
    }

    private void requireNonEmpty(String input, String msg) {
        if (StringUtils.isEmpty(input)) {
            throw new IllegalArgumentException(msg);
        }
    }

}
