package com.lutz.alga.infrastructure.interfaces;

import lombok.NonNull;

public interface Validator {
    public boolean validate(@NonNull String input);
}
