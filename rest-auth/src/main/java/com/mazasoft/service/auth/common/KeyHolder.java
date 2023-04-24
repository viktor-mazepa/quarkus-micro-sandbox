package com.mazasoft.service.auth.common;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class KeyHolder {
    private static Set<String> ALLOWED_KEYS;

    public void addKey(String key) {
        if (ALLOWED_KEYS == null) {
            ALLOWED_KEYS = new HashSet<>();
        }
        ALLOWED_KEYS.add(key);
    }

    public boolean containsKey(String key) {
        return ALLOWED_KEYS.contains(key);
    }

    public int getSize() {
        return ALLOWED_KEYS.size();
    }
}
