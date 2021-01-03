package org.ninjaware.notebook.model.enums;

import java.util.Arrays;

public enum SortStyle {
    Alphabetic,
    Date;

    public static boolean isValid(String value) {
        return Arrays.stream(values()).anyMatch(s -> s.name().equals(value));
    }

    public static SortStyle toEnum(String value) {
        if (isValid(value)) {
            return SortStyle.valueOf(value);
        } else {
            return null;
        }
    }

}
