package org.ninjaware.notebook.model.enums;

import java.util.Arrays;

public enum PageStyle {
    Simple;

    public static boolean isValid(String value) {
        return Arrays.stream(values()).anyMatch(s -> s.name().equals(value));
    }

    public static PageStyle toEnum(String value) {
        if (isValid(value)) {
            return PageStyle.valueOf(value);
        } else {
            return null;
        }
    }

}

