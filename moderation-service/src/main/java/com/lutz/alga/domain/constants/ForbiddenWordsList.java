package com.lutz.alga.domain.constants;

import java.util.Collections;
import java.util.List;

public class ForbiddenWordsList {
    public static final List<String> WORDS;

    static {
        List<String> wordList = List.of("ódio", "xingamento");
        WORDS = Collections.unmodifiableList(wordList);
    }
}
