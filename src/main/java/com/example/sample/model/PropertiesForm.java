package com.example.sample.model;

import java.util.HashMap;
import java.util.Map;

public class PropertiesForm {

    private Map<String, Object> properties = new HashMap<>();

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
