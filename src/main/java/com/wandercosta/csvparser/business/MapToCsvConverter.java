package com.wandercosta.csvparser.business;

import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * Converter to generate the CSV format from a Map.
 *
 * @author Wander Costa (rwander@gmail.com)
 */
@Component
public class MapToCsvConverter {

    /**
     * Convert a flat map into CSV format.
     *
     * @param map the map to be converted.
     * @return the String containing the CSV format of the map.
     */
    String convert(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        if (!CollectionUtils.isEmpty(map)) {
            map.keySet().stream().sorted().forEach(key -> sb.append(key).append(","));
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
            map.keySet().stream().sorted().forEach(key -> sb.append(map.get(key)).append(","));
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

}
