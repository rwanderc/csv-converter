package com.wandercosta.csvparser.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.stereotype.Component;

/**
 * Class to transform a Map generated from JSON or XML processing into a flat map, with all data accessible from the
 * first level.
 *
 * @author Wander Costa (rwander@gmail.com)
 */
@Component
public class MapFlattener {

    private static final String SEPARATOR = ".";

    /**
     * Create a flat map from the input map. The flat map resulted will contain all the data in the input map, however,
     * the data will be available from the first level. The keys of the new map will be created from the composition of
     * the keys of the old map, making a path for all the entries.
     *
     * @param map The input map.
     */
    Map<String, String> convert(Map<String, Object> map) {
        Map<String, String> flatMap = new HashMap<>();
        convert(map, "", flatMap);
        return flatMap;
    }

    /**
     * Recursive method to create a new flat map of the input map. At the end of the method, the flatMap will contain
     * all the data in the input map, however, the data will be available from the first level. The keys of the new map
     * will be created from the composition of the keys of the old map, making a path.
     *
     * @param object
     * @param prefix
     * @param flatMap
     */
    private void convert(Object object, String prefix, Map<String, String> flatMap) {
        if (object instanceof Map) {
            ((Map) object).entrySet().forEach(entry -> convert(entry, prefix, flatMap));
        } else if (object instanceof List) {
            List list = (List) object;
            for (int i = 0; i < list.size(); i++) {
                convert(list.get(i), prefix.concat("[" + i + "]"), flatMap);
            }
        } else if (object instanceof Map.Entry) {
            Entry<String, Object> entry = (Entry<String, Object>) object;
            String newPrefix = prefix;
            if (prefix != null && !prefix.isEmpty()) {
                newPrefix = prefix.concat(SEPARATOR);
            }
            if (isPrimitive(entry.getValue())) {
                flatMap.put(newPrefix.concat(entry.getKey()), entry.getValue().toString());
            } else {
                convert(entry.getValue(), newPrefix.concat(entry.getKey()), flatMap);
            }
        }
    }

    /**
     * Validates if the object is an instance of a primitive type.
     *
     * @param obj
     * @return
     */
    private boolean isPrimitive(Object obj) {
        return (obj instanceof String)
                || (obj instanceof Integer)
                || (obj instanceof Float)
                || (obj instanceof Double);
    }

}
