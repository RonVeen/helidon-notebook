package org.ninjaware.notebook.model.enums;


import java.util.Arrays;

public enum SortOrder {
    Ascending,
    Descending;

    public static boolean isValid(String value) {
        return Arrays.stream(values()).anyMatch(s -> s.name().equals(value));
    }

    public static SortOrder toEnum(String value) {
        if (isValid(value)) {
            return SortOrder.valueOf(value);
        } else {
            return null;
        }
    }

}
