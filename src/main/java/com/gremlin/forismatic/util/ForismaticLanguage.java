package com.gremlin.forismatic.util;

import java.util.HashMap;
import java.util.Map;

public enum ForismaticLanguage {
    en("english"), ru("russian");

    private final String label;
    ForismaticLanguage(String label) {
        this.label = label;
    }

    // Storing the values in a map so we can avoid multiple iterations on repeated calls to the Enum.
    private static final Map<String, ForismaticLanguage> BY_LABEL = new HashMap<>();
    static {
        for (ForismaticLanguage value : values()) {
            BY_LABEL.put(value.label, value);
        }
    }

    public static ForismaticLanguage valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
