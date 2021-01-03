package org.ninjaware.notebook.model.system;

public interface KeyGenerator {

    KeyGenerator generator = new DefaultKeyGenerator();

    static KeyGenerator getInstance() {
        return generator;
    }

    String next();
}
