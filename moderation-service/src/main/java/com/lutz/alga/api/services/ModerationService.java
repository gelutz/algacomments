package com.lutz.alga.api.services;

import com.lutz.alga.api.dtos.ModerationInput;
import com.lutz.alga.api.dtos.ModerationOutput;

public class ModerationService {

    public ModerationOutput execute(ModerationInput input) {

        return new ModerationOutput(true, "");
    }
}
