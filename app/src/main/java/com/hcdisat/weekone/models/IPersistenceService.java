package com.hcdisat.weekone.models;

public interface IPersistenceService {

    void setValue(String key, String value);

    String getValue(String key);
}
