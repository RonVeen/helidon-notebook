package org.ninjaware.notebook.model.system;

import java.util.UUID;

public class DefaultKeyGenerator implements KeyGenerator {
    @Override
    public String next() {
        return UUID.randomUUID().toString();
    }
}
