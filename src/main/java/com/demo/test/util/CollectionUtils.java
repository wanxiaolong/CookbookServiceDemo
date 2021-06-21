package com.demo.test.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtils {
    public static final String EMPTY_STRING = "";
    public static final String DELIMITER = ",";

    public static boolean isEmpty(Collection c) {
        return c == null || c.size() == 0;
    }

    /**
     * Join the element in list.
     * this is used for sql 'in' query
     */
    public static String joinString(List<String> list) {
        if (!isEmpty(list)) {
            List<String> newList = list.stream()
                    .map(s -> "'" + s + "'")
                    .collect(Collectors.toList());
            return String.join(DELIMITER, newList);
        }
        return EMPTY_STRING;
    }

    public static String joinLong(List<Long> list) {
        if (!isEmpty(list)) {
            List<String> newList = list.stream()
                    .map(l -> String.valueOf(l))
                    .collect(Collectors.toList());
            return String.join(DELIMITER, newList);
        }
        return EMPTY_STRING;
    }
}
